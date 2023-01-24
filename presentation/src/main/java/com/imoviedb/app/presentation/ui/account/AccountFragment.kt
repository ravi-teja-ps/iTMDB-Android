package com.imoviedb.app.presentation.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.imoviedb.app.domain.account.model.AccountDomainModel
import com.imoviedb.app.presentation.R
import com.imoviedb.app.presentation.databinding.FragmentAccountBinding
import com.imoviedb.app.presentation.ui.account.viewmodel.AccountViewModel
import com.imoviedb.app.presentation.ui.base.BaseFragment
import com.imoviedb.app.presentation.ui.base.UiState
import com.imoviedb.app.presentation.ui.utils.UrlUtils
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseFragment() {

    override val hasBottomNavigation: Boolean = true
    override val isDetailScreen: Boolean = false
    override val showTitleBar: Boolean = true
    override val titleId: Int = R.string.account_screen

    private var _binder: FragmentAccountBinding? = null
    private val binding get() = _binder!!
    private val accountViewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binder = FragmentAccountBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            accountViewModel.getAccountData()
            accountViewModel.uiState.collect {
                when (it) {
                    is UiState.Loading -> {}
                    is UiState.OnComplete -> {
                        updateUi(it.data)
                    }
                    is UiState.OnError -> {
                        showErrorScreenWithInfo(it.errorCode, it.errorMessage)
                    }
                }
            }
        }
    }

    private fun updateUi(state: AccountDomainModel) {
        binding.userName.text = state.name
        binding.extraProp1.text = state.username
        Picasso.with(context).load(UrlUtils.GRAVATAR_URL_PREFIX + state.avatarHash)
            .into(binding.profileImage)
    }

    /**
     * Clear binder traces on fragment destroyed
     */
    override fun onDestroyBinding() {
        _binder = null
    }

}
package com.imoviedb.app.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.imoviedb.app.R
import com.imoviedb.app.data.models.account.AccountModel
import com.imoviedb.app.databinding.FragmentAccountBinding
import com.imoviedb.app.ui.account.viewmodel.AccountViewModel
import com.imoviedb.app.ui.core.BaseViewModel.State.*

import com.imoviedb.app.ui.core.BaseFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment(): BaseFragment() {

    override val hasBottomNavigation: Boolean = true
    override val isDetailScreen: Boolean = false
    override val showTitleBar: Boolean = true
    override val titleId: Int =R.string.account_screen

    private var _binder : FragmentAccountBinding? = null
    private val binding get() = _binder!!
    private val accountViewModel : AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binder = FragmentAccountBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            accountViewModel.getAccountData()
            accountViewModel.dataState.collect{
                when(it){
                    is Loading -> { }
                    is OnComplete ->  { updateUi(it.completionResult as AccountModel) }
                    is OnError ->  { }
                    is OnCompletePagedData -> {}
                }
            }
        }
    }

    private fun updateUi(state: AccountModel) {
        binding.textView.text = state.name
        binding.textView2.text = state.username
        Picasso.with(context).load("http://www.gravatar.com/avatar/" +state.avatar?.gravatar?.hash).into(binding.profileImage)
    }

    /**
     * Clear binder traces on fragment destroyed
     */
    override fun onDestroyBinding() {
        _binder = null
    }

    companion object {
        @JvmStatic
        fun newInstance( ) : AccountFragment {
            return  AccountFragment().apply {
            }
        }
    }
}
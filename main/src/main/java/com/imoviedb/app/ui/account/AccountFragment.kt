package com.imoviedb.app.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.imoviedb.app.data.models.account.AccountModel
import com.imoviedb.app.databinding.FragmentAccountBinding
import com.imoviedb.app.ui.account.viewmodel.AccountViewModel
import com.imoviedb.app.ui.core.BaseViewModel.State.*

import com.imoviedb.app.ui.core.BaseFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment(): BaseFragment() {

    private lateinit var accountScreenBinding : FragmentAccountBinding
    private val accountViewModel : AccountViewModel by viewModels()

    override val hasBottomNavigation: Boolean
        get() = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountScreenBinding = FragmentAccountBinding.inflate(inflater)
        return accountScreenBinding.root
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
        accountScreenBinding.textView.text = state.name
        accountScreenBinding.textView2.text = state.username
        Picasso.with(context).load("http://www.gravatar.com/avatar/" +state.avatar?.gravatar?.hash).into(accountScreenBinding.profileImage)
    }

    companion object {
        @JvmStatic
        fun newInstance( ) : AccountFragment {
            return  AccountFragment().apply {
            }
        }
    }
}
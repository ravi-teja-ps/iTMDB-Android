package com.imoviedb.app.ui.startup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.imoviedb.app.R
import com.imoviedb.app.databinding.FragmentSplashBinding
import com.imoviedb.app.ui.authentication.ui.LoginScreenFragment
import com.imoviedb.app.ui.core.BaseFragment
import com.imoviedb.app.ui.core.BaseViewModel
import com.imoviedb.app.ui.startup.viewmodel.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    //View model for initial token
    private val splashViewModel : SplashScreenViewModel by viewModels()

    //view binding for XML
    private lateinit var fragmentSplashBinding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAccessToken()
     }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSplashBinding = FragmentSplashBinding.inflate(inflater)
        return fragmentSplashBinding.root
    }

    /** Load basic access token for further api calls  */
    private fun loadAccessToken() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                splashViewModel.loadAccessTokenWithoutSession()
                splashViewModel.splashScreenState.collect{
                    when(it){
                        is BaseViewModel.State.Loading -> {
                            fragmentSplashBinding.progressBar.visibility = if(it.isLoading) View.VISIBLE else View.GONE
                        }

                        is BaseViewModel.State.OnComplete -> {
                            navigateToLoginScreen()
                        }

                        is BaseViewModel.State.OnError -> {
                            showErrorScreenWithInfo(it.errorCode)
                        }

                        is BaseViewModel.State.OnCompletePagedData -> {  }
                    }
                }
            }
        }
    }

    private fun navigateToLoginScreen(){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container,LoginScreenFragment.newInstance())?.commit()
    }

    override val hasBottomNavigation: Boolean
        get() = false

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }

}
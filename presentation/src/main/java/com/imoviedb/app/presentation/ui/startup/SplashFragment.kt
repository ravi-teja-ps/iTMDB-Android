package com.imoviedb.app.presentation.ui.startup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.imoviedb.app.presentation.R
import com.imoviedb.app.presentation.databinding.FragmentSplashBinding
import com.imoviedb.app.presentation.ui.base.BaseFragment
import com.imoviedb.app.presentation.ui.base.UiState
import com.imoviedb.app.presentation.ui.startup.viewmodel.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    override val hasBottomNavigation: Boolean = false
    override val isDetailScreen: Boolean = false
    override val showTitleBar: Boolean = false
    override val titleId: Int = R.string.app_name

    //View model for initial token
    private val splashViewModel: SplashScreenViewModel by viewModels()

    //view binding for XML
    private var _binder: FragmentSplashBinding? = null
    private val binding get() = _binder!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAccessToken()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binder = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    /** Load basic access token for further api calls  */
    private fun loadAccessToken() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                splashViewModel.loadAccessTokenWithoutSession()
                splashViewModel.splashScreenState.collect {
                    when (it) {
                        is UiState.Loading -> {
                                toggleProgressBar(it.isLoading)
                        }

                        is UiState.OnComplete -> {
                            toggleProgressBar(false)
                            navigateToLoginScreen()
                        }

                        is UiState.OnError -> {
                            showErrorScreenWithInfo(it.errorCode, it.errorMessage)
                            toggleProgressBar(false)
                        }
                    }
                }
            }
        }
    }

    private fun toggleProgressBar(willShowLoading: Boolean) {
        binding.progressBar.visibility =
            if (willShowLoading) View.VISIBLE else View.GONE
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(R.id.action_splashFragment_to_loginScreenFragment)
    }

    /**
     * invoked on onDestroy of fragment from base class
     */
    override fun onDestroyBinding() {
        _binder = null
    }
}
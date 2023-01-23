package com.imoviedb.app.presentation.ui.authentication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.imoviedb.app.presentation.R
import com.imoviedb.app.presentation.databinding.LoginScreenBinding
import com.imoviedb.app.presentation.ui.authentication.viewmodel.LoginViewModel
import com.imoviedb.app.presentation.ui.base.BaseFragment
import com.imoviedb.app.presentation.ui.base.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Login screen fragment to authenticate  user
 */
@AndroidEntryPoint
class LoginScreenFragment : BaseFragment() {

    override val hasBottomNavigation: Boolean = false
    override val isDetailScreen: Boolean = false
    override val showTitleBar: Boolean = false
    override val titleId: Int = R.string.login_screen

    private val loginViewModel: LoginViewModel by viewModels()

    //Binding goes here
    private var _binder: LoginScreenBinding? = null
    private val binding get() = _binder!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binder = LoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        registerForStatus()
    }

    private fun initViews() {

        with(binding) {
            emailEditField.editText?.setText(loginViewModel.userName.value)
            passwordEditField.editText?.setText(loginViewModel.password.value)

            setErrorHandlersForInputs()

            signinBtn.setOnClickListener {
                loginViewModel.login()
            }
        }
    }

    //Function to reset error inputs post re-edit
    private fun setErrorHandlersForInputs() {
        setSubmitButtonObserver()
        with(binding) {
            with(emailEditField) {
                editText?.doOnTextChanged { text, _, _, _ ->
                    isErrorEnabled = false
                    loginViewModel.setUserId(text.toString())
                }
            }

            with(passwordEditField) {
                editText?.doOnTextChanged { text, _, _, _ ->
                    isErrorEnabled = false
                    loginViewModel.setPassword(text.toString())
                }
            }

        }
    }

    private fun setSubmitButtonObserver() {
        lifecycleScope.launch {
            loginViewModel.signInButtonStatus.collect { value ->
                binding.signinBtn.isEnabled = value
                binding.genericErrorLabel.visibility = View.GONE
            }
        }
    }

    //Observe for State of the UI from view model
    private fun registerForStatus() {
        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                loginViewModel.loginScreenUiState.collect {
                    when (it) {
                        is State.Loading -> {
                            toggleProgressBar(it.isLoading)
                        }

                        is State.OnComplete -> {
                            navigateToMainFragment()
                            toggleProgressBar(false)
                        }

                        is State.OnError -> {
                            binding.genericErrorLabel.visibility = View.VISIBLE
                            binding.genericErrorLabel.text = it.errorMessage
                            binding.signinBtn.isEnabled = false
                            toggleProgressBar(false)
                        }

                        is State.OnCompletePagedData -> {} //Case not needed as it is not paged data result
                    }
                }
            }
        }
    }

    private fun navigateToMainFragment() {
        findNavController().navigate(R.id.action_loginScreenFragment_to_popularShowsFragment)
    }

    private fun toggleProgressBar(willShowLoading: Boolean) {
        binding.progressBarView.visibility =
            if (willShowLoading) View.VISIBLE else View.GONE
    }

    /**
     * Clear binder traces on fragment destroyed
     */
    override fun onDestroyBinding() {
        _binder = null
    }
}
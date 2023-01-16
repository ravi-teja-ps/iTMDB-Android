package com.imoviedb.app.presentation.ui.authentication.ui

import android.os.Bundle
import android.text.TextUtils
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binder = LoginScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        registerForStatus()
    }

    private fun initViews() {

        setErrorHandlersForInputs()

        binding.signinBtn.setOnClickListener {
            validateAndProceedInputFields(
                binding.emailEditField.editText?.text.toString(),
                binding.passwordEditField.editText?.text.toString()
            ) { id, message ->
                when (id) {
                    ERROR_INPUT_USERNAME -> {
                        binding.emailEditField.error = message
                    }

                    ERROR_INPUT_PWD -> {
                        binding.passwordEditField.error = message
                    }
                }
            }
        }

    }

    //Check for input validity
    private fun validateAndProceedInputFields(
        email: String,
        password: String,
        OnError: (id: Int, message: String) -> Unit
    ) {
        if (TextUtils.isEmpty(email)) {
            OnError(ERROR_INPUT_USERNAME, ERROR_MESSAGE_USER_NAME)
            return
        } else if (TextUtils.isEmpty(password)) {
            OnError(ERROR_INPUT_PWD, ERROR_MESSAGE_PASSWORD)
            return
        } else {
            loginViewModel.login(userName = email, password = password)
        }
    }

    //Function to reset error inputs post re-edit
    private fun setErrorHandlersForInputs() {
        binding.emailEditField.editText?.doOnTextChanged { _, _, _, _ ->
            binding.emailEditField.isErrorEnabled = false //reset
        }

        binding.passwordEditField.editText?.doOnTextChanged { _, _, _, _ ->
            binding.passwordEditField.isErrorEnabled = false
        }
    }

    //Observe for State of the UI from view model
    private fun registerForStatus() {
        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                loginViewModel.loginStatus.collect {
                    when (it) {
                        is State.Loading -> {
                            binding.progressBarView.visibility =
                                if (it.isLoading) View.VISIBLE else View.GONE
                        }

                        is State.OnComplete -> {
                            navigateToMainFragment()
                        }

                        is State.OnError -> {
                            showErrorScreenWithInfo(it.errorCode)
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


    /**
     * Clear binder traces on fragment destroyed
     */
    override fun onDestroyBinding() {
        _binder = null
    }


    companion object {
        //
        const val ERROR_INPUT_USERNAME = 1
        const val ERROR_INPUT_PWD = 2
        const val ERROR_MESSAGE_USER_NAME = "Email Address is not valid"
        const val ERROR_MESSAGE_PASSWORD = "Password is empty"
    }
}
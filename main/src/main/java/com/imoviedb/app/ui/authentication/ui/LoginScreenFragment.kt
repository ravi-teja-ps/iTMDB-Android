package com.imoviedb.app.ui.authentication.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.imoviedb.app.R
import com.imoviedb.app.databinding.LoginScreenBinding
import com.imoviedb.app.ui.authentication.viewmodel.LoginViewModel
import com.imoviedb.app.ui.core.BaseFragment
import com.imoviedb.app.ui.core.BaseViewModel.State.*
import com.imoviedb.app.ui.popularshows.showslist.PopularShowsFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Login screen fragment to authenticate tmdb user
 */
@AndroidEntryPoint
class LoginScreenFragment : BaseFragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binder: LoginScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binder = LoginScreenBinding.inflate(inflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        registerForStatus()
    }

    private fun initViews() {

        setErrorHandlersForInputs()

        binder.signinBtn.setOnClickListener {
            validateAndProceedInputFields(
                binder.emailEditField.editText?.text.toString(),
                binder.passwordEditField.editText?.text.toString()
            ) { id, message ->
                when (id) {
                    ERROR_INPUT_USERNAME -> {
                        binder.emailEditField.error = message
                    }

                    ERROR_INPUT_PWD -> {
                        binder.passwordEditField.error = message
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
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
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
        binder.emailEditField.editText?.doOnTextChanged { _, _, _, _ ->
            binder.emailEditField.isErrorEnabled = false //reset
        }

        binder.passwordEditField.editText?.doOnTextChanged { _, _, _, _ ->
            binder.passwordEditField.isErrorEnabled = false
        }
    }

    //Observe for State of the UI from view model
    private fun registerForStatus() {
        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                loginViewModel.loginStatus.collect {
                    when (it) {
                        is Loading -> {
                            binder.progressBarView.visibility =
                                if (it.isLoading) View.VISIBLE else View.GONE
                        }

                        is OnComplete -> {
                            navigateToMainFragment()
                        }

                        is OnError -> {
                            showErrorScreenWithInfo(it.errorCode)
                        }

                        is OnCompletePagedData -> {} //Case not needed as it is not paged data result
                    }
                }
            }
        }
    }

    private fun navigateToMainFragment() {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.container,
            PopularShowsFragment.newInstance()
        )?.commit()
    }

    override val hasBottomNavigation: Boolean
        get() = false


    companion object {
        //
        const val ERROR_INPUT_USERNAME = 1
        const val ERROR_INPUT_PWD = 2
        const val ERROR_MESSAGE_USER_NAME = "Email Address is not valid"
        const val ERROR_MESSAGE_PASSWORD = "Password is empty"

        @JvmStatic
        fun newInstance(): LoginScreenFragment {
            return LoginScreenFragment()
        }
    }
}
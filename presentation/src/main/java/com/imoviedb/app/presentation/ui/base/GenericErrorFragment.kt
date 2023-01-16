package com.imoviedb.app.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imoviedb.app.presentation.databinding.FragmentGenericErrorBinding

/**
 * Generic error message fragment with details
 */
class GenericErrorFragment : Fragment() {
    private var statusCode: Int? = null
    private var message:String? = null

    private lateinit var errorFragmentBinding: FragmentGenericErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            statusCode = it.getInt(ERROR_CODE_TYPE)
            message = it.getString(ERROR_MESG_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        errorFragmentBinding =
            FragmentGenericErrorBinding.inflate(inflater)
        return errorFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorFragmentBinding.errorDescription.text = message ?: ""
        errorFragmentBinding.errorTitle.text = statusCode.toString()

        errorFragmentBinding.retryErrorAction.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        const val ERROR_CODE_TYPE = "ErrorCode"
        const val ERROR_MESG_KEY = "statusMessage"
    }
}
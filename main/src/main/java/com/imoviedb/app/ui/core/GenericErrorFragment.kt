package com.imoviedb.app.ui.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.imoviedb.app.data.networking.utils.ErrorCodeMapper
import com.imoviedb.app.databinding.FragmentGenericErrorBinding

/**
 * Generic error message fragment with details
 */
class GenericErrorFragment : Fragment() {
    private var param1: Int? = null

    private lateinit var errorFragmentBinding: FragmentGenericErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ERROR_CODE_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        errorFragmentBinding = FragmentGenericErrorBinding.inflate(inflater)
        return errorFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val errorInfo = ErrorCodeMapper.getMessageDescriptionFromCode(param1 ?: -1)
        errorInfo.apply {
            errorFragmentBinding.errorDescription.text = second
            errorFragmentBinding.errorTitle.text = first
        }
    }


    companion object {
        private const val ERROR_CODE_TYPE = "ErrorCode"

        @JvmStatic
        fun newInstance(param1: Int) =
            GenericErrorFragment().apply {
                arguments = Bundle().apply {
                    putInt(ERROR_CODE_TYPE, param1)
                }
            }
    }
}
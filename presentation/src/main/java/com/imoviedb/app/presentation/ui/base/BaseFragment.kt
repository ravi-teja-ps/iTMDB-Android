package com.imoviedb.app.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imoviedb.app.presentation.R


/**
 * Base fragment to have common reusable functions across root activity screens
 */
abstract class BaseFragment : Fragment() {
    abstract val hasBottomNavigation: Boolean
    abstract val isDetailScreen: Boolean
    abstract val showTitleBar: Boolean
    abstract val titleId: Int
    private val title: String by lazy { resources.getString(titleId) }
    abstract fun onDestroyBinding()

    /** show bottom navigation on selected tab if enabled in respective fragment */
    private fun showBottomNavigationWithSelectedTab() {
        (activity as? RootActivity)?.let {
            it.findViewById<BottomNavigationView>(R.id.bottomNavigation).visibility =
                if (hasBottomNavigation) View.VISIBLE else View.GONE
        }
    }

    //Show a error fragment in current flow
    fun showErrorScreenWithInfo(code: Int, message: String?) {
        val bundle = Bundle().apply {
            if (message != null) {
                putString(GenericErrorFragment.ERROR_MESG_KEY, message)
            }
            putInt(GenericErrorFragment.ERROR_CODE_TYPE, code)
        }
        findNavController().navigate(R.id.genericErrorFragment, bundle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (showTitleBar) {
            showToolbar()
        } else {
            hideToolBar()
        }

        showBottomNavigationWithSelectedTab()
    }

    private fun showToolbar() {
        (activity as? RootActivity)?.let {
            val toolbar: Toolbar = it.findViewById(R.id.toolbar) as Toolbar
            toolbar.visibility = View.VISIBLE
            toolbar.title = title
            if (isDetailScreen) {
                toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
            } else {
                toolbar.navigationIcon = null
            }
        }
    }

    private fun hideToolBar() {
        (activity as? RootActivity)?.let {
            (it.findViewById(R.id.toolbar) as Toolbar).visibility = View.GONE
        }
    }

    fun updateToolbarTitle(headerTitle: String?) {
        (activity as? RootActivity)?.let {
            (it.findViewById(R.id.toolbar) as Toolbar).title = headerTitle ?: title
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onDestroyBinding()
    }
}
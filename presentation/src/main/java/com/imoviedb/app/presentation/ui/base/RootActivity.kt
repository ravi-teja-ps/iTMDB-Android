package com.imoviedb.app.presentation.ui.base

import android.os.Bundle
import androidx.navigation.findNavController
import com.imoviedb.app.presentation.R
import com.imoviedb.app.presentation.databinding.ActivityRootBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : BaseActivity() {

    private var _binder: ActivityRootBinding? = null
    private val binding get() = _binder!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binder = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationListener()
        initToolbar()
    }

    /**
     * function to handle tab events in root activity
     */
    private fun initBottomNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.popular_tab -> {
                    findNavController(R.id.container).navigate(R.id.popularShowsFragment)
                }
                R.id.account_tab -> {
                    findNavController(R.id.container).navigate(R.id.accountFragment)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun initToolbar() {

        binding.toolbar.title = title
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binder = null
    }
}
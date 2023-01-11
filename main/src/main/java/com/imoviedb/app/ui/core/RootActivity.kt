package com.imoviedb.app.ui.core

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.imoviedb.app.R
import com.imoviedb.app.databinding.ActivityRootBinding
import com.imoviedb.app.ui.account.AccountFragment
import com.imoviedb.app.ui.popularshows.showslist.PopularShowsFragment
import com.imoviedb.app.ui.startup.SplashFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : BaseActivity() {

    private var _binder: ActivityRootBinding? = null
    private val binding get() = _binder!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binder = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SplashFragment.newInstance()).commit()
        }

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
                    val popularShowsFragment = supportFragmentManager.findFragmentByTag("popular")
                        ?: PopularShowsFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, popularShowsFragment).addToBackStack("")
                        .commit()
                }
                R.id.account_tab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, AccountFragment.newInstance()).addToBackStack("")
                        .commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = title
        toolbar.setNavigationOnClickListener { _ ->
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binder = null
    }
}
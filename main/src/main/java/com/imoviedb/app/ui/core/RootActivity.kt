package com.imoviedb.app.ui.core

import android.os.Bundle
import com.imoviedb.app.R
import com.imoviedb.app.databinding.ActivityRootBinding
import com.imoviedb.app.ui.account.AccountFragment
import com.imoviedb.app.ui.startup.SplashFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : BaseActivity() {

    private lateinit var activityRootBinding : ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRootBinding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(activityRootBinding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SplashFragment.newInstance()).commit()
        }

        initBottomNavigationListener()
    }

    /**
     * function to handle tab events in root activity
     */
    private fun initBottomNavigationListener(){
        activityRootBinding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.popular_tab -> { }
                R.id.account_tab-> { supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AccountFragment.newInstance()).addToBackStack("").commit() }
             }
            return@setOnItemSelectedListener true
        }
    }
}
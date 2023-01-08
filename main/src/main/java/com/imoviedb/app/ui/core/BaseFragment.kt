package com.imoviedb.app.ui.core

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imoviedb.app.R

/**
 * Base fragment to have common reusable functions across root activity screens
 */
abstract class BaseFragment : Fragment() {
    abstract  val hasBottomNavigation : Boolean

  /** show bottom navigation on selected tab if enabled in respective fragment */
  fun showBottomNavigationWithSelectedTab(){
      (activity as? RootActivity)?.let {
          it.findViewById<BottomNavigationView>(R.id.bottomNavigation).visibility =
              if(hasBottomNavigation) View.VISIBLE else View.GONE
      }
  }

    //Show a error fragment in current flow
    fun showErrorScreenWithInfo(code : Int ){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container,GenericErrorFragment.newInstance(code) )?.commit()
    }
}
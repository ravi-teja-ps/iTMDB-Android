package com.imoviedb.app.presentation.ui.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity

//Function to close soft input in a activity which has a focused view.
fun FragmentActivity.closeKeyboardIfShown() {
    try {
        this.currentFocus?.let {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            manager?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }catch (e : Exception){ e.printStackTrace() }
}

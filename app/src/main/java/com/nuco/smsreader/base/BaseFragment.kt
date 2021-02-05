package com.nuco.smsreader.base

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Base fragment class
 */
abstract class BaseFragment : Fragment() {

    fun showToast(message: CharSequence, length: Int = Toast.LENGTH_LONG) {
        Toast.makeText(requireContext(), message, length).show()
    }
}

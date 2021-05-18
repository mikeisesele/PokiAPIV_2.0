package com.decagon.pokiapi_v20.utils

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String) {
    Toast.makeText(this.requireActivity(), message, Toast.LENGTH_SHORT).show()
}

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

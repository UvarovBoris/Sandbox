package com.uvarov.sandbox.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toastShort(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

fun Context.toastLong(@StringRes resId: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, resId, duration).show()
}
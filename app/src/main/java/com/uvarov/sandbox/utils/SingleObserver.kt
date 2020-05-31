package com.uvarov.sandbox.utils

import androidx.lifecycle.Observer

open class SingleObserver<T>(private val handler: (T) -> Unit) : Observer<SingleEvent<T>> {
    override fun onChanged(t: SingleEvent<T>) {
        t.getContent()?.let {
            handler.invoke(it)
        }
    }
}
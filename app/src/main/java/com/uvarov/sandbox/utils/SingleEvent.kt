package com.uvarov.sandbox.utils

class SingleEvent<out T>(private val content: T) {

    private var isHandled = false

    fun getContent(): T? {
        return if (isHandled) {
            null
        } else {
            isHandled = true
            content
        }
    }

    fun requireContent(): T = content
}
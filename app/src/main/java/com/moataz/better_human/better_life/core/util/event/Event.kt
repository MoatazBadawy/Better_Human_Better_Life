package com.moataz.better_human.better_life.core.util.event

import androidx.lifecycle.Observer

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return when (hasBeenHandled) {
            true -> null
            else -> {
                hasBeenHandled = true
                content
            }
        }
    }
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let(onEventUnhandledContent)
    }
}
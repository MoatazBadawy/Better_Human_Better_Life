package com.moataz.better_human.better_life.core.util.helper.main

import android.view.View
import android.view.Window
import androidx.core.view.ViewCompat
import javax.inject.Inject

class Views @Inject constructor() {
    fun provideViews(window: Window): Window {
        // make the status bar white with black icons
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        // make the app support only arabic "Right to left"
        // even if the language of the device on english or others
        ViewCompat.setLayoutDirection(window.decorView, ViewCompat.LAYOUT_DIRECTION_RTL)
        return window
    }
}
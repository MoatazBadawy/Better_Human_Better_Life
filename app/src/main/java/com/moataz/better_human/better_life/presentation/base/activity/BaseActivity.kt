package com.moataz.better_human.better_life.presentation.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB : ViewDataBinding>(private val layoutId: Int) :
    AppCompatActivity() {

    private lateinit var _binding: VDB
    protected val binding: VDB get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        _binding = DataBindingUtil.setContentView(this@BaseActivity, layoutId)
    }
}
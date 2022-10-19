package com.moataz.better_human.better_life.presentation.main.view

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.moataz.better_human.better_life.R
import com.moataz.better_human.better_life.core.util.helper.main.AppRating
import com.moataz.better_human.better_life.core.util.helper.main.InAppUpdate
import com.moataz.better_human.better_life.core.util.helper.main.Views
import com.moataz.better_human.better_life.databinding.ActivityMainBinding
import com.moataz.better_human.better_life.presentation.base.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    @Inject
    lateinit var initView: Views

    @Inject
    lateinit var appRating: AppRating

    @Inject
    lateinit var inAppUpdate: InAppUpdate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView.provideViews(window)
        appRating.provideAppRating(this)
        inAppUpdate.provideInAppUpdate(this, this)
    }

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)
        val colorState = ColorStateList.valueOf(Color.parseColor("#F8F8F8"))
        binding.bottomNavView.itemRippleColor = colorState
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode != RESULT_OK) {
                Log.e("MY_APP", "Update flow failed! Result code: $resultCode")
                inAppUpdate.provideInAppUpdate(this, this)
            }
        }
    }
}
package com.moataz.better_human.better_life.core.util.helper.main

import androidx.appcompat.app.AppCompatActivity
import com.suddenh4x.ratingdialog.AppRating
import com.suddenh4x.ratingdialog.preferences.RatingThreshold
import javax.inject.Inject

class AppRating @Inject constructor() {
    fun provideAppRating(activity: AppCompatActivity): Boolean {
        return AppRating.Builder(activity)
            .setMinimumLaunchTimes(3)
            .setMinimumDays(3)
            .useGoogleInAppReview()
            .setMinimumLaunchTimesToShowAgain(10)
            .setMinimumDaysToShowAgain(7)
            .setRatingThreshold(RatingThreshold.FOUR)
            .showIfMeetsConditions()
    }
}
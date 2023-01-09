package com.theviciousgames.dpimodifier

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.suddenh4x.ratingdialog.AppRating
import com.suddenh4x.ratingdialog.preferences.RatingThreshold
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DpiActivity : AppCompatActivity() {
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        keepScreenAwake()
        forceDayMode()
        initializeFirebase()
        rateApp()
    }

    private fun initializeFirebase()
    {
        analytics = Firebase.analytics
    }
  private fun keepScreenAwake()
   {
       window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
   }

    private fun forceDayMode()
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private fun rateApp()
    {
        AppRating.Builder(this)
            .setMinimumLaunchTimes(5)
            .setMinimumDays(7)
            .setMinimumLaunchTimesToShowAgain(5)
            .setMinimumDaysToShowAgain(10)
            .setRatingThreshold(RatingThreshold.FOUR)
            .showIfMeetsConditions()
    }
}
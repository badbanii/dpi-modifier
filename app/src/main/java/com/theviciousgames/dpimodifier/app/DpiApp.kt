package com.theviciousgames.dpimodifier.app

import android.app.Application
import com.fxn.stash.Stash
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DpiApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeStash()
    }

    private fun initializeStash()
    {
        Stash.init(this)
    }
}
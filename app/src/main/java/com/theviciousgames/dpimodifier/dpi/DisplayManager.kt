package com.theviciousgames.dpimodifier.dpi

import android.app.Activity
import android.util.DisplayMetrics


class DisplayManager(private val activity: Activity) {

    fun getScreenDpi(): Int {
        val dm = DisplayMetrics()
        activity.windowManager.defaultDisplay.getRealMetrics(dm)
        return dm.densityDpi
    }
}
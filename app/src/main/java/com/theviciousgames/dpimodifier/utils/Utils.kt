package com.theviciousgames.dpimodifier

import android.app.Activity
import android.util.DisplayMetrics

fun getDpi(activity:Activity):Int
{
    val dm = DisplayMetrics()
    activity.windowManager.defaultDisplay.getRealMetrics(dm)
    return dm.densityDpi
}

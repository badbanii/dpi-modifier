package com.theviciousgames.dpimodifier.wm

import android.annotation.SuppressLint
import android.app.Activity
import android.util.DisplayMetrics
import android.view.Display
import com.theviciousgames.dpimodifier.utils.Constants
import com.theviciousgames.dpimodifier.utils.Constants.CLASS_NAME_WINDOW_MANAGER

class WmUtils {

    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    private fun getWindowManagerService(): Any? {
        return try {
            val wmg = Class.forName(Constants.CLASS_NAME_WINDOW_MANAGER_GLOBAL)
            val getWindowManagerService =
                wmg.getDeclaredMethod(Constants.SERVICE_NAME_WINDOW_MANAGER_GLOBAL)
            getWindowManagerService.isAccessible = true
            getWindowManagerService.invoke(null)
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }

    @SuppressLint("PrivateApi")
    fun setDisplayDensity(density: Int) {

        val wmService = getWindowManagerService() ?: return

        try {
            Class.forName(CLASS_NAME_WINDOW_MANAGER)
                .getMethod(
                    "setForcedDisplayDensity",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                .invoke(wmService, Display.DEFAULT_DISPLAY, density)
        } catch (_: Exception) {
        }

        try {
            Class.forName(CLASS_NAME_WINDOW_MANAGER)
                .getMethod(
                    "setForcedDisplayDensityForUser",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                .invoke(wmService, Display.DEFAULT_DISPLAY, density, -3)
        } catch (_: Exception) {
        }
    }

    @SuppressLint("PrivateApi")
    fun resetDisplayDensity() {

        val wmService = getWindowManagerService() ?: return

        try {
            Class.forName(CLASS_NAME_WINDOW_MANAGER)
                .getMethod(
                    "clearForcedDisplayDensityForUser",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                .invoke(wmService, Display.DEFAULT_DISPLAY, -3)
        } catch (_: Exception) {
        }
    }

    fun getDisplayDensity(activity: Activity): Int {
        val dm = DisplayMetrics()
        activity.windowManager.defaultDisplay.getRealMetrics(dm)
        return dm.densityDpi
    }
}

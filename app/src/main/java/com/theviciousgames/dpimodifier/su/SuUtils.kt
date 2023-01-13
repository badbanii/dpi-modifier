package com.theviciousgames.dpimodifier.su

import android.util.Log
import com.topjohnwu.superuser.Shell
import eu.chainfire.libsuperuser.Shell.SU

class SuUtils {
    fun setDisplayDensity(dpi: Int) {
        Shell.cmd("wm density $dpi").submit { result: Shell.Result? ->
            Log.d(
                "debug",
                result?.out.toString()
            )
            Log.d("debug", "error: " + result?.err.toString())
        }
    }

    fun resetDisplayDensity() {
        Shell.cmd("wm density reset").submit { result: Shell.Result? ->
            Log.d(
                "debug",
                "result: " + result?.out.toString()
            )
            Log.d("debug", "error: " + result?.err.toString())
        }
    }

    fun getRootAccess(): Boolean {
        return SU.available()
    }
}
package com.theviciousgames.dpimodifier.su

import android.util.Log
import com.topjohnwu.superuser.Shell

class SuUtils {
     fun setDisplayDensity(dpi:Int)
    {
        Shell.cmd("wm density $dpi").submit { result: Shell.Result? ->
            Log.d("debug",
                result?.out.toString()
            )
            Log.d("debug",result?.err.toString())
        }
    }

    fun resetDisplayDensity()
    {
        Shell.cmd("wm density reset").submit { result: Shell.Result? ->
            Log.d("debug",
                "out "+result?.out.toString()
            )
            Log.d("debug","err "+result?.err.toString())
        }
    }

    fun shellRun(cmd:String)
    {
        Shell.cmd(cmd).submit { result: Shell.Result? ->
            Log.d("debug",
                "out "+result?.out.toString()
            )
            Log.d("debug","err "+result?.err.toString())
        }
    }

     fun hasRootAccess():Boolean
    {
        return Shell.rootAccess()
    }
}
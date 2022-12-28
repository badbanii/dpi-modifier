package com.theviciousgames.dpimodifier.su

import android.util.Log
import com.topjohnwu.superuser.Shell

class SuShell {
     fun updateDpiTo(dpi:Int)
    {
        Shell.cmd("wm density $dpi").submit { result: Shell.Result? ->
            Log.d("debug",
                result?.out.toString()
            )
            Log.d("debug",result?.err.toString())
        }
    }

    fun resetDpiToDefault()
    {
        Shell.cmd("wm size reset").submit { result: Shell.Result? ->
            Log.d("debug",
                result?.out.toString()
            )
            Log.d("debug",result?.err.toString())
        }
    }

    fun shellTest(cmd:String)
    {
        Shell.cmd(cmd).submit { result: Shell.Result? ->
            Log.d("debug",
                result?.out.toString()
            )
            Log.d("debug",result?.err.toString())
        }
    }
     fun hasRootAccess():Boolean?
    {
        return Shell.isAppGrantedRoot()
    }
}
package com.theviciousgames.dpimodifier.ui.newbie.newbie

import androidx.lifecycle.ViewModel
import com.theviciousgames.dpimodifier.dpi.DisplayManager
import com.theviciousgames.dpimodifier.su.SuShell

class NewbieFragmentViewModel: ViewModel() {
    private var suShell: SuShell = SuShell()
    private lateinit var displayManager:DisplayManager

    var newDpi=0
    var oldDpi=0

    fun getCurrentDpi():Int
    {
        return displayManager.getScreenDpi()
    }
    fun hasRootAccess()
    {
        suShell.hasRootAccess()
    }

    fun shellTest(cmd:String)
    {
        suShell.shellTest(cmd)
    }

    fun updateDpiTo(dpi:Int)
    {
        suShell.updateDpiTo(dpi)
    }

    fun resetDpiToDefault()
    {
        suShell.resetDpiToDefault()
    }
}
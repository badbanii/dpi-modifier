package com.theviciousgames.dpimodifier.ui.newbie.newbie

import androidx.lifecycle.ViewModel
import com.theviciousgames.dpimodifier.su.SuShell

class NewbieFragmentViewModel: ViewModel() {
    private var suShell: SuShell = SuShell()
    var newDpi=0
    var oldDpi=0

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
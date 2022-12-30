package com.theviciousgames.dpimodifier.ui.newbie

import androidx.lifecycle.ViewModel
import com.theviciousgames.dpimodifier.su.SuShell
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NewbieFragmentViewModel @Inject constructor(
    private val suShell: SuShell,
) : ViewModel() {

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
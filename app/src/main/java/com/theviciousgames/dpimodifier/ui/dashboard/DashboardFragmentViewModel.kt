package com.theviciousgames.dpimodifier.ui.dashboard

import androidx.lifecycle.ViewModel
import com.fxn.stash.Stash
import com.theviciousgames.dpimodifier.su.SuShell
import com.theviciousgames.dpimodifier.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DashboardFragmentViewModel @Inject constructor(
    private val suShell: SuShell,
) : ViewModel() {

    var newDpi=0
    var oldDpi=0

    fun getShowConfirmationSetting():Boolean
    {
        return Stash.getBoolean(Constants.SHOW_CHANGE_CONFIRMATION,true)
    }
    fun setShowConfirmationSetting(value:Boolean)
    {
        Stash.put(Constants.SHOW_CHANGE_CONFIRMATION,value)
    }
    fun hasRootAccess(): Boolean
    {
        return suShell.hasRootAccess()
    }

    fun shellTest(cmd:String)
    {
        suShell.shellRun(cmd)
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
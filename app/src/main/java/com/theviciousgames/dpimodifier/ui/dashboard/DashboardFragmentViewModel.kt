package com.theviciousgames.dpimodifier.ui.dashboard

import androidx.lifecycle.ViewModel
import com.fxn.stash.Stash
import com.theviciousgames.dpimodifier.su.SuUtils
import com.theviciousgames.dpimodifier.utils.Constants
import com.theviciousgames.dpimodifier.wm.WmUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.chainfire.libsuperuser.Shell.SU
import javax.inject.Inject


@HiltViewModel
class DashboardFragmentViewModel @Inject constructor(
    private val suUtils: SuUtils,
    private val wmUtils: WmUtils
) : ViewModel() {

    var newDpi=0
    var oldDpi=0

    fun setDisplayDensity(value:Int)
    {
        if(getRootAccess())
        {
            suUtils.setDisplayDensity(value)
        }
        else
        {
            wmUtils.setDisplayDensity(value)
        }
    }
    fun getShowConfirmationSetting():Boolean
    {
        return Stash.getBoolean(Constants.SHOW_CHANGE_CONFIRMATION,true)
    }
    fun setShowConfirmationSetting(value:Boolean)
    {
        Stash.put(Constants.SHOW_CHANGE_CONFIRMATION,value)
    }
    fun getRootAccess(): Boolean
    {
        return SU.available()
    }
    fun shellRun(cmd:String)
    {
        suUtils.shellRun(cmd)
    }

    fun resetDisplayDensity()
    {
        if(getRootAccess())
        {
            suUtils.resetDisplayDensity()
        }
        else
        {
            wmUtils.resetDisplayDensity()
        }

    }
}
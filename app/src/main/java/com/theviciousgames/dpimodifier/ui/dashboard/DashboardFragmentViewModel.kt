package com.theviciousgames.dpimodifier.ui.dashboard

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.fxn.stash.Stash
import com.theviciousgames.dpimodifier.su.SuUtils
import com.theviciousgames.dpimodifier.utils.Constants
import com.theviciousgames.dpimodifier.wm.WmUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DashboardFragmentViewModel @Inject constructor(
    private val suUtils: SuUtils,
    private val wmUtils: WmUtils
) : ViewModel() {

    fun getDisplayDensity(activity: Activity):Int
    {
        return wmUtils.getDisplayDensity(activity)
    }

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
        return suUtils.getRootAccess()
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
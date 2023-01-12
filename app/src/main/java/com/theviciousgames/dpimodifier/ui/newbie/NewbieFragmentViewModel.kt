package com.theviciousgames.dpimodifier.ui.newbie

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.fxn.stash.Stash
import com.theviciousgames.dpimodifier.su.SuUtils
import com.theviciousgames.dpimodifier.utils.Constants
import com.theviciousgames.dpimodifier.utils.Operation
import com.theviciousgames.dpimodifier.wm.WmUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NewbieFragmentViewModel @Inject constructor(
    private val suUtils: SuUtils,
    private val wmUtils: WmUtils
) : ViewModel() {

    private fun getRootAccess(): Boolean
    {
        return suUtils.getRootAccess()
    }

    fun getDisplayDensity(activity: Activity):Int
    {
        return wmUtils.getDisplayDensity(activity)
    }

    fun setDisplayDensity(value:Int,operation: Operation)
    {
        if(getRootAccess())
        {
            if(operation==Operation.INCREASE){
                suUtils.setDisplayDensity(value+5)
            }
            else{
                suUtils.setDisplayDensity(value-5)
            }
        }
        else
        {
            if(operation==Operation.INCREASE)
            {
                wmUtils.setDisplayDensity(value+5)
            }
            else{
                wmUtils.setDisplayDensity(value-5)
            }
        }
    }

    fun resetDisplayDensity()
    {
        if(getRootAccess())
        {
            suUtils.resetDisplayDensity()
        }else{
            wmUtils.resetDisplayDensity()
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
}
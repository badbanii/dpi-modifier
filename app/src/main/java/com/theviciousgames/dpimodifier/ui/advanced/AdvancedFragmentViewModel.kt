package com.theviciousgames.dpimodifier.ui.advanced

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fxn.stash.Stash
import com.theviciousgames.dpimodifier.su.SuUtils
import com.theviciousgames.dpimodifier.utils.Constants
import com.theviciousgames.dpimodifier.wm.WmUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class AdvancedFragmentViewModel @Inject constructor(
    private val suUtils: SuUtils,
    private val wmUtils: WmUtils
): ViewModel() {

    var oldDpi=290

    private val _resetDPI = MutableStateFlow(false)
    val resetDpi: StateFlow<Boolean> = _resetDPI

    private fun triggerResetDpi(value: Boolean) = viewModelScope.launch {
        _resetDPI.value = value
    }
    fun getDpi(activity: Activity):Int
    {
        return wmUtils.getDpi(activity)
    }
    private fun getRootAccess(): Boolean
    {
        return suUtils.getRootAccess()
    }
    fun resetDpiTriggerFlow()
    {
        _resetDPI.value=false
    }
    fun saveCurrentConfiguration(value:Int)
    {
        oldDpi=value
    }
     fun runDelayedReset() {
        Timer().schedule(15000) {
            Log.d("debug","Reset DPI")
            triggerResetDpi(true)
        }
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
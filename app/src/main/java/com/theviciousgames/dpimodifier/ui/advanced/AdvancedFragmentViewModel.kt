package com.theviciousgames.dpimodifier.ui.advanced

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fxn.stash.Stash
import com.theviciousgames.dpimodifier.su.SuShell
import com.theviciousgames.dpimodifier.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class AdvancedFragmentViewModel @Inject constructor(
    private val suShell: SuShell
): ViewModel() {

    var newDpi=290
    var oldDpi=290

    private val _resetDPI = MutableStateFlow(false)
    val resetDpi: StateFlow<Boolean> = _resetDPI

    private fun triggerResetDpi(value: Boolean) = viewModelScope.launch {
        _resetDPI.value = value
    }

    fun resetDpiTriggerFlow()
    {
        _resetDPI.value=false
    }
    fun saveCurrentConfiguration(value:Int)
    {
        oldDpi=value
    }
     fun delayedReset() {
        Timer().schedule(15000) {
            Log.d("debug","Reset DPI")
            triggerResetDpi(true)
        }
    }

    fun hasRootAccess()
    {
        suShell.hasRootAccess()
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

    fun getShowConfirmationSetting():Boolean
    {
        return Stash.getBoolean(Constants.SHOW_CHANGE_CONFIRMATION,true)
    }

    fun setShowConfirmationSetting(value:Boolean)
    {
        Stash.put(Constants.SHOW_CHANGE_CONFIRMATION,value)
    }
}
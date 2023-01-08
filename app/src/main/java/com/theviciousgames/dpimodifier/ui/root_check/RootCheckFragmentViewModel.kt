package com.theviciousgames.dpimodifier.ui.root_check

import androidx.lifecycle.ViewModel
import com.theviciousgames.dpimodifier.su.SuUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RootCheckFragmentViewModel @Inject constructor(
    private val suUtils: SuUtils,
) : ViewModel() {

    var newDpi=0
    var oldDpi=0

    fun hasRootAccess(): Boolean
    {
        return suUtils.hasRootAccess()
    }

    fun shellTest(cmd:String)
    {
        suUtils.shellRun(cmd)
    }

    fun updateDpiTo(dpi:Int)
    {
        suUtils.updateDpiTo(dpi)
    }

    fun resetDpiToDefault()
    {
        suUtils.resetDpiToDefault()
    }
}
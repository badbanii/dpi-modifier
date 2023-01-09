package com.theviciousgames.dpimodifier.ui.root_check

import android.content.Context
import androidx.lifecycle.ViewModel
import com.theviciousgames.dpimodifier.su.SuUtils
import com.theviciousgames.dpimodifier.utils.PermissionChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RootCheckFragmentViewModel @Inject constructor(
    private val suUtils: SuUtils,
    private val permissionChecker: PermissionChecker
) : ViewModel() {

    var newDpi=0
    var oldDpi=0

     fun isWriteSecureSettingsPermissionGranted(context:Context): Boolean {
        return permissionChecker.isPermissionsGranted(context, android.Manifest.permission.WRITE_SECURE_SETTINGS)
    }

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
        suUtils.setDisplayDensity(dpi)
    }

    fun resetDpiToDefault()
    {
        suUtils.resetDisplayDensity()
    }
}
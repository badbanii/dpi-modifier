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

     fun isWriteSecureSettingsPermissionGranted(context:Context): Boolean {
        return permissionChecker.isPermissionsGranted(context, android.Manifest.permission.WRITE_SECURE_SETTINGS)
    }

    private fun getRootAccess(): Boolean
    {
        return suUtils.getRootAccess()
    }
}
package com.theviciousgames.dpimodifier.utils

import android.content.Context
import android.content.pm.PackageManager

class PermissionChecker {
    fun isPermissionsGranted(context: Context, permission: String): Boolean =
        context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
}
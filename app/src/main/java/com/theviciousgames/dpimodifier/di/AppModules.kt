package com.theviciousgames.dpimodifier.di

import com.theviciousgames.dpimodifier.su.SuUtils
import com.theviciousgames.dpimodifier.utils.PermissionChecker
import com.theviciousgames.dpimodifier.wm.WmUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Provides
    @Singleton
    fun provideSuUtils(): SuUtils {
        return SuUtils()
    }

    @Provides
    @Singleton
    fun provideWmUtils():WmUtils{
        return WmUtils()
    }

    @Provides
    @Singleton
    fun providePermissionChecker():PermissionChecker
    {
        return PermissionChecker()
    }
}
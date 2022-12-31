package com.theviciousgames.dpimodifier.di

import com.theviciousgames.dpimodifier.su.SuShell
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
    fun provideSuShell(): SuShell {
        return SuShell()
    }
}
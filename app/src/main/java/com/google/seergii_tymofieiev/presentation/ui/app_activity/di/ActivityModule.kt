package com.google.seergii_tymofieiev.presentation.ui.app_activity.di

import androidx.appcompat.app.AppCompatActivity
import com.google.seergii_tymofieiev.presentation.di.scope.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @Provides
    @PerActivity
    fun provideFragmentManager() = activity.supportFragmentManager
}
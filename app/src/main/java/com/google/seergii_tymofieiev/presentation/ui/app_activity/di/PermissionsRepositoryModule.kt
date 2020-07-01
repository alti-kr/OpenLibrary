package com.google.seergii_tymofieiev.presentation.ui.app_activity.di

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.google.seergii_tymofieiev.presentation.di.scope.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@Module
class PermissionsRepositoryModule (val activity: FragmentActivity) {


    @Provides
    @PerActivity
    fun provideContext(): Context = activity

    @Provides
    @PerActivity
    fun provideActivity(): FragmentActivity = activity


}
package com.google.seergii_tymofieiev.presentation.di

import com.google.seergii_tymofieiev.presentation.di.module.ApiModule
import com.google.seergii_tymofieiev.presentation.di.module.AppModule
import com.google.seergii_tymofieiev.presentation.di.module.NavigationModule
import com.google.seergii_tymofieiev.presentation.di.module.RepositoryModule
import com.google.seergii_tymofieiev.presentation.ui.app_activity.di.ActivityComponent
import com.google.seergii_tymofieiev.presentation.ui.app_activity.di.ActivityModule
import com.google.seergii_tymofieiev.presentation.ui.app_activity.di.PermissionsRepositoryModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        NavigationModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun plusActivityComponent(
        permissionModule: PermissionsRepositoryModule,
        activityModule: ActivityModule
    ): ActivityComponent
}
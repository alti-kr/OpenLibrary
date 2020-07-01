package com.google.seergii_tymofieiev.presentation.ui.app_activity.di

import com.google.seergii_tymofieiev.presentation.di.scope.PerActivity
import com.google.seergii_tymofieiev.presentation.ui.app_activity.AppActivity
import com.google.seergii_tymofieiev.presentation.ui.open_library.di.LibraryFlowContainerComponent
import dagger.Subcomponent

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@PerActivity
@Subcomponent(modules = [PermissionsRepositoryModule::class, ActivityModule::class])
interface ActivityComponent {
    fun inject(appActivity: AppActivity)
    fun plusLibraryFlowComponent(): LibraryFlowContainerComponent
    companion object {
        var activityComponent: ActivityComponent? = null
    }
}
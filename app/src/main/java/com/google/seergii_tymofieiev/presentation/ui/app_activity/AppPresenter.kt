package com.google.seergii_tymofieiev.presentation.ui.app_activity

import com.arellomobile.mvp.InjectViewState
import com.google.seergii_tymofieiev.presentation.base.BasePresenterError
import com.google.seergii_tymofieiev.presentation.di.scope.PerActivity
import com.google.seergii_tymofieiev.presentation.navigation.FlowRouter
import com.google.seergii_tymofieiev.presentation.navigation.MainRouter
import com.google.seergii_tymofieiev.presentation.navigation.Screens
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@InjectViewState
@PerActivity
class AppPresenter  @Inject constructor(
    private val router: MainRouter
) : BasePresenterError<MainView>(FlowRouter(router)) {

    fun onCreate() {
        router.newRootScreen(Screens.LibraryFlowScreen)
    }
}
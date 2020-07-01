package com.google.seergii_tymofieiev.presentation.navigation

import com.google.seergii_tymofieiev.presentation.di.scope.PerFlow
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@PerFlow
class FlowRouter(private val appRouter: Router) : Router() {

    fun startFlow(screen: SupportAppScreen) {
        appRouter.navigateTo(screen)
    }

    fun newRootFlow(screen: SupportAppScreen) {
        appRouter.newRootScreen(screen)
    }

    fun finishFlow() {
        appRouter.exit()
    }
}
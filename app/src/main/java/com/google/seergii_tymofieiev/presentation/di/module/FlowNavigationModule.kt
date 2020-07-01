package com.google.seergii_tymofieiev.presentation.di.module

import com.google.seergii_tymofieiev.presentation.di.scope.PerFlow
import com.google.seergii_tymofieiev.presentation.navigation.FlowRouter
import com.google.seergii_tymofieiev.presentation.navigation.MainRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@Module
class FlowNavigationModule {

    @Provides
    @PerFlow
    fun provideCicerone(appRouter: MainRouter) = Cicerone.create(FlowRouter(appRouter))

    @Provides
    @PerFlow
    fun provideRouter(cicerone: Cicerone<FlowRouter>): FlowRouter = cicerone.router

}
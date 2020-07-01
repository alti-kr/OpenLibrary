package com.google.seergii_tymofieiev.presentation.di.module

import com.google.seergii_tymofieiev.presentation.navigation.MainRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideCicerone() = Cicerone.create(MainRouter())

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<MainRouter>): MainRouter = cicerone.router

    @Provides
    @Singleton
    fun provideNavigationHolder(cicerone: Cicerone<MainRouter>) = cicerone.navigatorHolder

}
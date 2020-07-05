package com.google.seergii_tymofieiev

import android.app.Application
import android.content.Context
import com.google.seergii_tymofieiev.presentation.di.AppComponent
import com.google.seergii_tymofieiev.presentation.di.DaggerAppComponent
import com.google.seergii_tymofieiev.presentation.di.module.AppModule

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
class App : Application() {
    companion object {
        lateinit var OLApplication: App
        fun getContext(): Context {
            return OLApplication.applicationContext
        }

        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        OLApplication = this
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
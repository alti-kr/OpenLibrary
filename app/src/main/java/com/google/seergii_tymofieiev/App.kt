package com.google.seergii_tymofieiev

import android.app.Application
import com.google.seergii_tymofieiev.presentation.di.AppComponent
import com.google.seergii_tymofieiev.presentation.di.DaggerAppComponent
import com.google.seergii_tymofieiev.presentation.di.module.AppModule

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
class App : Application() {
    companion object{
        lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }
    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
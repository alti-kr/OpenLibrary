package com.google.seergii_tymofieiev.presentation.di.module

import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }
    @Provides
    @Singleton
    fun provideInputMethodManager(app: Application): InputMethodManager {
        return app.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
}
package com.google.seergii_tymofieiev.presentation.di.module

import android.app.Application
import com.google.seergii_tymofieiev.data.http_api.HttpAuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesOkHttpCache(application: Application): Cache {
        val size: Long = 15 * 1024 * 1024 // 15 MB
        return Cache(application.cacheDir, size)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: Interceptor,
        cache: Cache,
        authInterceptor: HttpAuthInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
package com.google.seergii_tymofieiev.presentation.di.module

import android.app.Application
import com.google.gson.GsonBuilder
import com.google.seergii_tymofieiev.data.http_api.HttpAuthInterceptor
import com.google.seergii_tymofieiev.data.http_api.RxErrorHandlingCallAdapterFactory
import com.google.seergii_tymofieiev.data.net.BooksApi
import com.google.seergii_tymofieiev.utils.AppConfig.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideGsonBuilder(): GsonBuilder {
        return GsonBuilder()
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
            level = HttpLoggingInterceptor.Level.NONE
        }
    }
    @Provides
    @Singleton
    fun provideRetrofit(gsonBuilder: GsonBuilder, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideBooksListApi(retrofit: Retrofit): BooksApi {
        return retrofit.create(BooksApi::class.java)
    }
}
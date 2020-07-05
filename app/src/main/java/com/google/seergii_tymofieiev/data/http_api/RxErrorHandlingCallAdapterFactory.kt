package com.google.seergii_tymofieiev.data.http_api

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@Suppress("UNCHECKED_CAST")
class RxErrorHandlingCallAdapterFactory: CallAdapter.Factory() {

    private val defaultFactory by lazy {
        RxJava2CallAdapterFactory.create()
    }

    companion object {
        fun create(): CallAdapter.Factory = RxErrorHandlingCallAdapterFactory()

        private fun asRetrofitException(throwable: Throwable, retrofit: Retrofit): RetrofitException {
            return when (throwable) {
                is HttpException -> {
                    val response = throwable.response()
                    RetrofitException.httpError(
                        response!!.raw().request.url.toString(),
                        response,
                        retrofit
                    )
                }
                is IOException -> {
                    // A network error happened
                    RetrofitException.networkError(throwable)
                }
                else -> {
                    // We don't know what happened. We need to simply convert to an unknown error
                    RetrofitException.unexpectedError(throwable)
                }
            }
        }
    }

    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val rawType = getRawType(returnType)
        return when (rawType) {
            Single::class.java -> {
                val wrapped = defaultFactory.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
                RxCallAdapterWrapper(retrofit, wrapped)
            }
            Observable::class.java -> {
                val wrapped = defaultFactory.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
                RxCallAdapterWrapperForObservable(retrofit, wrapped)
            }
            Completable::class.java -> {
                val wrapped = defaultFactory.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
                RxCallAdapterWrapperForCompletable(retrofit, wrapped)
            }
            else -> null
        }
    }

    private class RxCallAdapterWrapper<R>(
        val retrofit: Retrofit,
        val wrapped: CallAdapter<R, *>
    ) : CallAdapter<R, Single<R>> {

        override fun responseType() = wrapped.responseType()

        override fun adapt(call: Call<R>): Single<R> {
            val adapted = (wrapped.adapt(call) as Single<R>)
            return adapted.onErrorResumeNext { throwable: Throwable ->
                Single.error(
                    asRetrofitException(
                        throwable,
                        retrofit
                    )
                )
            }
        }
    }

    private class RxCallAdapterWrapperForObservable<R>(
        val retrofit: Retrofit,
        val wrapped: CallAdapter<R, *>
    ) : CallAdapter<R, Observable<R>> {

        override fun responseType() = wrapped.responseType()

        override fun adapt(call: Call<R>): Observable<R> {
            val adapted = (wrapped.adapt(call) as Observable<R>)
            return adapted.onErrorResumeNext { throwable: Throwable ->
                Observable.error(
                    asRetrofitException(
                        throwable,
                        retrofit
                    )
                )
            }
        }
    }

    private class RxCallAdapterWrapperForCompletable<R>(
        val retrofit: Retrofit,
        val wrapped: CallAdapter<R, *>
    ) : CallAdapter<R, Completable> {

        override fun responseType() = wrapped.responseType()

        override fun adapt(call: Call<R>): Completable {
            val adapted = (wrapped.adapt(call) as Completable)
            return adapted.onErrorResumeNext { throwable: Throwable ->
                Completable.error(
                    asRetrofitException(
                        throwable,
                        retrofit
                    )
                )
            }
        }
    }

}
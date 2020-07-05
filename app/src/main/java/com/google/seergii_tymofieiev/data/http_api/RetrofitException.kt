package com.google.seergii_tymofieiev.data.http_api

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
class RetrofitException (
    val mes: String?,
    val url: String?,
    val response: Response<*>?,
    val serverErrorType: ServerErrorType,
    val exception: Throwable?,
    val retrofit: Retrofit?
) : RuntimeException(mes, exception) {

    companion object {

        fun httpError(url: String, response: Response<*>, retrofit: Retrofit): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            return RetrofitException(
                message,
                url,
                response,
                ServerErrorType.HTTP,
                null,
                retrofit
            )
        }

        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(
                exception.message,
                null,
                null,
                ServerErrorType.NETWORK,
                exception,
                null
            )
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(
                exception.message,
                null,
                null,
                ServerErrorType.UNEXPECTED,
                exception,
                null
            )
        }

    }

    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>): T? {
        response?.errorBody()?.let { errorBody ->
            retrofit?.let { retrofit ->
                val converter: Converter<ResponseBody, T> =
                    retrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
                return converter.convert(errorBody)
            }
        }
        return null
    }

    enum class ServerErrorType {
        NETWORK,
        HTTP,
        UNEXPECTED
    }
}
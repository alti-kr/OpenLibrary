package com.google.seergii_tymofieiev.data.repository

import io.reactivex.Observable

/**
 * Created by Sergii Tymofieiev on 02.07.2020
 */
interface Repository  {
    fun getObservableError(): Observable<Throwable>
}
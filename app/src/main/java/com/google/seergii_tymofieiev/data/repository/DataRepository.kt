package com.google.seergii_tymofieiev.data.repository

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * Created by Sergii Tymofieiev on 02.07.2020
 */
abstract class DataRepository :     Repository {
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected val processScheduler: Scheduler = Schedulers.io()
    protected val resultScheduler: Scheduler = AndroidSchedulers.mainThread()
    protected val exceptionSubject: PublishSubject<Throwable> = PublishSubject.create()
    val error: Observable<Throwable> = exceptionSubject.hide()
    override fun getObservableError(): Observable<Throwable> {
        return error
    }

}
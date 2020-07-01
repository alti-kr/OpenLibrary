package com.google.seergii_tymofieiev.presentation.base

import com.google.seergii_tymofieiev.presentation.navigation.FlowRouter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
open class BasePresenterError<V : BaseViewError>(protected open var mFlowRouter: FlowRouter) :
    BasePresenter<V>() {


    open fun setupDataSource(
        successDisposable: Disposable,
        errorObservable: Observable<Throwable>
    ) {
        setupDataSource(successDisposable, getDisposableError(errorObservable))
    }

    open fun getDisposableError(errorObservable: Observable<Throwable>): Disposable {
        return errorObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleRequestError)
    }
    private fun handleRequestError(error: Throwable) {

    }
}
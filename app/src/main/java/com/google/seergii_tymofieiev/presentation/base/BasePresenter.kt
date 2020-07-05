package com.google.seergii_tymofieiev.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
open class BasePresenter<V : MvpView> : MvpPresenter<V>() {
    private val disposables = CompositeDisposable()
    override fun onDestroy() {
        unsubscribeDisposables()
    }

    open fun unsubscribeDisposables() {
        disposables.clear()
    }

    open fun setupDataSource(successDisposable: Disposable, errorDisposable: Disposable) {
        disposables.add(successDisposable)
        disposables.add(errorDisposable)
    }

    open fun unsubscribeDisposable(successDisposable: Disposable?) {
        if (successDisposable != null) {
            disposables.remove(successDisposable)
        }
    }
}
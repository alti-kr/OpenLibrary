package com.google.seergii_tymofieiev.presentation.ui.open_library.book_details

import android.text.TextUtils
import com.arellomobile.mvp.InjectViewState
import com.google.seergii_tymofieiev.R

import com.google.seergii_tymofieiev.data.model.BookDetail
import com.google.seergii_tymofieiev.data.repository.BooksRepository
import com.google.seergii_tymofieiev.presentation.base.BasePresenterError
import com.google.seergii_tymofieiev.presentation.di.scope.PerFragment
import com.google.seergii_tymofieiev.presentation.navigation.FlowRouter
import com.google.seergii_tymofieiev.utils.Utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 03.07.2020
 */
@PerFragment
@InjectViewState
class BookDetailsPresenter @Inject constructor(
    mFlowRouter: FlowRouter,
    private val booksRepository: BooksRepository
) : BasePresenterError<BookDetailsView>(mFlowRouter) {
    var bookId: String? = ""
    var bookName: String? = ""


    fun getToolbarText(): String {
        return bookName!!
    }

    fun closeWindowThis() {
        mFlowRouter.exit()
    }

    fun fetchBooksData() {
        if (!TextUtils.isEmpty(bookId)) {
            val successDisposable = booksRepository.getObservableRepositoriesBooksDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleBooksList)
            setupDataSource(successDisposable, booksRepository.getObservableError())
            booksRepository.fetchBookDetails(bookId!!)
        }
    }

    private fun handleBooksList(data: BookDetail) {
        unsubscribeDisposables()
        viewState.toggleProgressBar(false)
        viewState.toggleContentContainer(true)
        val publishDate = String.format(
            Utils.getStringById(R.string.details_publish_date),
            if (TextUtils.isEmpty(data.publishDate)) "" else data.publishDate
        )
        val pagesCount = String.format(
            Utils.getStringById(R.string.details_pages),
            data.pagesCount
        )

        viewState.setData(
            title = data.title,
            subTitle = data.subtitle,
            coverUrl = data.coverUrl,
            datePublish = publishDate,
            countPages = pagesCount
        )
    }
}
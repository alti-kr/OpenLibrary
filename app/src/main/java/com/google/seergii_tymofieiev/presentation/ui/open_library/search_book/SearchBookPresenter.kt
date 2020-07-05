package com.google.seergii_tymofieiev.presentation.ui.open_library.search_book

import com.arellomobile.mvp.InjectViewState
import com.google.seergii_tymofieiev.R
import com.google.seergii_tymofieiev.data.model.Book
import com.google.seergii_tymofieiev.data.repository.BooksRepository
import com.google.seergii_tymofieiev.presentation.base.BasePresenterError
import com.google.seergii_tymofieiev.presentation.di.scope.PerFragment
import com.google.seergii_tymofieiev.presentation.navigation.FlowRouter
import com.google.seergii_tymofieiev.presentation.navigation.Screens
import com.google.seergii_tymofieiev.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@PerFragment
@InjectViewState
class SearchBookPresenter  @Inject constructor(
    mFlowRouter: FlowRouter,
    private val booksRepository: BooksRepository
) : BasePresenterError<SearchBookView>(mFlowRouter) {
    private var searchText = ""
    fun fetchList(value: String) {
        searchText = value
        viewState.toggleLoadingView(true)
        val successDisposable = booksRepository.getObservableRepositoriesBooksList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleBooksList)
        setupDataSource(successDisposable, booksRepository.getObservableError())

        booksRepository.fetchBookListByName(value)
    }
    private fun handleBooksList(data: List<Book>){
        unsubscribeDisposables()
        viewState.toggleLoadingView(false)
        if(data.isNotEmpty()) {
            mFlowRouter.navigateTo(Screens.BooksListScreen(ArrayList(data), searchText))
        }else{
            viewState.showError(Utils.getStringById(R.string.error_empty))
        }
    }
    fun closeWindowThis() {
        mFlowRouter.exit()
    }
}
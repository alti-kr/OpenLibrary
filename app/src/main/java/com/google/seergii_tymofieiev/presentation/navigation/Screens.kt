package com.google.seergii_tymofieiev.presentation.navigation

import com.google.seergii_tymofieiev.data.model.Book
import com.google.seergii_tymofieiev.presentation.ui.open_library.LibraryFlowFragment
import com.google.seergii_tymofieiev.presentation.ui.open_library.book_details.BookDetailsFragment
import com.google.seergii_tymofieiev.presentation.ui.open_library.books_list.BooksListFragment
import com.google.seergii_tymofieiev.presentation.ui.open_library.search_book.SearchBookFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
object Screens {
    object LibraryFlowScreen : SupportAppScreen() {
        override fun getFragment() = LibraryFlowFragment.newInstance()
    }

    object SearchBookScreen : SupportAppScreen() {
        override fun getFragment() = SearchBookFragment.newInstance()
    }
    data class BooksListScreen(val flowData: ArrayList<Book>, val text: String): SupportAppScreen() {
        override fun getFragment() = BooksListFragment.newInstance(flowData, text)
    }
    data class BookDetailsScreen(val bookId: String, val text: String): SupportAppScreen() {
        override fun getFragment() = BookDetailsFragment.newInstance(bookId, text)
    }

}
package com.google.seergii_tymofieiev.presentation.ui.open_library.di

import com.google.seergii_tymofieiev.presentation.di.scope.PerFragment
import com.google.seergii_tymofieiev.presentation.ui.open_library.book_details.BookDetailsFragment
import com.google.seergii_tymofieiev.presentation.ui.open_library.books_list.BooksListFragment
import com.google.seergii_tymofieiev.presentation.ui.open_library.search_book.SearchBookFragment
import dagger.Subcomponent

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@PerFragment
@Subcomponent
interface LibraryFlowFragmentsComponent {
    fun inject(searchBookFragment: SearchBookFragment)
    fun inject(booksListFragment: BooksListFragment)
    fun inject(bookDetailsFragment: BookDetailsFragment)
    companion object {
        var libraryFlowFragmentsComponent: LibraryFlowFragmentsComponent? = null
    }
}
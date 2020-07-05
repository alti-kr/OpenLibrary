package com.google.seergii_tymofieiev.data.repository

import com.google.seergii_tymofieiev.data.model.Book
import com.google.seergii_tymofieiev.data.model.BookDetail
import io.reactivex.Observable

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
interface BooksRepository: Repository {
    fun fetchBookListByName(value: String)
    fun fetchBookDetails(value: String)
    fun getObservableRepositoriesBooksList(): Observable<List<Book>>
    fun getObservableRepositoriesBooksDetail(): Observable<BookDetail>
}
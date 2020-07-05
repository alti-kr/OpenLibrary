package com.google.seergii_tymofieiev.data.net

import com.google.seergii_tymofieiev.data.model.BooksList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
interface BooksApi {
    @GET("search.json/")
    fun fetchBooksList(@Query("q") value: String): Observable<BooksList>
    @GET("/api/books/")
    fun fetchBookDetails(@Query("bibkeys") value: String, @Query("format") format: String = "json", @Query("jscmd") jscmd:String = "data") : Observable<okhttp3.ResponseBody>
}
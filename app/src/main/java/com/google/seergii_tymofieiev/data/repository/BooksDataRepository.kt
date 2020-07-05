package com.google.seergii_tymofieiev.data.repository

import com.google.seergii_tymofieiev.data.model.Book
import com.google.seergii_tymofieiev.data.model.BookDetail
import com.google.seergii_tymofieiev.data.model.BooksList
import com.google.seergii_tymofieiev.data.net.BooksApi
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import okhttp3.ResponseBody
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
class BooksDataRepository @Inject constructor(
    private val api: BooksApi
) : DataRepository(), BooksRepository {
    private val repositoriesSubjectBooksList: PublishSubject<List<Book>> =
        PublishSubject.create()

    private val repositoriesBooksList: Observable<List<Book>> =
        repositoriesSubjectBooksList.hide()

    override fun getObservableRepositoriesBooksList(): Observable<List<Book>> {
        return repositoriesBooksList
    }

    private val repositoriesSubjectBookDetail: PublishSubject<BookDetail> =
        PublishSubject.create()

    private val repositoriesBookDetail: Observable<BookDetail> =
        repositoriesSubjectBookDetail.hide()

    override fun getObservableRepositoriesBooksDetail(): Observable<BookDetail> {
        return repositoriesBookDetail
    }

    override fun fetchBookListByName(value: String) {
        val call = api.fetchBooksList(value)
        val disposable = call
            .subscribeOn(processScheduler)
            .observeOn(resultScheduler)
            .map(this::mapResponseToList)
            .subscribe(
                repositoriesSubjectBooksList::onNext,
                exceptionSubject::onNext
            )
        compositeDisposable.add(disposable)

    }

    fun mapResponseToList(res: BooksList): List<Book> {
        // If necessary, put here mapping rules
        return res.docs
    }

    override fun fetchBookDetails(value: String) {
        val call = api.fetchBookDetails(value)
        val disposable = call
            .subscribeOn(processScheduler)
            .observeOn(resultScheduler)
            .map(this::mapResponseToDetail)
            .subscribe(
                repositoriesSubjectBookDetail::onNext,
                exceptionSubject::onNext
            )
        compositeDisposable.add(disposable)
    }

    private fun mapResponseToDetail(data: ResponseBody): BookDetail {
        val response = JSONObject(data.string())
        var tTitle = ""
        var tSubTitle = ""
        var tPages = 0
        var tPublishDate = ""
        var tCoverUrl = ""
        response.keys().forEach {
            val tObject = response.getJSONObject(it)
            if (tObject.has("title")) {
                tTitle = tObject.getString("title")
            }
            if (tObject.has("subtitle")) {
                tSubTitle = tObject.getString("subtitle")
            }
            if (tObject.has("number_of_pages")) {
                tPages = tObject.getInt("number_of_pages")

            }
            if (tObject.has("publish_date")) {
                tPublishDate = tObject.getString("publish_date")
            }
            if (tObject.has("cover")) {
                val covers = tObject.getJSONObject("cover")
                when {
                    covers.has("large") -> {
                        tCoverUrl = covers.getString("large")
                    }
                    covers.has("medium") -> {
                        tCoverUrl = covers.getString("medium")
                    }
                    covers.has("small") -> {
                        tCoverUrl = covers.getString("small")
                    }
                }
            }
        }
        return BookDetail(tTitle, tSubTitle, tCoverUrl, tPages, tPublishDate)
    }
}
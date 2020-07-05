package com.google.seergii_tymofieiev.data.model


/**
 * Created by Sergii Tymofieiev on 03.07.2020
 */
data class BookDetail(
    val title: String,
    val subtitle: String,
    val coverUrl: String,
    val pagesCount: Int,
    val publishDate: String?
)
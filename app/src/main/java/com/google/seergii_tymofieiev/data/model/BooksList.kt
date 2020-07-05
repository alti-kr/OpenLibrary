package com.google.seergii_tymofieiev.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Sergii Tymofieiev on 02.07.2020
 */
data class BooksList(
    @SerializedName("docs") val docs: List<Book>
)

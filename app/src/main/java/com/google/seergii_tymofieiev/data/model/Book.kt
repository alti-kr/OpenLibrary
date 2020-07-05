package com.google.seergii_tymofieiev.data.model

import android.os.Parcelable
import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@Parcelize
data class Book(
    @SerializedName("cover_i") val coverId: Int,
    @SerializedName("title_suggest") val title: String,
    @SerializedName("author_name") val authorNameList: List<String>?,
    @SerializedName("cover_edition_key") val coverEditionKey : String?,
    @SerializedName("edition_key") val editionKey : List<String>?
): Parcelable {
    fun getCoverUrl(): String? {
        return "http://covers.openlibrary.org/b/olid/"+getOpenLibraryId()+"-M.jpg?default=false"
    }
    fun getOpenLibraryId(): String {
        return if(!TextUtils.isEmpty(coverEditionKey)) coverEditionKey!! else if(editionKey?.size?:0 >0) editionKey!![0] else ""
    }
    fun getAuthorName():String{
        return if(authorNameList != null && authorNameList.isNotEmpty()) authorNameList[0] else ""
    }
}

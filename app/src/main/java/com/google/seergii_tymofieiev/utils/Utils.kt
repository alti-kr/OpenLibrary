package com.google.seergii_tymofieiev.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.seergii_tymofieiev.App

/**
 * Created by Sergii Tymofieiev on 02.07.2020
 */
object Utils {
    fun getDrawable(drawableId: Int): Drawable? = ContextCompat.getDrawable(App.getContext(), drawableId)
    private fun getStringById(context: Context?, id: Int): String {
        return context?.resources?.getString(id)
            ?: App.getContext().resources.getString(id)
    }

    fun getStringById(id: Int) =
        getStringById(App.getContext(), id)

}
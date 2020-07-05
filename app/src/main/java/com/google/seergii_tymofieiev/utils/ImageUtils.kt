package com.google.seergii_tymofieiev.utils

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

/**
 * Created by Sergii Tymofieiev on 03.07.2020
 */
object ImageUtils {

    fun loadImage(
        imageUrl: String?,
        container: ImageView,
        transformation: Transformation?,
        resizeData: LoadImageResizeData?,
        dummyDrawable: Drawable?,
        callback: LoadImageCallback?
    ) {
        if (!TextUtils.isEmpty(imageUrl)) {
            var requestCreator =
                Picasso.get().load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE).noFade()
            if (dummyDrawable != null) {
                requestCreator.placeholder(dummyDrawable)
            }
            if (resizeData != null) {
                requestCreator.resize(resizeData.width, resizeData.height)
            }
            if (transformation != null) {
                requestCreator.transform(transformation)
            }
            requestCreator.into(container, object : Callback {
                override fun onSuccess() {
                    callback?.onSuccess()
                }

                override fun onError(e: Exception?) {
                    requestCreator = Picasso.get().load(imageUrl).noFade()
                    if (dummyDrawable != null) {
                        requestCreator.placeholder(dummyDrawable)
                        requestCreator.error(dummyDrawable)
                    }
                    if (transformation != null) {
                        requestCreator.transform(transformation)
                    }
                    if (resizeData != null) {
                        requestCreator.resize(resizeData.width, resizeData.height)
                    }
                    requestCreator.into(
                        container,
                        object : Callback {
                            override fun onSuccess() {
                                callback?.onSuccess()
                            }

                            override fun onError(e: Exception?) {
                                Log.d("My_log", "Picasso = " + e?.toString())
                            }
                        })
                }
            })
        }
    }

    data class LoadImageResizeData(val width: Int, val height: Int)

    interface LoadImageCallback {
        fun onSuccess()
    }
}
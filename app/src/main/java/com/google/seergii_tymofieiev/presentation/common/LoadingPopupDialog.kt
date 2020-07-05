package com.google.seergii_tymofieiev.presentation.common

import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.google.seergii_tymofieiev.R

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
class LoadingPopupDialog (inflater: LayoutInflater) : PopupWindow() {

    init {
        contentView = inflater.inflate(R.layout.loading_view, null)
        width = LinearLayout.LayoutParams.MATCH_PARENT
        height = LinearLayout.LayoutParams.MATCH_PARENT
        isFocusable = false
        inputMethodMode = INPUT_METHOD_NOT_NEEDED
    }

    fun show() {
        showAtLocation(contentView, Gravity.CENTER, 0, 0)
    }

    fun hide() {
        dismiss()
    }
}
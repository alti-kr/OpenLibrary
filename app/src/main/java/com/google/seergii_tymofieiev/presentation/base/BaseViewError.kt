package com.google.seergii_tymofieiev.presentation.base

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
interface BaseViewError : BaseView {
    fun showHttpError(error: String?)
}
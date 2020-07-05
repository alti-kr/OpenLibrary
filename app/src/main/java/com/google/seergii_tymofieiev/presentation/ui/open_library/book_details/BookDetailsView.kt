package com.google.seergii_tymofieiev.presentation.ui.open_library.book_details

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.google.seergii_tymofieiev.presentation.base.BaseViewError

/**
 * Created by Sergii Tymofieiev on 03.07.2020
 */
@StateStrategyType(SkipStrategy::class)
interface BookDetailsView : BaseViewError {
    fun toggleProgressBar(onOff: Boolean)
    fun toggleContentContainer(onOff: Boolean)
    fun setData(title:String, subTitle : String, coverUrl: String, datePublish: String, countPages: String)
}
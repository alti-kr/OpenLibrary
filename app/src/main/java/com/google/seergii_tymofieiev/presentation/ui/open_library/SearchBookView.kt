package com.google.seergii_tymofieiev.presentation.ui.open_library

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.google.seergii_tymofieiev.presentation.base.BaseViewError

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@StateStrategyType(SkipStrategy::class)
interface SearchBookView: BaseViewError {
}
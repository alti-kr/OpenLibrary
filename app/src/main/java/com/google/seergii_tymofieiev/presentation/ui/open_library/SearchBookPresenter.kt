package com.google.seergii_tymofieiev.presentation.ui.open_library

import com.arellomobile.mvp.InjectViewState
import com.google.seergii_tymofieiev.presentation.base.BasePresenterError
import com.google.seergii_tymofieiev.presentation.di.scope.PerFragment
import com.google.seergii_tymofieiev.presentation.navigation.FlowRouter
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@PerFragment
@InjectViewState
class SearchBookPresenter  @Inject constructor(
    mFlowRouter: FlowRouter
) : BasePresenterError<SearchBookView>(mFlowRouter) {
}
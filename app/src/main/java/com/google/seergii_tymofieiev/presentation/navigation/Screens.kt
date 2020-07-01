package com.google.seergii_tymofieiev.presentation.navigation

import androidx.fragment.app.Fragment
import com.google.seergii_tymofieiev.presentation.ui.open_library.LibraryFlowFragment
import com.google.seergii_tymofieiev.presentation.ui.open_library.SearchBookFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
object Screens {
    object LibraryFlowScreen : SupportAppScreen() {
        override fun getFragment() = LibraryFlowFragment.newInstance()
    }

    object SearchBookScreen : SupportAppScreen() {
        override fun getFragment() = SearchBookFragment.newInstance()

    }
}
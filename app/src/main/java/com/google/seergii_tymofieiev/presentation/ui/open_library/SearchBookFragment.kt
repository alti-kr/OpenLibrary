package com.google.seergii_tymofieiev.presentation.ui.open_library

import com.google.seergii_tymofieiev.presentation.base.BaseFragmentError

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
class SearchBookFragment : BaseFragmentError(), SearchBookView {
    companion object{
        fun newInstance(): SearchBookFragment =
            SearchBookFragment()
    }
}
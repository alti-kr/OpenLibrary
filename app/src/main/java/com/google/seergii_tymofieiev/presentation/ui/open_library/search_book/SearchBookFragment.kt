package com.google.seergii_tymofieiev.presentation.ui.open_library.search_book

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.seergii_tymofieiev.presentation.base.BaseFragmentError
import com.google.seergii_tymofieiev.presentation.ui.open_library.di.LibraryFlowFragmentsComponent.Companion.libraryFlowFragmentsComponent
import javax.inject.Inject
import com.google.seergii_tymofieiev.R
import com.google.seergii_tymofieiev.utils.safeOnClickListener
import kotlinx.android.synthetic.main.search_book_view.*

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
class SearchBookFragment : BaseFragmentError(),
    SearchBookView {
    companion object {
        fun newInstance(): SearchBookFragment =
            SearchBookFragment()
    }

    override val layoutRes: Int = R.layout.search_book_view

    override fun injectDaggerDependency() {
        libraryFlowFragmentsComponent?.inject(this)
    }

    @Inject
    lateinit var inputMethodManager: InputMethodManager

    @Inject
    @InjectPresenter
    lateinit var mPresenter: SearchBookPresenter

    @ProvidePresenter
    fun providePresenter() = mPresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_search.apply {
            safeOnClickListener {
                view.let {
                    inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
                }
                mPresenter.fetchList(textInputLayout.editText?.text.toString())
            }
        }
    }

    override fun onBackPressed() {
        mPresenter.closeWindowThis()

    }
}
package com.google.seergii_tymofieiev.presentation.ui.open_library.books_list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.seergii_tymofieiev.App
import com.google.seergii_tymofieiev.R
import com.google.seergii_tymofieiev.data.model.Book
import com.google.seergii_tymofieiev.presentation.base.BaseFragmentError
import com.google.seergii_tymofieiev.presentation.ui.open_library.di.LibraryFlowFragmentsComponent.Companion.libraryFlowFragmentsComponent
import com.google.seergii_tymofieiev.utils.Utils
import kotlinx.android.synthetic.main.books_list_view.*
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 02.07.2020
 */
class BooksListFragment : BaseFragmentError(), BooksListView {

    companion object {
        const val paramList = "list_key"
        const val paramText = "text_key"

        fun newInstance(flowData: ArrayList<Book>, text: String): BooksListFragment =
            BooksListFragment().apply {
                arguments = bundleOf(
                    paramList to flowData,
                    paramText to text
                )
            }
    }


    @Inject
    @InjectPresenter
    lateinit var mPresenter: BooksListPresenter

    @ProvidePresenter
    fun getPresenter(): BooksListPresenter {
        return mPresenter.apply {
            arguments?.let { args ->
                flowData = args.getParcelableArrayList<Book>(paramList) as ArrayList<Book>
                searchText = args.getString(paramText)
            }
        }
    }

    override val layoutRes: Int = R.layout.books_list_view

    override fun injectDaggerDependency() {
        libraryFlowFragmentsComponent?.inject(this)
    }

    private lateinit var groupList: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            navigationIcon = Utils.getDrawable(R.drawable.ic_back)
            setNavigationOnClickListener {
                mPresenter.closeWindowThis()
            }

        }
        tool_text.text = mPresenter.getToolbarText()

        groupList = view.findViewById(R.id.group_list)
        val mLayoutManager =
            LinearLayoutManager(App.getContext(), LinearLayoutManager.VERTICAL, false)
        groupList.apply {
            adapter = mPresenter.getAdapter()
            layoutManager = mLayoutManager
            addItemDecoration(VerticalDecoration(10))

        }
    }

    override fun onBackPressed() {
        mPresenter.closeWindowThis()
    }
}
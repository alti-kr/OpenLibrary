package com.google.seergii_tymofieiev.presentation.ui.open_library.books_list

import androidx.recyclerview.widget.RecyclerView
import com.google.seergii_tymofieiev.data.model.Book
import com.google.seergii_tymofieiev.presentation.base.BasePresenterError
import com.google.seergii_tymofieiev.presentation.navigation.FlowRouter
import com.google.seergii_tymofieiev.presentation.navigation.Screens
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 02.07.2020
 */
class BooksListPresenter @Inject constructor(
    mFlowRouter: FlowRouter
) : BasePresenterError<BooksListView>(mFlowRouter) {
    var flowData: ArrayList<Book> = ArrayList()
    var searchText: String? = ""
    private var adapter: BooksListAdapter? = null
    fun getAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        if (adapter == null) {
            adapter = BooksListAdapter()
            adapter!!.setData(flowData, object : BooksListAdapter.OnItemsListClickListener{
                override fun onItemClick(item: Book) {
                    mFlowRouter.navigateTo(Screens.BookDetailsScreen(item.getOpenLibraryId(), item.title))
                }

            })
        }
        return adapter!!
    }

    fun getToolbarText(): String {
        return searchText!!
    }

    fun closeWindowThis() {
        mFlowRouter.exit()
    }

}
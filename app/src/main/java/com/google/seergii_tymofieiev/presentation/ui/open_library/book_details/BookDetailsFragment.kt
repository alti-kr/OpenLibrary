package com.google.seergii_tymofieiev.presentation.ui.open_library.book_details

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.seergii_tymofieiev.R
import com.google.seergii_tymofieiev.presentation.base.BaseFragmentError
import com.google.seergii_tymofieiev.presentation.ui.open_library.di.LibraryFlowFragmentsComponent.Companion.libraryFlowFragmentsComponent
import com.google.seergii_tymofieiev.utils.ImageUtils
import com.google.seergii_tymofieiev.utils.Utils
import kotlinx.android.synthetic.main.book_details_view.*
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 03.07.2020
 */
class BookDetailsFragment : BaseFragmentError(), BookDetailsView {

    companion object {
        const val paramKey = "list_key"
        const val paramText = "text_key"

        fun newInstance(bookId: String, text: String): BookDetailsFragment =
            BookDetailsFragment().apply {
                arguments = bundleOf(
                    paramKey to bookId,
                    paramText to text
                )
            }
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var contentContainer: ScrollView
    private lateinit var coverImageView: ImageView
    override val layoutRes: Int = R.layout.book_details_view

    @Inject
    @InjectPresenter
    lateinit var mPresenter: BookDetailsPresenter


    @ProvidePresenter
    fun getPresenter(): BookDetailsPresenter {
        return mPresenter.apply {
            arguments?.let { args ->
                bookId = args.getString(paramKey)
                bookName = args.getString(paramText)
            }
        }
    }


    override fun injectDaggerDependency() {
        libraryFlowFragmentsComponent?.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            navigationIcon = Utils.getDrawable(R.drawable.ic_back)
            setNavigationOnClickListener {
                mPresenter.closeWindowThis()
            }

        }
        tool_text.text = mPresenter.getToolbarText()
        progressBar = view.findViewById(R.id.progressBar)
        contentContainer = view.findViewById(R.id.content_container)
        contentContainer.visibility = GONE
        coverImageView = view.findViewById(R.id.book_image)


    }

    override fun onBackPressed() {
        mPresenter.closeWindowThis()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.fetchBooksData()
    }

    override fun toggleProgressBar(onOff: Boolean) {
        progressBar.visibility = if (onOff) VISIBLE else GONE
    }

    override fun toggleContentContainer(onOff: Boolean) {
        contentContainer.visibility = if (onOff) VISIBLE else GONE
    }

    override fun setData(
        title: String,
        subTitle: String,
        coverUrl: String,
        datePublish: String,
        countPages: String
    ) {
        book_title.text = title
        book_subtitle.text = subTitle
        ImageUtils.loadImage(
            coverUrl,
            coverImageView,
            null,
            null,
            Utils.getDrawable(R.drawable.ic_dummy),
            null
        )
        book_publish.text = datePublish
        book_pages.text = countPages
    }
}
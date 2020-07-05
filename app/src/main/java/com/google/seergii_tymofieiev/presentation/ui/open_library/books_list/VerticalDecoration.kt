package com.google.seergii_tymofieiev.presentation.ui.open_library.books_list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Sergii Tymofieiev on 02.07.2020
 */
class VerticalDecoration(private val dividerThickness: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)
        outRect.left = dividerThickness
        outRect.right = dividerThickness
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }
        if (itemPosition != 0) {
            outRect.top = dividerThickness
        }
    }

}
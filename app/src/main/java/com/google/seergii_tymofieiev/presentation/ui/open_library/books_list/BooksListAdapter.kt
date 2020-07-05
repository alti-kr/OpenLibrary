package com.google.seergii_tymofieiev.presentation.ui.open_library.books_list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.seergii_tymofieiev.R
import com.google.seergii_tymofieiev.data.model.Book
import com.google.seergii_tymofieiev.utils.ImageUtils
import com.google.seergii_tymofieiev.utils.Utils
import com.google.seergii_tymofieiev.utils.safeOnClickListener

/**
 * Created by Sergii Tymofieiev on 02.07.2020
 */
class BooksListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemsList: ArrayList<Book> = ArrayList()
    private var onItemsListClickListener:OnItemsListClickListener? = null
    fun setData(itemsList: ArrayList<Book>, onItemsListClickListener: OnItemsListClickListener?) {
        this.itemsList = itemsList
        this.onItemsListClickListener = onItemsListClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.book_list_item_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holderCommon: RecyclerView.ViewHolder, position: Int) {
        val holder: ItemViewHolder = holderCommon as ItemViewHolder
        val item = itemsList[position]
        holder.sourcePosition = position
        holder.authorView.text = item.getAuthorName()
        holder.descView.text = item.title
        holder.titleView.text = item.title
        ImageUtils.loadImage(
            imageUrl = item.getCoverUrl(),
            container = holder.imageView,
            transformation = null,
            resizeData = null,
            dummyDrawable = Utils.getDrawable(R.drawable.ic_dummy),
            callback = null
        )
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sourcePosition = -1
        private var mView = itemView
        var imageView: ImageView = itemView.findViewById(R.id.book_image)
        var titleView: TextView = itemView.findViewById(R.id.book_title)
        var authorView: TextView = itemView.findViewById(R.id.book_author)
        var descView: TextView = itemView.findViewById(R.id.book_desc)
        init {
            mView.apply {
                safeOnClickListener {
                    onItemsListClickListener?.onItemClick(itemsList[sourcePosition])
                }
            }
        }
    }

 interface  OnItemsListClickListener{
     fun onItemClick(item: Book)
 }
}
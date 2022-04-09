package com.patrick.giphyviewer.util

import androidx.recyclerview.widget.DiffUtil
import com.patrick.giphyviewer.data.model.Data

class BaseDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}

object DataComparator : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean = oldItem == newItem
}
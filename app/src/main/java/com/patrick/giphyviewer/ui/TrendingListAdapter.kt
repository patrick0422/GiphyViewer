package com.patrick.giphyviewer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.patrick.giphyviewer.data.model.Data
import com.patrick.giphyviewer.databinding.ItemTrendingGifBinding
import com.patrick.giphyviewer.util.BaseDiffUtil

class TrendingListAdapter: RecyclerView.Adapter<TrendingViewHolder>() {
    var trendingList = listOf<Data>()

    fun setData(newList: List<Data>) {
        DiffUtil.calculateDiff(BaseDiffUtil(trendingList, newList)).dispatchUpdatesTo(this)
        trendingList = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder = TrendingViewHolder.from(parent)
    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) = holder.bind(trendingList[position])
    override fun getItemCount(): Int = trendingList.size
}

class TrendingViewHolder(private val binding: ItemTrendingGifBinding): RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): TrendingViewHolder =
            TrendingViewHolder(ItemTrendingGifBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    fun bind(data: Data): Unit = with(binding) {
        Glide
            .with(this.root)
            .load(data.images.original.url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}

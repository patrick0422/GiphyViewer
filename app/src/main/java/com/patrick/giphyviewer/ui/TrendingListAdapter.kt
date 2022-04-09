package com.patrick.giphyviewer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.patrick.giphyviewer.data.model.Data
import com.patrick.giphyviewer.databinding.ItemTrendingGifBinding
import com.patrick.giphyviewer.util.BaseDiffUtil

class TrendingListAdapter(diffCallback: DiffUtil.ItemCallback<Data>) : PagingDataAdapter<Data, TrendingViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder = TrendingViewHolder.from(parent)
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

    fun bind(data: Data?): Unit = with(binding) {
        if (data == null) {
            Toast.makeText(root.context, "Null Occurred", Toast.LENGTH_SHORT).show()
            return
        }

        imageView.layoutParams.apply {
            width = data.images.original.width.toInt()
            height = data.images.original.height.toInt()
        }
        Glide
            .with(this.root)
            .load(data.images.original.url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(
                CircularProgressDrawable(binding.root.context).apply {
                    strokeWidth = 5f
                    centerRadius = 30f
                    start()
                }
            )
            .into(imageView)
            .clearOnDetach()
    }
}
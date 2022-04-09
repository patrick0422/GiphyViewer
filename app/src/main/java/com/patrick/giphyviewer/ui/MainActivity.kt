package com.patrick.giphyviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.patrick.giphyviewer.R
import com.patrick.giphyviewer.data.model.GiphyResponse
import com.patrick.giphyviewer.databinding.ActivityMainBinding
import com.patrick.giphyviewer.util.DataComparator
import com.patrick.giphyviewer.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    private val mainViewModel: MainViewModel by viewModels()
    private val pagingAdapter: TrendingListAdapter by lazy { TrendingListAdapter(DataComparator) }
    private val staggeredGridLayoutManager: StaggeredGridLayoutManager by lazy {
        StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL).apply {
            gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?): Unit = with(binding) {
        super.onCreate(savedInstanceState)
        lifecycleOwner = this@MainActivity
        trendingList.adapter = pagingAdapter
        trendingList.layoutManager = staggeredGridLayoutManager

        lifecycleScope.launch {
            mainViewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}
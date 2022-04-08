package com.patrick.giphyviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.patrick.giphyviewer.R
import com.patrick.giphyviewer.data.model.GiphyResponse
import com.patrick.giphyviewer.databinding.ActivityMainBinding
import com.patrick.giphyviewer.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }
    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter: TrendingListAdapter by lazy { TrendingListAdapter() }
    private val staggeredGridLayoutManager: StaggeredGridLayoutManager by lazy {
        StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL).apply {
            gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) = with(binding) {
        super.onCreate(savedInstanceState)
        lifecycleOwner = this@MainActivity
        trendingList.adapter = mAdapter
        trendingList.layoutManager = staggeredGridLayoutManager

        getTrendingGifs()

        mainViewModel.getTrendingGifsResponse.observe(this@MainActivity) {
            handleTrendingGifsResponse(
                it
            )
        }
    }

    private fun handleTrendingGifsResponse(response: NetworkResult<GiphyResponse>?) {
        when (response) {
            is NetworkResult.Success -> {
                mAdapter.setData(response.data!!.data)
            }
            is NetworkResult.Error -> {
                Toast.makeText(this, "Failed: ${response.message}", Toast.LENGTH_SHORT).show()
            }
            is NetworkResult.Loading -> {

            }
        }
    }

    private fun getTrendingGifs() = mainViewModel.getTrendingGifs()
}
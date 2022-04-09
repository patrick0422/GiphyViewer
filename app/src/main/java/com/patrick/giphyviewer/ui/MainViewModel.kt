package com.patrick.giphyviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.patrick.giphyviewer.data.GiphyPagingSource
import com.patrick.giphyviewer.data.GiphyRepository
import com.patrick.giphyviewer.data.model.GiphyResponse
import com.patrick.giphyviewer.util.Constants.GIPHY_API_KEY
import com.patrick.giphyviewer.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val giphyRepository: GiphyRepository
) : ViewModel() {
    val flow = Pager(
        PagingConfig(pageSize = 25)
    ) {
        GiphyPagingSource(giphyRepository)
    }.flow
        .cachedIn(viewModelScope)
}
package com.patrick.giphyviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _getTrendingGifsResponse: MutableLiveData<NetworkResult<GiphyResponse>> = MutableLiveData()
    val getTrendingGifsResponse: LiveData<NetworkResult<GiphyResponse>> get() = _getTrendingGifsResponse

    fun getTrendingGifs() = viewModelScope.launch {
        val queries = HashMap<String, String>()
        queries["api_key"] = GIPHY_API_KEY

        _getTrendingGifsResponse.value = try {
            val response = giphyRepository.getTrendingGifs(queries)

            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.stackTraceToString())
        }
    }

}
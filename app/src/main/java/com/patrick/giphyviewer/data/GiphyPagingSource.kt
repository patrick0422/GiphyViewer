package com.patrick.giphyviewer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.patrick.giphyviewer.data.model.Data
import com.patrick.giphyviewer.util.Constants.GIPHY_API_KEY
import java.lang.Exception
import javax.inject.Inject

class GiphyPagingSource @Inject constructor(
    private val giphyRepository: GiphyRepository
) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> = try {
        val queries = HashMap<String, String>().also {
            it["api_key"] = GIPHY_API_KEY
            it["offset"] = (params.key ?: 0).toString()
        }
        val response = giphyRepository.getTrendingGifs(queries)

        if (response.isSuccessful && response.body() != null) {
            LoadResult.Page(
                data = response.body()!!.data,
                prevKey = null,
                nextKey = response.body()!!.pagination.offset + 1
            )
        } else {
            LoadResult.Error(Exception(response.message()))
        }
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}
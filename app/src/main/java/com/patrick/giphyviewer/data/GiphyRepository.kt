package com.patrick.giphyviewer.data

import com.patrick.giphyviewer.data.model.GiphyResponse
import retrofit2.Response
import javax.inject.Inject


class GiphyRepository @Inject constructor(
    private val giphyApi: GiphyApi
) {
    suspend fun getTrendingGifs(queries: Map<String, String>): Response<GiphyResponse> = giphyApi.getTrendingGifs(queries)
}
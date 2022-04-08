package com.patrick.giphyviewer.data

import com.patrick.giphyviewer.data.model.GiphyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GiphyApi {
    @GET("gifs/trending")
    suspend fun getTrendingGifs(@QueryMap queries: Map<String, String>): Response<GiphyResponse>
}
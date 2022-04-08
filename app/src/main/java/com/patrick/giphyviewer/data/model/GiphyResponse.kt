package com.patrick.giphyviewer.data.model


import com.google.gson.annotations.SerializedName

data class GiphyResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("pagination")
    val pagination: Pagination,
    @SerializedName("meta")
    val meta: Meta
)
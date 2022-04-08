package com.patrick.giphyviewer.data.model


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("offset")
    val offset: Int
)
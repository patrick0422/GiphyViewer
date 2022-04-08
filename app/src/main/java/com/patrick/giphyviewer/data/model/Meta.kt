package com.patrick.giphyviewer.data.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("status")
    val status: Int,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("response_id")
    val responseId: String
)
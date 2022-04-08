package com.patrick.giphyviewer.data.model


import com.google.gson.annotations.SerializedName

data class Looping(
    @SerializedName("mp4_size")
    val mp4Size: String,
    @SerializedName("mp4")
    val mp4: String
)
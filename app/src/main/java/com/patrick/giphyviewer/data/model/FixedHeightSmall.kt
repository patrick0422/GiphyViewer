package com.patrick.giphyviewer.data.model


import com.google.gson.annotations.SerializedName

data class FixedHeightSmall(
    @SerializedName("height")
    val height: String,
    @SerializedName("width")
    val width: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("mp4_size")
    val mp4Size: String,
    @SerializedName("mp4")
    val mp4: String,
    @SerializedName("webp_size")
    val webpSize: String,
    @SerializedName("webp")
    val webp: String
)
package com.patrick.giphyviewer.data.model


import com.google.gson.annotations.SerializedName

data class Onclick(
    @SerializedName("url")
    val url: String
)
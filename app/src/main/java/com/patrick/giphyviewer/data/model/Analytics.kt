package com.patrick.giphyviewer.data.model


import com.google.gson.annotations.SerializedName

data class Analytics(
    @SerializedName("onload")
    val onload: Onload,
    @SerializedName("onclick")
    val onclick: Onclick,
    @SerializedName("onsent")
    val onsent: Onsent
)
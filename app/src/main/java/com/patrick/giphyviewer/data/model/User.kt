package com.patrick.giphyviewer.data.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("banner_image")
    val bannerImage: String,
    @SerializedName("banner_url")
    val bannerUrl: String,
    @SerializedName("profile_url")
    val profileUrl: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("instagram_url")
    val instagramUrl: String,
    @SerializedName("website_url")
    val websiteUrl: String,
    @SerializedName("is_verified")
    val isVerified: Boolean
)
package com.example.sneakers.data.models.home

import com.google.gson.annotations.SerializedName

data class SneakerListDto(

    @SerializedName("list") var list: ArrayList<SneakerItemDto> = arrayListOf()

)

data class SneakerItemDto(

    @SerializedName("id") var id: String? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("media") var media: ArrayList<String> = arrayListOf(),
    @SerializedName("releaseDate") var releaseDate: String? = null,
    @SerializedName("retailPrice") var retailPrice: Int? = null,
    @SerializedName("styleId") var styleId: String? = null,
    @SerializedName("shoe") var shoe: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("year") var year: Int? = null

)
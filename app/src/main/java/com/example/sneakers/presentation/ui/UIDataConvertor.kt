package com.example.sneakers.presentation.ui

import com.example.sneakers.data.models.cart.SneakerItemDao
import com.example.sneakers.data.models.home.SneakerItemDto
import com.example.sneakers.helpers.nonNullString

data class SneakerUiDto(
    val id: String,
    val title: String,
    val description: String,
    val images: List<String>,
    val price: String,
    val gender: String,
    val brand: String
)

fun SneakerItemDto.toSneakerUiDto(): SneakerUiDto {
    return SneakerUiDto(
        id = this.id.nonNullString(),
        title = this.title.nonNullString(),
        description = this.description.nonNullString(),
        images = this.media.map { it.nonNullString() },
        price = "$".plus(this.retailPrice.nonNullString()),
        gender = this.gender.nonNullString(),
        brand = this.brand.nonNullString()
    )
}
fun SneakerUiDto.toSneakerItemDao():SneakerItemDao{
    return SneakerItemDao(
        id = this.id.nonNullString(),
        title = this.title.nonNullString(),
        description = this.description.nonNullString(),
        images = this.images.map { it.nonNullString() },
        price = this.price.nonNullString(),
        gender = this.gender.nonNullString(),
        brand = this.brand.nonNullString()
    )
}

fun SneakerItemDao.toSneakerUiDto(): SneakerUiDto {
    return SneakerUiDto(
        id = this.id.nonNullString(),
        title = this.title.nonNullString(),
        description = this.description.nonNullString(),
        images = this.images.map { it.nonNullString() },
        price = this.price.nonNullString(),
        gender = this.gender.nonNullString(),
        brand = this.brand.nonNullString()
    )
}

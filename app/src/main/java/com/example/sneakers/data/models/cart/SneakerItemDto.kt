package com.example.sneakers.data.models.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SneakerItemDao(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "images")
    val images: List<String>,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "brand")
    val brand: String
)
package com.example.sneakers.data.local.localdatasourceimpl.cart

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object SneakerTypeConvertor {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
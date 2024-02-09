package com.example.sneakers.helpers

object UtilHelper {
}


fun Any?.nonNullString(): String {
    val value = this.toString()
    return if (value.contains("null")) "" else value
}
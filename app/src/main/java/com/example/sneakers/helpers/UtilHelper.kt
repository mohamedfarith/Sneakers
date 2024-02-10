package com.example.sneakers.helpers


fun Any?.nonNullString(): String {
    val value = this.toString()
    return if (value.contains("null")) "" else value
}
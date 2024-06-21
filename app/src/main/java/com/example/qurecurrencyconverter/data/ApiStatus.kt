package com.example.qurecurrencyconverter.data

sealed class UIStatus<out T> {

    data class Success<T>(val data: T): UIStatus<T>()
    data class Failure<T>(val message:String):UIStatus<T>()
    data object Loading: UIStatus<Nothing>()
}
package com.example.qurecurrencyconverter.data

sealed class ResponseStatus<T> {
    data class Success<T>(val data:T): ResponseStatus<T>()
    data class Failure<T>(val message: String): ResponseStatus<T>()
    data class HttpException<T>(val message: String, val code:Int):ResponseStatus<T>()
}

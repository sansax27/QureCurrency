package com.example.qurecurrencyconverter.data

import java.lang.Exception

object NetworkUtils {

    suspend fun <T> makeApiCall(apiCall: suspend() -> T):ResponseStatus<T> {
        return try {
            ResponseStatus.Success(apiCall.invoke())
        } catch (e:Exception) {
            ResponseStatus.Failure(e.message ?: "")
        }
    }
}
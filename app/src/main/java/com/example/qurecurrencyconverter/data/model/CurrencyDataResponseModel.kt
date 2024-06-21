package com.example.qurecurrencyconverter.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Types


@JsonClass(generateAdapter = true)
data class CurrencyDataResponseModel(@Json(name = "data") val data: Map<String, String>)

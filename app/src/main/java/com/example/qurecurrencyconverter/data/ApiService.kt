package com.example.qurecurrencyconverter.data

import com.example.qurecurrencyconverter.data.model.CurrencyDataResponseModel
import retrofit2.http.GET

interface ApiService {

    @GET("latest?apikey=fca_live_frG6mYPC4rNPso9w9eBpggCYeL4TYiT14HJ3wz9k")
    suspend fun getCurrencyData(): Map<String, Map<String, String>>
}
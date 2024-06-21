package com.example.qurecurrencyconverter.data.repository

import com.example.qurecurrencyconverter.data.dao.CurrencyConversionHistoryDao
import javax.inject.Inject

class ConversionHistoryRepository @Inject constructor(private val dao: CurrencyConversionHistoryDao) {

    fun getCurrencyConversionHistoryData() = dao.getCurrencyConversionHistory()
}
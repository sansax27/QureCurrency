package com.example.qurecurrencyconverter.data.repository

import com.example.qurecurrencyconverter.data.ApiService
import com.example.qurecurrencyconverter.data.NetworkUtils
import com.example.qurecurrencyconverter.data.ResponseStatus
import com.example.qurecurrencyconverter.data.dao.CurrencyDao
import com.example.qurecurrencyconverter.data.dao.CurrencyConversionHistoryDao
import com.example.qurecurrencyconverter.data.model.CurrencyConversionHistoryDataDBModel
import com.example.qurecurrencyconverter.data.model.CurrencyDataDBModel
import com.example.qurecurrencyconverter.data.model.CurrencyDataResponseModel
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject


class ConversionRepository @Inject constructor(private val apiService: ApiService,
                                               private val currencyDao: CurrencyDao,
                                               private val currencyConversionHistoryDao: CurrencyConversionHistoryDao) {

    fun getCurrencyData() = currencyDao.getCurrencyData()

    suspend fun fetchCurrencyData():ResponseStatus<Map<String, Map<String, String>>> {
        Timber.e("Getting Data")
        val response = NetworkUtils.makeApiCall {
            apiService.getCurrencyData()
        }
        return when(response) {
            is ResponseStatus.Success -> {
                val dbDataList = mutableListOf<CurrencyDataDBModel>()

                for (item in response.data["data"]!!.entries) {
                    dbDataList.add(CurrencyDataDBModel(item.key, item.value.toDouble()))
                }
                currencyDao.insertCurrencyData(dbDataList)
                ResponseStatus.Success(response.data)
            }
            is ResponseStatus.Failure -> {
                ResponseStatus.Failure(response.message)
            }
            else -> {
                ResponseStatus.Failure("")
            }
        }
    }

    suspend fun saveCurrencyHistoryDao(currencyConversionHistoryDataDBModel:
                                         CurrencyConversionHistoryDataDBModel) =
        currencyConversionHistoryDao.insertCurrencyConversionHistoryData(currencyConversionHistoryDataDBModel)

}
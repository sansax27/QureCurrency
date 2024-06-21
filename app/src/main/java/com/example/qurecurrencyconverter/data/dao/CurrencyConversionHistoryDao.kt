package com.example.qurecurrencyconverter.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qurecurrencyconverter.data.model.CurrencyConversionHistoryDataDBModel
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrencyConversionHistoryDao {

    @Query("select * from CurrencyConversionHistoryData")
    fun getCurrencyConversionHistory(): Flow<List<CurrencyConversionHistoryDataDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyConversionHistoryData(currencyConversionHistoryDataDBModel:
                                                        CurrencyConversionHistoryDataDBModel)
}
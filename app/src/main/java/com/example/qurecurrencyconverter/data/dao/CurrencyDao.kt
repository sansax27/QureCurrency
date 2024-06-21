package com.example.qurecurrencyconverter.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qurecurrencyconverter.data.model.CurrencyDataDBModel
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrencyDao {


    @Query("select * from CurrencyData")
    fun getCurrencyData():Flow<List<CurrencyDataDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyData(currencyDataDBModelList: List<CurrencyDataDBModel>)

}
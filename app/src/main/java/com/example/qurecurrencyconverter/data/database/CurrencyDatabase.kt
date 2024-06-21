package com.example.qurecurrencyconverter.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.qurecurrencyconverter.data.dao.CurrencyDao
import com.example.qurecurrencyconverter.data.dao.CurrencyConversionHistoryDao
import com.example.qurecurrencyconverter.data.model.CurrencyConversionHistoryDataDBModel
import com.example.qurecurrencyconverter.data.model.CurrencyDataDBModel


@Database(entities = [CurrencyDataDBModel::class, CurrencyConversionHistoryDataDBModel::class], version = 1)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun getCurrencyDao() : CurrencyDao
    abstract fun getCurrencyConversionHistoryDao(): CurrencyConversionHistoryDao
}
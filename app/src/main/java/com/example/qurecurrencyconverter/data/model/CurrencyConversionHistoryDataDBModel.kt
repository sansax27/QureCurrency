package com.example.qurecurrencyconverter.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "CurrencyConversionHistoryData", primaryKeys = ["curr_first", "curr_second"])
data class CurrencyConversionHistoryDataDBModel(@ColumnInfo(name = "curr_first") val firstCurr:String,
    @ColumnInfo(name = "curr_second") val secondCurr: String)

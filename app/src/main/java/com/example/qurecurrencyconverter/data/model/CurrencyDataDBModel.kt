package com.example.qurecurrencyconverter.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CurrencyData")
data class CurrencyDataDBModel(@PrimaryKey @ColumnInfo(name = "curr_symbol") val currSymbol: String,
                               @ColumnInfo(name = "curr_rate") val currRate: Double)
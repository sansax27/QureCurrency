package com.example.qurecurrencyconverter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.qurecurrencyconverter.data.repository.ConversionHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ConversionHistoryViewModel @Inject constructor(private val repository: ConversionHistoryRepository): ViewModel() {

    fun getCurrencyConversionHistoryData() = repository.getCurrencyConversionHistoryData()
}
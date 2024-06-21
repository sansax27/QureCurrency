package com.example.qurecurrencyconverter.viewmodel


import android.provider.Contacts.Intents.UI
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qurecurrencyconverter.data.ResponseStatus
import com.example.qurecurrencyconverter.data.UIStatus
import com.example.qurecurrencyconverter.data.model.CurrencyConversionHistoryDataDBModel
import com.example.qurecurrencyconverter.data.model.CurrencyDataDBModel
import com.example.qurecurrencyconverter.data.repository.ConversionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ConversionViewModel @Inject constructor(private val repository: ConversionRepository): ViewModel() {

    fun getCurrencyData() = repository.getCurrencyData()

    private val _fetchCurrencyDataMLD = MutableLiveData<UIStatus<List<CurrencyDataDBModel>>>()
    val fetchCurrencyDataLD: LiveData<UIStatus<List<CurrencyDataDBModel>>>
        get() = _fetchCurrencyDataMLD

    init {
        fetchCurrencyData()
    }

    private fun fetchCurrencyData() = viewModelScope.launch {
        _fetchCurrencyDataMLD.postValue(UIStatus.Loading)
        when(val response = repository.fetchCurrencyData()) {
            is ResponseStatus.Success -> {
                Timber.e("Success")
                _fetchCurrencyDataMLD.postValue(UIStatus.Success(emptyList()))
            }
            is ResponseStatus.HttpException -> {
                Timber.e("Failure ${response.code} ${response.message}")
                _fetchCurrencyDataMLD.postValue(UIStatus.Failure("${response.code} ${response.message}"))
            }
            is ResponseStatus.Failure -> {
                Timber.e("Failure ${response.message}")
                _fetchCurrencyDataMLD.postValue(UIStatus.Failure(response.message))
            }
        }
    }

    fun saveConversionHistoryData(currencyConversionHistoryDataDBModel: CurrencyConversionHistoryDataDBModel)
    = viewModelScope.launch {
        repository.saveCurrencyHistoryDao(currencyConversionHistoryDataDBModel)
    }
}
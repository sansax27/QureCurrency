package com.example.qurecurrencyconverter

import android.content.Context
import androidx.room.Room
import com.example.qurecurrencyconverter.data.ApiService
import com.example.qurecurrencyconverter.data.database.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context):CurrencyDatabase = Room.databaseBuilder(context,
        CurrencyDatabase::class.java, "currency_db").build()

    @Singleton
    @Provides
    fun providesCurrencyDao(currencyDatabase: CurrencyDatabase) = currencyDatabase.getCurrencyDao()


    @Provides
    @Singleton
    fun providesCurrencyConversionHistoryDao(currencyDatabase: CurrencyDatabase) = currencyDatabase.getCurrencyConversionHistoryDao()

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }).build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL).client(okHttpClient).addConverterFactory(MoshiConverterFactory.create()).build()

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)



}
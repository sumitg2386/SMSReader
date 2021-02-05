package com.nuco.smsreader.di

import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import com.nuco.smsreader.BuildConfig
import com.nuco.smsreader.app.Constants
import com.nuco.smsreader.app.Utils
import com.nuco.smsreader.network.ErrorInterceptor
import com.nuco.smsreader.network.NetworkApi
import com.nuco.smsreader.repository.FirestoreRepository
import com.nuco.smsreader.repository.SMSRepositoryImpl
import com.nuco.smsreader.viewmodel.SMSFirestoreViewModel
import com.nuco.smsreader.viewmodel.SMSViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val viewModels = module {
    viewModel { SMSViewModel(get()) }
    single { SMSFirestoreViewModel(get()) }

    single { SMSRepositoryImpl(get()) }
    single { FirestoreRepository() }
    single { Utils() }

}

val remoteDataModule = module {

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }

    single { GsonBuilder().registerTypeAdapter(Date::class.java, DateTypeAdapter()).create() }

    single { GsonConverterFactory.create(get()) }

    single {
        OkHttpClient.Builder()
                .connectTimeout(Constants.NETWORK_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(Constants.NETWORK_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(get() as HttpLoggingInterceptor)
                .addInterceptor(ErrorInterceptor())
                .build()
    }

    single {
        Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(get() as GsonConverterFactory)
                .client(get() as OkHttpClient)
                .build()
    }

    single {
        (get() as Retrofit).create(
                NetworkApi::class.java
        )
    }
}
package com.nuco.smsreader.repository

import com.nuco.smsreader.model.SMSDetail
import com.nuco.smsreader.model.SMSListResponse
import com.nuco.smsreader.model.SaveSMSDetailResponse
import com.nuco.smsreader.network.NetworkApi
import com.nuco.smsreader.network.ResultWrapper
import com.nuco.smsreader.network.safeApiCall

class SMSRepositoryImpl(private val networkApi: NetworkApi) : SMSRepository {
    override suspend fun saveSMSDetail(smsDetail: SMSDetail): ResultWrapper<SaveSMSDetailResponse> =
        safeApiCall { saveSMS(smsDetail) }

    private suspend fun saveSMS(smsDetail: SMSDetail): ResultWrapper<SaveSMSDetailResponse> {
        val response = networkApi.saveSMSDetail(smsDetail)
        return if (response.isSuccessful)
            ResultWrapper.Success(response.body()!!)
        else ResultWrapper.GenericError(response.code(), response.message())
    }

    override suspend fun getSMSList(): ResultWrapper<SMSListResponse> =
        safeApiCall { getSMS() }

    private suspend fun getSMS(): ResultWrapper<SMSListResponse> {
        val response = networkApi.getSMSList()
        return if (response.isSuccessful)
            ResultWrapper.Success(response.body()!!)
        else ResultWrapper.GenericError(response.code(), response.message())
    }
}
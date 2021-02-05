package com.nuco.smsreader.network

import com.nuco.smsreader.model.SMSDetail
import com.nuco.smsreader.model.SMSListResponse
import com.nuco.smsreader.model.SaveSMSDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkApi {
    @GET("getSMSList")
    suspend fun getSMSList(): Response<SMSListResponse>

    @POST("saveSMSDetail")
    suspend fun saveSMSDetail(smsDetail: SMSDetail): Response<SaveSMSDetailResponse>
}
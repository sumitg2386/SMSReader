package com.nuco.smsreader.model

import com.google.gson.annotations.SerializedName

data class SMSListResponse(
    @SerializedName("results")
    val smsList: List<SMSDetail>,
)

data class SaveSMSDetailResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("error")
    val error: String)
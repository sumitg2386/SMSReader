package com.nuco.smsreader.repository

import com.nuco.smsreader.model.SMSDetail
import com.nuco.smsreader.model.SMSListResponse
import com.nuco.smsreader.model.SaveSMSDetailResponse
import com.nuco.smsreader.network.ResultWrapper

/**
 * SMS Repository to fetch/save the data to server
 */
interface SMSRepository {
    /**
     * Save the SMS detail to server
     * @param smsDetail object having sms detail
     */
    suspend fun saveSMSDetail(smsDetail: SMSDetail): ResultWrapper<SaveSMSDetailResponse>

    /**
     * Gets the list of SMS stored on server
     * @return returns the collection reference to query sms list
     */
    suspend fun getSMSList(): ResultWrapper<SMSListResponse>
}
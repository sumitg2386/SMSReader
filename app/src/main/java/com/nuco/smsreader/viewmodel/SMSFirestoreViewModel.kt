package com.nuco.smsreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.nuco.smsreader.R
import com.nuco.smsreader.model.SMSDetail
import com.nuco.smsreader.model.SMSListResponse
import com.nuco.smsreader.network.ErrorObject
import com.nuco.smsreader.repository.FirestoreRepository

/**
 * Firestore ViewModel to save/fetch the SMS data from Firestore
 */
class SMSFirestoreViewModel(private val fireStoreRepository: FirestoreRepository
) : ViewModel() {
    var smsListLiveData = MutableLiveData<SMSListResponseWrapper>()

    /**
     * Gets the SMS list data from Firestore
     */
    fun getSMSList(): LiveData<SMSListResponseWrapper> {
        smsListLiveData = MutableLiveData<SMSListResponseWrapper>()
        fireStoreRepository.getSmsList().addSnapshotListener { querySnapshot, error ->
            if (querySnapshot != null) {
                val list = mutableListOf<SMSDetail>()
                for (sms in querySnapshot) {
                    val amount = sms["amount"] as String
                    val receivedTime = sms["receivedTime"] as Timestamp
                    list.add(SMSDetail(amount, receivedTime.toDate()))
                }
                smsListLiveData!!.value = SMSListResponseWrapper(response = SMSListResponse(smsList = list))
            } else {
                smsListLiveData!!.value = SMSListResponseWrapper(
                        error = ErrorObject(
                                msg = error?.message,
                                code = R.string.sms_list_fetching_failed
                        )
                )
            }
        }
        return smsListLiveData
    }

    data class SMSListResponseWrapper(
            val response: SMSListResponse? = null,
            val error: ErrorObject? = null
    )
}
package com.nuco.smsreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuco.smsreader.model.SMSListResponse
import com.nuco.smsreader.network.ErrorObject
import com.nuco.smsreader.network.ErrorResponses
import com.nuco.smsreader.network.ResultWrapper
import com.nuco.smsreader.repository.SMSRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel to save/fetch the SMS data from server.
 */
class SMSViewModel(private val repository: SMSRepository) : ViewModel() {
    private var smsListLiveData: MutableLiveData<SMSListResponseWrapper>? = null

    /**
     * Gets the SMS list data from server
     */
    fun getSMSList(): LiveData<SMSListResponseWrapper> {
        smsListLiveData = MutableLiveData()

        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) {
                repository.getSMSList()
            }) {
                is ResultWrapper.Success -> {
                    smsListLiveData!!.postValue(
                            SMSListResponseWrapper(
                                    response = result.data
                            )
                    )
                }
                is ResultWrapper.GenericError -> smsListLiveData!!.postValue(
                        SMSListResponseWrapper(
                                error = ErrorObject(
                                        result.code,
                                        result.error
                                )
                        )
                )
                is ResultWrapper.NetworkError -> smsListLiveData!!.postValue(
                        SMSListResponseWrapper(
                                error = ErrorObject(0, ErrorResponses.Network.name)
                        )
                )
                is ResultWrapper.Error -> smsListLiveData!!.postValue(
                        SMSListResponseWrapper(
                                error = ErrorObject(
                                        0,
                                        result.exception.message
                                )
                        )
                )
            }
        }
        return smsListLiveData as LiveData<SMSListResponseWrapper>
    }

    data class SMSListResponseWrapper(
            val response: SMSListResponse? = null,
            val error: ErrorObject? = null
    )
}
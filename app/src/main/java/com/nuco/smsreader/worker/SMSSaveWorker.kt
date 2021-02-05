package com.nuco.smsreader.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nuco.smsreader.app.Constants
import com.nuco.smsreader.model.SMSDetail
import com.nuco.smsreader.network.ResultWrapper
import com.nuco.smsreader.repository.SMSRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

/**
 * Worker to save the SMS detail to server
 */
class SMSSaveWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams),
    KoinComponent {
    private val tag: String? = SMSSaveWorker::class.simpleName

    private val repository: SMSRepository by inject()

    override suspend fun doWork(): Result {
        val amount =  inputData.getString(Constants.INTENT_EXTRA_SMS_AMOUNT)
        val time =  inputData.getLong(Constants.INTENT_EXTRA_SMS_TIME, 0L)
        if (amount != null && time != 0L) {
            when (withContext(Dispatchers.IO) {
                repository.saveSMSDetail(SMSDetail(amount, Date(time)))
            }) {
                is ResultWrapper.Success -> {
                    return Result.success()
                }
                is ResultWrapper.GenericError -> {
                    return Result.success()
                }
                is ResultWrapper.NetworkError -> {
                    return Result.success()
                }
                is ResultWrapper.Error -> {
                    return Result.success()
                }
            }
        }
        return Result.success()
    }
}
package com.nuco.smsreader.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nuco.smsreader.app.Constants
import com.nuco.smsreader.repository.FirestoreRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

/**
 * Worker to save the SMS detail to Firestore
 */
class SMSFirestoreSaveWorker(context: Context, workerParams: WorkerParameters) :
        CoroutineWorker(context, workerParams),
        KoinComponent {

    private val firestoreRepository: FirestoreRepository by inject()

    override suspend fun doWork(): Result {
        val amount = inputData.getString(Constants.INTENT_EXTRA_SMS_AMOUNT)
        val time = inputData.getLong(Constants.INTENT_EXTRA_SMS_TIME, 0L)
        if (amount != null && time != 0L) {
            firestoreRepository.saveSMSDetail(amount, Date(time)).addOnCompleteListener {
            }
        }
        return Result.success()
    }
}
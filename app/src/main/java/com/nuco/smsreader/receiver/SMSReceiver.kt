package com.nuco.smsreader.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.nuco.smsreader.app.Constants
import com.nuco.smsreader.app.Utils
import com.nuco.smsreader.worker.SMSFirestoreSaveWorker
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * SMS receiver to receive all sms messages
 */
class SMSReceiver : BroadcastReceiver(), KoinComponent {
    private val tag: String? = SMSReceiver::class.simpleName
    private val utils: Utils by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        messages.map {
            if (!utils.isValidMobileNumber(it.originatingAddress)) {
                val message = it.messageBody
                Log.d(tag, message)
                val amount = utils.isValidMessage(message)
                if (amount != null) {
                    val time = it.timestampMillis
                    if (context != null) {
                        saveSmsDetail(context, amount, time)
                    }
                }
            }
        }
    }

    private fun saveSmsDetail(context: Context, amount: String, time: Long) {
        // TODO use either of Firestore or retrofit
//        val saveSMSWorkRequest = OneTimeWorkRequestBuilder<SMSSaveWorker>()
        val saveSMSWorkRequest = OneTimeWorkRequestBuilder<SMSFirestoreSaveWorker>()
        val data = Data.Builder()
        data.putString(Constants.INTENT_EXTRA_SMS_AMOUNT, amount)
        data.putLong(Constants.INTENT_EXTRA_SMS_TIME, time)
        saveSMSWorkRequest.setInputData(data.build())
        WorkManager.getInstance(context).enqueue(saveSMSWorkRequest.build())
    }
}
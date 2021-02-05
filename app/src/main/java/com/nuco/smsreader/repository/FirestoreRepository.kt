package com.nuco.smsreader.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.nuco.smsreader.app.Constants.SMS_AMOUNT
import com.nuco.smsreader.app.Constants.SMS_COLLECTION
import com.nuco.smsreader.app.Constants.SMS_RECEIVED_TIME
import java.util.*
import kotlin.collections.HashMap

/**
 * Repository to interact with Firebase Firestore
 */
class FirestoreRepository {
    private val db = FirebaseFirestore.getInstance()

    /**
     * Save the SMS to Firestore
     * @param amount Amount
     * @param time SMS recived time
     */
    fun saveSMSDetail(amount: String, time: Date): Task<Void> {
        val userMap: MutableMap<String, Any> = HashMap()
        userMap[SMS_AMOUNT] = amount
        userMap[SMS_RECEIVED_TIME] = Timestamp(time)
        return db.collection(SMS_COLLECTION).document(UUID.randomUUID().toString()).set(userMap as Map<String, Any>)
    }

    /**
     * Gets the list of SMS stored on Firestore
     * @return returns the collection reference to query sms list
     */
    fun getSmsList(): CollectionReference {
        return db.collection(SMS_COLLECTION)
    }
}
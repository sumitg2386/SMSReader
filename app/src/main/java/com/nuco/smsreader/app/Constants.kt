package com.nuco.smsreader.app

/**
 * Application constants
 */
object Constants {
    const val SMS_PERMISSION_REQUEST_CODE = 1

    const val INTENT_EXTRA_SMS_AMOUNT = "SMS_AMOUNT"
    const val INTENT_EXTRA_SMS_TIME = "SMS_TIME"
    const val NETWORK_TIME_OUT = 2L

    const val SMS_DATE_TIME_FORMAT = "E, dd MMM YYYY - hh:mm aa"

    const val REGEX_MOBILE_NUMBER = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
    const val REGEX_AMOUNT = "(?i)(?:(?:RS|INR|MRP)\\.?\\s?)(\\d+(:?\\,\\d+)?(\\,\\d+)?(\\.\\d{1,2})?)"

    // Firebase Firestore
    const val SMS_COLLECTION = "sms"
    const val SMS_AMOUNT = "amount"
    const val SMS_RECEIVED_TIME = "receivedTime"
}
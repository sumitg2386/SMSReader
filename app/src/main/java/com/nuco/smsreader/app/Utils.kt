package com.nuco.smsreader.app

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Utility class
 */
class Utils {
    /**
     * Method to format the date of provided pattern
     */
    fun formatDate(dateTime: Date, pattern: String, timeZone: TimeZone = TimeZone.getDefault()): String {
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        simpleDateFormat.timeZone = timeZone
        return simpleDateFormat.format(dateTime)
    }

    /**
     * Checks whether message has amount.
     */
    fun isValidMessage(message: String): String? {
        var amount: String? = null
        val regEx: Pattern = Pattern.compile(Constants.REGEX_AMOUNT)
        val matcher: Matcher = regEx.matcher(message)

        if (matcher.find()) {
            amount = matcher.group()
        }
        return amount
    }

    /**
     * Checks whether valid mobile number
     */
    fun isValidMobileNumber(mobileNumber: String?): Boolean {
        var isValid = false
        if (mobileNumber != null) {

            val regEx: Pattern = Pattern.compile(Constants.REGEX_MOBILE_NUMBER)
            val matcher: Matcher = regEx.matcher(mobileNumber)

            if (matcher.find()) {
                isValid = true
            }
        }
        return isValid
    }

}
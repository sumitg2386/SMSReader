package com.nuco.smsreader

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.nuco.smsreader.app.Utils
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilsUnitTest {
    @Test
    fun utils_ValidMobileNumber() {
        assertThat(Utils().isValidMobileNumber("+918800000000")).isTrue()
        assertThat(Utils().isValidMobileNumber("8800000000")).isTrue()
        assertThat(Utils().isValidMobileNumber("08800000000")).isFalse()
        assertThat(Utils().isValidMobileNumber("+91880000000045")).isFalse()
        assertThat(Utils().isValidMobileNumber("248800000000")).isFalse()
        assertThat(Utils().isValidMobileNumber("VKICICIB")).isFalse()
        assertThat(Utils().isValidMobileNumber("BPiPaytm")).isFalse()
    }

    @Test
    fun utils_ValidSMS() {
        assertThat(Utils().isValidMessage("553642 is OTP for txn of INR 100.00 at ONE97 COMMUNICATIONS LIMI on ICICI Bank Debit Card XX1234.OTPs are SECRET.DO NOT disclose it to anyone.Bank NEVER asks for OTP.")).isEqualTo("INR 100.00")
        assertThat(Utils().isValidMessage("553642 is OTP for txn of Inr 100.00 at ONE97 COMMUNICATIONS LIMI on ICICI Bank Debit Card XX1234.OTPs are SECRET.DO NOT disclose it to anyone.Bank NEVER asks for OTP.")).isEqualTo("Inr 100.00")
        assertThat(Utils().isValidMessage("553642 is OTP for txn of RS 100.00 at ONE97 COMMUNICATIONS LIMI on ICICI Bank Debit Card XX1234.OTPs are SECRET.DO NOT disclose it to anyone.Bank NEVER asks for OTP.")).isEqualTo("RS 100.00")
        assertThat(Utils().isValidMessage("553642 is OTP for txn of Rs 100.00 at ONE97 COMMUNICATIONS LIMI on ICICI Bank Debit Card XX1234.OTPs are SECRET.DO NOT disclose it to anyone.Bank NEVER asks for OTP.")).isEqualTo("Rs 100.00")
        assertThat(Utils().isValidMessage("553642 is OTP for txn of 100.00 at ONE97 COMMUNICATIONS LIMI on ICICI Bank Debit Card XX1234.")).isNull()
        assertThat(Utils().isValidMessage("Txn of INR 100.00 on ICICI Bank Debit Card XX1234. Remaining balance Inr 10000.0")).isEqualTo("INR 100.00")
        assertThat(Utils().isValidMessage("553642 is OTP for txn of INR 10,000.00 at ONE97 COMMUNICATIONS LIMI on ICICI Bank Debit Card XX1234.OTPs are SECRET.DO NOT disclose it to anyone.Bank NEVER asks for OTP.")).isEqualTo("INR 10,000.00")
    }

}
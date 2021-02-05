package com.nuco.smsreader.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.nuco.smsreader.R
import com.nuco.smsreader.app.Constants
import com.nuco.smsreader.base.BaseActivity

/**
 * Min activity
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        checkSMSReadPermission()
    }

    private fun checkSMSReadPermission() {
        if (ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.RECEIVE_SMS
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                    arrayOf(Manifest.permission.RECEIVE_SMS),
                    Constants.SMS_PERMISSION_REQUEST_CODE
            )
        }
    }
}
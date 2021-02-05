package com.nuco.smsreader

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.workDataOf
import com.nuco.smsreader.di.remoteDataModule
import com.nuco.smsreader.di.viewModels
import com.nuco.smsreader.worker.SMSFirestoreSaveWorker
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.util.*

@RunWith(AndroidJUnit4::class)
class SMSFirestoreSaveWorkerUnitTest : KoinTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun testSaveSms() {
        val data = workDataOf("SMS_AMOUNT" to "Inr 100.0", "SMS_TIME" to Date().time)
        val worker = TestListenableWorkerBuilder<SMSFirestoreSaveWorker>(context).setInputData(data).build()
        val result = worker.startWork().get()
        assertThat(result, `is`(ListenableWorker.Result.success()))
    }

}
package com.example.servicestest

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyIntentService : IntentService(SERVICE_NAME) {
    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(false )
       createNotificationChannel()
        startForeground(NOTIFICATION_ID,createNotification())

    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
            for (i in 0 until 10) {
                Thread.sleep(1000)
                log("Timer $i")
            }
    }
    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }
    private fun log(message: String) {
        Log.d("SERVICE_TAG", "$SERVICE_NAME:$message")
    }
    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
    private fun createNotification()= NotificationCompat.Builder(this,CHANNEL_ID )
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentText("123")
        .setContentTitle("e31")
        .build()

    companion object {
        private const val CHANNEL_ID = "channel_id_MyForegroundService"
        private const val CHANNEL_NAME = "channel_name_MyForegroundService"
        private const val SERVICE_NAME = "MyIntentService"
        private const val NOTIFICATION_ID = 1

        fun intent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }

}

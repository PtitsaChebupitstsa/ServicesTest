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

class MyIntentService2 : IntentService(SERVICE_NAME) {
    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(true )


    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        val page = intent?.getIntExtra(PAGE,0 )?: 0
            for (i in 0 until 10) {
                Thread.sleep(1000)
                log("Timer $i page $page" )
            }
    }
    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }
    private fun log(message: String) {
        Log.d("SERVICE_TAG", "$SERVICE_NAME:$message")
    }


    companion object {
        private const val SERVICE_NAME = "MyIntentService"
        private const val PAGE = "page"
        fun newIntent(context: Context,page:Int): Intent {
            return Intent(context, MyIntentService2::class.java).apply {
                putExtra(PAGE,page)
            }
        }
    }

}

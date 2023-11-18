package com.example.servicestest


import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.servicestest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.simpleService.setOnClickListener {
            stopService(MyForegroundService.intent(this))
            startService(MyService.intent(this, 25))

        }

        binding.foregroundService.setOnClickListener {
            ContextCompat.startForegroundService(this,MyForegroundService.intent(this))
        }

        binding.intentService.setOnClickListener {
            startService(MyIntentService.intent(this))
        }
    }


}
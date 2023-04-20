package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log

class BestTripBotService : Service() {

    private val messages = arrayOf("Hello %s!", "How are you?", "Good Bye %s!")
    private var counter = 0
    private lateinit var notificationHandler: NotificationBuilder

    val TAG = "ChatBotService"

    override fun onCreate() {
        super.onCreate()
        notificationHandler = NotificationBuilder(this, getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
    }

    init
    {
        Log.d(TAG, "Chat Bot Service is Running")
    }

    override fun onBind(p0: Intent?): IBinder? = null

    private fun sendBroadcast(message: String){
        val responseBundle = Bundle()
        responseBundle.putString(KEY_RESPONSE_MSG, message)
        val responseIntent = Intent().apply {
            putExtras(responseBundle)
            action = KEY_RESPONSE_CMD
        }
        sendBroadcast(responseIntent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        super.onStartCommand(intent, flags, startId)
        intent?.let {
            val data = intent.extras
            when (val cmd = data?.getInt(KEY_CMD)) {
                CMD_GENERATE_MESSAGE -> {
                    val name = data.getString(KEY_NAME)
                    Log.d(TAG, "onStartCommand: $cmd, $name")
                    val message = messages[counter].format(name)
                    counter = (counter + 1) % 3

                    sendBroadcast(message)
                    notificationHandler.sendMessage(message)
                }
                else -> {
                    Log.d(TAG, "onStartCommand: $cmd")
                }
            }
        }


        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Serivice is being stopped... ")
        val message = "ChatBot Stopped: 00"
        notificationHandler.sendMessage(message)
    }

    companion object {
        const val CMD_GENERATE_MESSAGE = 50
        const val KEY_CMD = "cmd"
        const val KEY_NAME = "name"
        const val KEY_RESPONSE_MSG = "chat_message"
        const val KEY_RESPONSE_CMD = "chat_cmd"
        private const val TAG = "ChatBotService"
    }

}
package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.services

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.utilities.NotificationBuilder

class BestTripBotService : Service() {

    private val messages = arrayOf("BTBot: \nHello %s!", "BTBot: \nI'm Great! How Can I Help You?", "BTBot: \nCertainly!.. Your issue is fixed!", "BTBot: \nGood Bye %s!")
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

    private fun sendBroadcast(message: String, recievedMessage: String){
        val responseBundle = Bundle()
        responseBundle.putString(KEY_RESPONSE_MSG, message)
        responseBundle.putString(KEY_SENT_MSG, recievedMessage)
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
                    val chatMsg = data.getString(KEY_SENT_MSG)
                    Log.d(TAG, "onStartCommand: $cmd, $name, $chatMsg")
                    val message = messages[counter].format(name)
                    val recievedMessage = chatMsg.toString()
                    counter = (counter + 1) % 4

                    sendBroadcast(message, recievedMessage)
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
        const val KEY_SENT_MSG = "msg"
        const val KEY_RESPONSE_MSG = "chat_message"
        const val KEY_RESPONSE_CMD = "chat_cmd"
        private const val TAG = "ChatBotService"
    }

}
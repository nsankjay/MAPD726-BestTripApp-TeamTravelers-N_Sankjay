package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.R
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities.BestTripBotService.Companion.CMD_GENERATE_MESSAGE
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities.BestTripBotService.Companion.KEY_CMD
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities.BestTripBotService.Companion.KEY_NAME
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities.BestTripBotService.Companion.KEY_RESPONSE_MSG

class BestTripBotActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_trip_bot)

        val startServiceBtn = findViewById<Button>(R.id.startServiceBtn)
        val stopServiceBtn = findViewById<Button>(R.id.stopServiceBtn)
        val generateChatBtn = findViewById<Button>(R.id.generateChatBtn)
        val tvChatBox = findViewById<TextView>(R.id.tvChatBox)
        val nameTxtBx = findViewById<EditText>(R.id.nameTxtBx)

        startServiceBtn.setOnClickListener {
            Intent(this, BestTripBotService::class.java).also {
                startService(it)
                tvChatBox.text = ""
                val nameTxt = nameTxtBx.text.toString()
                val data = Bundle().apply {
                    putInt(KEY_CMD, CMD_GENERATE_MESSAGE)
                    putString(KEY_NAME, nameTxt)
                }

                var listView = findViewById<ListView>(R.id.chatListView)

                //Creating a Seperate list view variable
                var list = mutableListOf<BestTripBotModel>()

                //Adding data to the adapter
                list.add(BestTripBotModel("Chat Bot Joined the Session", R.drawable.ic_msg_rcvd1))

                listView.adapter = BestTripBotModelAdapter(this, R.layout.bubble_message, list)

            }
        }

        stopServiceBtn.setOnClickListener {
            Intent(this, BestTripBotService::class.java).also {
                stopService(it)
                tvChatBox.text = "Chat Service stopped"

                var listView = findViewById<ListView>(R.id.chatListView)

                //Creating a Seperate list view variable
                var list = mutableListOf<BestTripBotModel>()

                //Adding data to the adapter
                list.add(BestTripBotModel("Chat Bot Left the Session", R.drawable.ic_msg_rcvd1))

                listView.adapter = BestTripBotModelAdapter(this, R.layout.bubble_message, list)
            }
        }

        generateChatBtn.setOnClickListener {

            val nameTxt = nameTxtBx.text.toString()
            val data = Bundle().apply {
                putInt(KEY_CMD, CMD_GENERATE_MESSAGE)
                putString(KEY_NAME, nameTxt)
            }
            intent = Intent(applicationContext, BestTripBotService::class.java)
            intent.putExtras(data)
            startService(intent)

        }

        textView = findViewById(R.id.tvChatBox)
        textView.movementMethod = ScrollingMovementMethod()
        val intentFilter = IntentFilter()
        intentFilter.addAction(BestTripBotService.KEY_RESPONSE_CMD)
        registerReceiver(broadcastReceiver, intentFilter)

    }

    private val broadcastReceiver = object : BroadcastReceiver()  {
        val TAG = "BroadcastReceiver"
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            val data = intent?.extras
            Log.d(TAG, "onReceive: $action")
            if (action == BestTripBotService.KEY_RESPONSE_CMD) {
                if (data != null) {
                    textView.append("${data.getString(KEY_RESPONSE_MSG)}\n")
                }
            }
        }
    }

}
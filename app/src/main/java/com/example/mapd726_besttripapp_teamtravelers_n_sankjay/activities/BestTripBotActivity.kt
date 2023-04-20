package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.R
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.services.BestTripBotService.Companion.CMD_GENERATE_MESSAGE
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.services.BestTripBotService.Companion.KEY_CMD
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.services.BestTripBotService.Companion.KEY_NAME
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.services.BestTripBotService.Companion.KEY_RESPONSE_MSG
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.adapter.BestTripBotModelAdapter
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.dataModel.BestTripBotModel
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.services.BestTripBotService
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.services.BestTripBotService.Companion.KEY_SENT_MSG

class BestTripBotActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_trip_bot)

        val profileSharedPref = getSharedPreferences("paymentSharedPref", MODE_PRIVATE)

        val startServiceBtn = findViewById<Button>(R.id.startServiceBtn)
        val stopServiceBtn = findViewById<Button>(R.id.stopServiceBtn)
        val generateChatBtn = findViewById<Button>(R.id.generateChatBtn)
        val tvChatBox = findViewById<TextView>(R.id.tvChatBox)
        val nameTxtBx = findViewById<EditText>(R.id.nameTxtBx)
        val chatMsgTxtBx = findViewById<EditText>(R.id.sendMsgTxtBx)

        val storedFirstName = profileSharedPref.getString("pref_first_name",null)

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

            val nameTxt = storedFirstName.toString()
            val chatTxt = chatMsgTxtBx.text.toString()
            val data = Bundle().apply {
                putInt(KEY_CMD, CMD_GENERATE_MESSAGE)
                putString(KEY_NAME, nameTxt)
                putString(KEY_SENT_MSG, chatTxt)
            }
            intent = Intent(applicationContext, BestTripBotService::class.java)
            intent.putExtras(data)
            startService(intent)

            chatMsgTxtBx.setText("")


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
                    textView.append("${data.getString(KEY_SENT_MSG)}\n")
                    val delayInMillis = 1500L // 1.5 seconds
                    Handler(Looper.getMainLooper()).postDelayed({
                        textView.append("   ${data.getString(KEY_RESPONSE_MSG)}\n")
                        textView.append(" \n")
                    }, delayInMillis)
                }
            }
        }
    }

    //    Loading the menu in the view
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    // Menu options onClick event

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var i = intent
        when (item.itemId) {
            R.id.menuViewProfileBtn -> {
                i = Intent(this, ViewUserAccountActivity::class.java)
                startActivity(i)

                Intent(this, BestTripBotService::class.java).also {
                    stopService(it)
                }

            }

            R.id.menuPaymentBtn -> {
                i = Intent(this, SavePaymentActivity::class.java)
                startActivity(i)

                Intent(this, BestTripBotService::class.java).also {
                    stopService(it)
                }

            }

            R.id.menuHomeBtn -> {
                i = Intent(this, MainActivity::class.java)
                startActivity(i)

                Intent(this, BestTripBotService::class.java).also {
                    stopService(it)
                }

            }


        }

        return true
    }

}
package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.R
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.adapter.notificationsListModelAdapter
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.dataModel.notificationsListModel
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.services.BestTripBotService
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NotificationListActivity : AppCompatActivity() {

    var notificationTitle: String = "Title"
    var notificationDate: String = ""

    var listLength: Int = 1
    var arrayNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)

        val notificationsSharedPref = getSharedPreferences("notificationsSharedPref", MODE_PRIVATE)
        val editorProfile = notificationsSharedPref.edit()

        notificationTitle = notificationsSharedPref.getString("pref_noti_title",null).toString()
        notificationDate = notificationsSharedPref.getString("pref_noti_date",null).toString()

        var listView = findViewById<ListView>(R.id.notificationsListView)

        //Creating a Seperate list view variable
        var list = mutableListOf<notificationsListModel>()

        //setCurrentDate ()


        //Adding data to the adapter
//        list.add(notificationsListModel("Payment updated", notificationDate, R.drawable.ic_msg_rcvd1))
//        list.add(notificationsListModel("BTBot sent you a message", notificationDate, R.drawable.ic_msg_rcvd1))

        for (i in 0 until listLength) {

            arrayNum += 1

            list.add(notificationsListModel(notificationTitle, notificationDate, R.drawable.ic_msg_rcvd1))
        }

        listView.adapter = notificationsListModelAdapter(this, R.layout.notification_row, list)

        //On click listener for the list view
        listView.setOnItemClickListener { adapterView, view, i, l ->
            if (i == 0) {

            }
            if (i == 1) {

            }
            if (i == 2) {

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
            R.id.menuHomeBtn -> {
                i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }

            R.id.menuPaymentBtn -> {
                i = Intent(this, SavePaymentActivity::class.java)
                startActivity(i)
            }

            R.id.menuHelpBtn -> {
                i = Intent(this, BestTripBotActivity::class.java)
                startActivity(i)

                Intent(this, BestTripBotService::class.java).also {
                    startService(it)
                }

            }

            R.id.menuViewProfileBtn -> {
                i = Intent(this, ViewUserAccountActivity::class.java)
                startActivity(i)
            }


        }

        return true
    }

    private fun setCurrentDate ()
    {
        val simpleDateFormat: DateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
        val calendar = Calendar.getInstance()
        notificationDate = simpleDateFormat.format(calendar.time)
    }

}
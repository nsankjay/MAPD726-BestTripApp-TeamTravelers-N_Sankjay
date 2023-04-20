package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.RemoteViews
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NotificationBuilder (private val context: Context, private val notificationManager: NotificationManager) {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANEL_ID,
                "messages",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private var date: String? = null

    fun getDate(): String? {
        val simpleDateFormat: DateFormat = SimpleDateFormat("MM/dd/yyyy")
        val calendar = Calendar.getInstance()
        date = simpleDateFormat.format(calendar.time)
        return date
    }

    fun sendMessage(message: String){

        val notification = RemoteViews(context.packageName, R.layout.text_row)
        notification.setTextViewText(R.id.chatText, message)

        getDate()

        val mBuilder = Notification.Builder(context)
            .setSmallIcon(R.drawable.ic_message_icon)
            .setContentTitle("("+ date + ")" + "  Sankjay")
            .setCustomContentView(notification)
            .setContentText(message)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder.setChannelId(CHANEL_ID)
        }

        val noti = mBuilder.build()
        noti.flags = noti.flags or Notification.FLAG_AUTO_CANCEL
        notificationManager.notify(0, noti)
    }

    companion object {
        private const val CHANEL_ID = "96000"
    }

}
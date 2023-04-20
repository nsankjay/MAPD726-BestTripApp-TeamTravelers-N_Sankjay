package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SavePaymentActivity : AppCompatActivity() {

    var paymentSaveDate: String = ""
    var paymentChangeText: String = "Payment has been updated"

    //lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding= ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_save_payment)

        val paymentSharedPref = getSharedPreferences("paymentSharedPref", MODE_PRIVATE)
        val editor = paymentSharedPref.edit()

        val notificationsSharedPref = getSharedPreferences("notificationsSharedPref", MODE_PRIVATE)
        val notificationsSharedPrefeditor = notificationsSharedPref.edit()


        val cancelCardSaveBtn=findViewById<Button>(R.id.cancelCardSaveBtn)

        //Click Cancel Button action - cancel and go to Edit user account page
        cancelCardSaveBtn.setOnClickListener {

            Intent(this, EditUserAccountDetailsActivity::class.java).also {

                startActivity(it)
            }

            Toast.makeText(this@SavePaymentActivity, "Card Save Cancelled", Toast.LENGTH_SHORT).show()

        }

        val saveCardBtn=findViewById<Button>(R.id.cardSaveBtn)
        val ccCardNumber=findViewById<EditText>(R.id.typeCardNumber)
        val ccCardType=findViewById<EditText>(R.id.typeCardType)
        val ccExpiryDate=findViewById<EditText>(R.id.typeExpirationDate)
        val ccCvv=findViewById<EditText>(R.id.typeCvvCvc)

        val cardNumberResult = paymentSharedPref.getString("pref_card_number",null)
        val cardTypeResult = paymentSharedPref.getString("pref_card_type",null)
        val cardExpiryResult = paymentSharedPref.getString("pref_card_expiry",null)
        val cardCvvResult = paymentSharedPref.getString("pref_card_cvv",null)
        val trimCardNumber = cardNumberResult?.substring(12, Math.max(cardNumberResult.length, 4))
        ccCardNumber.setText(trimCardNumber)
        ccCardType.setText(cardTypeResult)
        ccExpiryDate.setText(cardExpiryResult)
        ccCvv.setText(cardCvvResult)


        //Click Save Card Button action - Save Card and go to View user account page
        saveCardBtn.setOnClickListener {
            setSaveCurrentDate ()

            val cardNumber = ccCardNumber.text.toString()
            val cardType = ccCardType.text.toString()
            val cardExpiry = ccExpiryDate.text.toString()
            val cardCvv = ccCvv.text.toString()
            val currentDate = paymentSaveDate
            val notiTitleText = paymentChangeText


            editor.apply{
                putString("pref_card_number", cardNumber)
                putString("pref_card_type", cardType)
                putString("pref_card_expiry", cardExpiry)
                putString("pref_card_cvv", cardCvv)
                apply()

            }

            notificationsSharedPrefeditor.apply {
                putString("pref_noti_title", notiTitleText)
                putString("pref_noti_date", currentDate)
                apply()
            }



            Intent(this, ViewUserAccountActivity::class.java).also {

                startActivity(it)
            }

            Toast.makeText(this@SavePaymentActivity, "Card Save Successful", Toast.LENGTH_SHORT).show()

        }
    }

    private fun setSaveCurrentDate ()
    {
        val simpleDateFormat: DateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
        val calendar = Calendar.getInstance()
        paymentSaveDate = simpleDateFormat.format(calendar.time)
    }
}
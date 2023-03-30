package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.R

class EditUserAccountDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_account_details)

        val saveBtn=findViewById<Button>(R.id.SaveBtn)

        //Click Save Button action - save and go to user account page
        saveBtn.setOnClickListener {

            //saveData()

            Intent(this, ViewUserAccountActivity::class.java).also {

                startActivity(it)
            }

            Toast.makeText(this@EditUserAccountDetailsActivity, "Save Successful", Toast.LENGTH_SHORT).show()

        }

        val cancelSaveBtn=findViewById<Button>(R.id.cancelProfileSaveBtn)

        //Click Cancel Button action - cancel and go to user account page
        cancelSaveBtn.setOnClickListener {

            Intent(this, ViewUserAccountActivity::class.java).also {

                startActivity(it)
            }

            Toast.makeText(this@EditUserAccountDetailsActivity, "Save Cancelled", Toast.LENGTH_SHORT).show()

        }

        val addPaymentBtn=findViewById<Button>(R.id.goToAddPaymentBtn)

        //Click Add Payment Button action - Go to Add payment view
        addPaymentBtn.setOnClickListener {

            Intent(this, SavePaymentActivity::class.java).also {

                startActivity(it)
            }

        }
    }

    private fun saveData() {
        val firstNameTxt = findViewById<EditText>(R.id.typeYourName).toString()
        val lastNameTxt = findViewById<EditText>(R.id.typeYourLastName).toString()
        val addressTxt = findViewById<EditText>(R.id.typeYourAddress).toString()
        val postalCodeTxt = findViewById<EditText>(R.id.typePostalCode).toString()
        val phoneNumberTxt = findViewById<EditText>(R.id.typePhoneNumber).toString()
        val ccNumberTxt = findViewById<EditText>(R.id.typeCardNumber).toString()
        val ccCvvTxt = findViewById<EditText>(R.id.typeCvvCvc).toString()
        val cardTypeTxt = findViewById<EditText>(R.id.typeCardType).toString()
        val expiryDateTxt = findViewById<EditText>(R.id.typeExpirationDate).toString()

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("firstName_KEY", firstNameTxt)
            putString("lastName_KEY", lastNameTxt)
            putString("address_KEY", addressTxt)
            putString("postalCode_KEY", postalCodeTxt)
            putString("phoneNumber_KEY", phoneNumberTxt)
            putString("ccNumber_KEY", ccNumberTxt)
            putString("ccCvv_KEY", ccCvvTxt)
            putString("cardType_KEY", cardTypeTxt)
            putString("expiryDate_KEY", expiryDateTxt)
        }.apply()
    }
}
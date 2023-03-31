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

        val firstNameTxt = findViewById<EditText>(R.id.typeYourName)
        val lastNameTxt = findViewById<EditText>(R.id.typeYourLastName)
        val addressTxt = findViewById<EditText>(R.id.typeYourAddress)
        val postalCodeTxt = findViewById<EditText>(R.id.typePostalCode)
        val phoneNumberTxt = findViewById<EditText>(R.id.typePhoneNumber)
        val eMailTxt = findViewById<EditText>(R.id.typeEmail)

        val paymentSharedPref = getSharedPreferences("paymentSharedPref", MODE_PRIVATE)
        val editor = paymentSharedPref.edit()

        val profileSharedPref = getSharedPreferences("paymentSharedPref", MODE_PRIVATE)
        val editorProfile = profileSharedPref.edit()

        val saveBtn=findViewById<Button>(R.id.SaveBtn)
        val cardNumberTxt=findViewById<TextView>(R.id.typeCardNumber)

        val firstNameRslt = profileSharedPref.getString("pref_first_name",null)
        val lastNameRslt = profileSharedPref.getString("pref_last_name",null)
        val addressRslt = profileSharedPref.getString("pref_address",null)
        val postalCodeRslt = profileSharedPref.getString("pref_postal_code",null)
        val phoneNumberRslt = profileSharedPref.getString("pref_phone_nuber",null)
        val eMailRslt = profileSharedPref.getString("pref_e_mail",null)
        firstNameTxt.setText(firstNameRslt)
        lastNameTxt.setText(lastNameRslt)
        addressTxt.setText(addressRslt)
        postalCodeTxt.setText(postalCodeRslt)
        phoneNumberTxt.setText(phoneNumberRslt)
        eMailTxt.setText(eMailRslt)

        val cardNumberResult = paymentSharedPref.getString("pref_card_number",null)

        if (cardNumberResult != null) {
            cardNumberTxt.text = cardNumberResult.substring(12, Math.max(cardNumberResult.length, 4))
        };

        //Click Save Button action - save and go to user account page
        saveBtn.setOnClickListener {

            val firstName = firstNameTxt.text.toString()
            val lastName = lastNameTxt.text.toString()
            val address = addressTxt.text.toString()
            val postalCode = postalCodeTxt.text.toString()
            val phoneNumber = phoneNumberTxt.text.toString()
            val eMail = eMailTxt.text.toString()

            editorProfile.apply{
                putString("pref_first_name", firstName)
                putString("pref_last_name", lastName)
                putString("pref_address", address)
                putString("pref_postal_code", postalCode)
                putString("pref_phone_nuber", phoneNumber)
                putString("pref_e_mail", eMail)
                apply()

            }

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

}
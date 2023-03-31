package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.R

class ViewUserAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_user_account)

        val profileSharedPref = getSharedPreferences("paymentSharedPref", MODE_PRIVATE)
        val editorProfile = profileSharedPref.edit()

        val dspFirstName = findViewById<TextView>(R.id.viewFirstNameData)
        val dspLastName = findViewById<TextView>(R.id.viewLastNameData)
        val dspAddress = findViewById<TextView>(R.id.viewAddressData)
        val dspPostalCode = findViewById<TextView>(R.id.viewPostalCodeData)
        val dspPhoneNumber = findViewById<TextView>(R.id.viewPhoneData)
        val dspFullName = findViewById<TextView>(R.id.viewFullNameData)
        val dspEMail = findViewById<TextView>(R.id.viewEmailData)
        val clickableWalletImage = findViewById<ImageButton>(R.id.clickablePaymentImage)

        val firstNameRslt = profileSharedPref.getString("pref_first_name",null)
        val lastNameRslt = profileSharedPref.getString("pref_last_name",null)
        val addressRslt = profileSharedPref.getString("pref_address",null)
        val postalCodeRslt = profileSharedPref.getString("pref_postal_code",null)
        val phoneNumberRslt = profileSharedPref.getString("pref_phone_nuber",null)
        val eMailRslt = profileSharedPref.getString("pref_e_mail",null)
        dspFirstName.setText(firstNameRslt)
        dspLastName.setText(lastNameRslt)
        dspAddress.setText(addressRslt)
        dspPostalCode.setText(postalCodeRslt)
        dspPhoneNumber.setText(phoneNumberRslt)
        dspFullName.text = firstNameRslt.toString() + " " + lastNameRslt.toString()
        dspEMail.text = eMailRslt.toString()

        val editBtn=findViewById<Button>(R.id.editBtn)

        //Click Edit Button action - go to edit user account details page
        editBtn.setOnClickListener {
            Intent(this, EditUserAccountDetailsActivity::class.java).also {

                startActivity(it)
            }

        }

        //Click Wallet Image Button action - go to Save Payment details page
        clickableWalletImage.setOnClickListener {
            Intent(this, SavePaymentActivity::class.java).also {

                startActivity(it)
            }

        }
    }

}
package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.R

class ViewUserAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_user_account)

        //loadData()


        val editBtn=findViewById<Button>(R.id.editBtn)

        //Click Edit Button action - go to edit user account details page
        editBtn.setOnClickListener {
            Intent(this, EditUserAccountDetailsActivity::class.java).also {

                startActivity(it)
            }

        }
    }

    private fun loadData() {

        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val firstNameString = sharedPreferences.getString("firstName_KEY", null)
        val lastNameString = sharedPreferences.getString("lastName_KEY", null)

        val FirstNameData =findViewById<TextView>(R.id.viewFirstNameData)
        FirstNameData.text = firstNameString

        val lastNameData =findViewById<TextView>(R.id.viewFirstNameData)
        lastNameData.text = lastNameString

    }
}
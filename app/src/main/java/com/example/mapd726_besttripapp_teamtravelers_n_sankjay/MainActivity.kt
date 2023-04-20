package com.example.mapd726_besttripapp_teamtravelers_n_sankjay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities.BestTripBotActivity
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities.SavePaymentActivity
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.activities.ViewUserAccountActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            }

            R.id.menuPaymentBtn -> {
                i = Intent(this, SavePaymentActivity::class.java)
                startActivity(i)
            }

            R.id.menuHelpBtn -> {
                i = Intent(this, BestTripBotActivity::class.java)
                startActivity(i)
            }


        }

        return true
    }

}
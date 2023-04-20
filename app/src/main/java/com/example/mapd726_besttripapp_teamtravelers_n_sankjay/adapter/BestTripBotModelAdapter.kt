package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.R
import com.example.mapd726_besttripapp_teamtravelers_n_sankjay.dataModel.BestTripBotModel

class BestTripBotModelAdapter (var mCtx: Context, var resources:Int, var items:List<BestTripBotModel>):
    ArrayAdapter<BestTripBotModel>(mCtx, resources, items) {

    // Creating a function
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val imageView: ImageView = view.findViewById(R.id.chatBubbleImage2)
        val chatTextView: TextView = view.findViewById(R.id.chatText2)

        val mItem: BestTripBotModel = items[position]
        imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.img))
        chatTextView.text = mItem.chat

        return view
    }
}
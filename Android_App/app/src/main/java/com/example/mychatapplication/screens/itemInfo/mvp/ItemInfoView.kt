package com.example.mychatapplication.screens.itemInfo.mvp

import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.R
import com.example.mychatapplication.models.EventItem
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class ItemInfoView(var activity: AppCompatActivity) : FrameLayout(activity) {

  var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
  
  private val progressBar:ProgressBar
  private val image:ImageView
  private val title:TextView 
  private val dateSet:TextView 
  private val address:TextView 
  private val description:TextView 

  init {
    inflate(activity, R.layout.item_info, this)
    progressBar = findViewById(R.id.progressBar)
    image = findViewById(R.id.imageView)
    title = findViewById(R.id.title)
    dateSet = findViewById(R.id.date)
    address = findViewById(R.id.address)
    description = findViewById(R.id.description)
    if (activity.supportActionBar != null){
      activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
      activity.supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
  }

  fun setData(eventItem: EventItem) {
    activity.runOnUiThread {
      title.text = context.getString(R.string.title_label, eventItem.title)
      address.text = context.getString(R.string.address_label, eventItem.address)
      val date = Date(eventItem.date.toLong() * 1000)
      val value = simpleDateFormat.format(date)
      dateSet.text = context.getString(R.string.time_label, value)
      description.text = context.getString(R.string.description_label, eventItem.description)
      Picasso.get().load(eventItem.image).into(image)
    }
  }

  fun showError() {
    activity.runOnUiThread {
      Toast.makeText(activity, context.getString(R.string.network_connection_label), Toast.LENGTH_LONG).show()
    }
  }

  fun showProgressBar(showProgress: Boolean) {
    activity.runOnUiThread {
      if (showProgress) {
        progressBar.visibility = View.VISIBLE
      }
      else {
        progressBar.visibility = View.INVISIBLE
      }
    }
  }
}

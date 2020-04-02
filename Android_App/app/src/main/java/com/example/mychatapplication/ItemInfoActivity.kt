package com.example.mychatapplication

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.models.EventItem
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ItemInfoActivity : AppCompatActivity() {

    var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_info)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        val image = findViewById<ImageView>(R.id.imageView)
        val title = findViewById<TextView>(R.id.title)
        val dateValue = findViewById<TextView>(R.id.date)
        val address = findViewById<TextView>(R.id.address)
        val description = findViewById<TextView>(R.id.description)
        val id = intent.getStringExtra("id")
        if (id!=null) {
            val service =
                RetrofitClientInstance.getRetrofitInstance()!!.create(ServerRequestService::class.java)
            val call = service.getEvent(RetrofitClientInstance.getToken(this)?.token!!, id)


            call.enqueue(object : Callback<EventItem> {
                override fun onFailure(call: Call<EventItem>, t: Throwable) {
                    showError()
                    progressBar.visibility = View.GONE
                }

                override fun onResponse(call: Call<EventItem>, response: Response<EventItem>) {
                    if (response.code() == 200 && response.body() != null) {
                        title.text = "Title: " + response.body()!!.title
                        address.text = "Address: " + response.body()!!.address
                        val date = Date(response.body()!!.date.toLong()*1000)
                        dateValue.text = "Time:" + simpleDateFormat.format(date)
                        description.text = "Description: " + response.body()!!.description
                        Picasso.get().load(response.body()!!.image).into(image)
                    }
                    progressBar.visibility = View.GONE
                }
            })
        }
        // add back arrow to toolbar
        if (supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    fun showError() {
        Toast.makeText(this, "Error, check network connection", Toast.LENGTH_LONG).show()
    }
}

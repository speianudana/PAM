package com.example.mychatapplication

import android.content.Intent
import android.net.Uri.fromParts
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_layout)

        val appSettings = findViewById<Button>(R.id.app_settings)
        val userId = findViewById<TextView>(R.id.user_id)
        val username = findViewById<TextView>(R.id.username)

        val service =
            RetrofitClientInstance.getRetrofitInstance()!!.create(ServerRequestService::class.java)
        val call = service.userInfo(RetrofitClientInstance.getToken(this)?.token!!)

        appSettings.setOnClickListener {
            val intent = Intent()
            intent.action = ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = fromParts("package", this.packageName, null)
            intent.data = uri
            this.startActivity(intent)
        }

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                showError()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 200 && response.body()!=null) {
                    userId.text = "User id: " + response.body()!!.id
                    username.text = "Username: " + response.body()!!.username
                }
            }

        })

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

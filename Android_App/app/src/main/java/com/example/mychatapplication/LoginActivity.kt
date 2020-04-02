package com.example.mychatapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.models.AuthToken
import com.example.mychatapplication.models.AuthenticateUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val loginButton = findViewById<Button>(R.id.login_button_id)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        loginButton.setOnClickListener {
            if(username.text.toString().isEmpty()) {
                username.error = "First name is required!"
            }
            if(password.text.toString().isEmpty()) {
                password.error = "password name is required!"
            }

            if (username.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {
                val authenticateUser = AuthenticateUser(username.text.toString(), password.text.toString())
                val service =
                    RetrofitClientInstance.getRetrofitInstance()!!.create(ServerRequestService::class.java)
                val call = service.authenticate(authenticateUser)

                call.enqueue(object : Callback<AuthToken> {
                    override fun onFailure(call: Call<AuthToken>, t: Throwable) {
                        showError()
                    }

                    override fun onResponse(call: Call<AuthToken>, response: Response<AuthToken>) {
                        if (response.code() == 200 && response.body()!=null) {
                            moveToNextScreen(response.body()!!)
                        } else {
                            showError()
                        }
                    }

                })
            }
        }
    }

    fun moveToNextScreen(authToken: AuthToken) {
        RetrofitClientInstance.saveToken(authToken, this)
        val intent= Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun showError() {
        Toast.makeText(this, "Error, check network connection or credentials", Toast.LENGTH_LONG).show()
    }
}

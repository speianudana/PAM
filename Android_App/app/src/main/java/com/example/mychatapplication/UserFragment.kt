package com.example.mychatapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mychatapplication.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFragment : Fragment() {
    companion object {
        const val TAG = "UserFragment"
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_info,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = view.findViewById<TextView>(R.id.username)
        val logoutButton = view.findViewById<Button>(R.id.logout)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        val service =
            RetrofitClientInstance.getRetrofitInstance()!!.create(ServerRequestService::class.java)
        val call = service.userInfo(RetrofitClientInstance.getToken(this.context!!)?.token!!)
        val logoutCall = service.logout(RetrofitClientInstance.getToken(this.context!!)?.token!!)

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                showError()
                progressBar.visibility = View.INVISIBLE
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 200 && response.body()!=null) {
                    username.text = "Hi: " + response.body()!!.username
                } else {
                    showError()
                }
                progressBar.visibility = View.INVISIBLE
            }
        })


        logoutButton.setOnClickListener {
            logoutCall.enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    showError()
                    moveToLoginScreen()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    moveToLoginScreen()
                }
            })

        }

    }

    fun showError() {
        Toast.makeText(context, "Error, check network connection", Toast.LENGTH_LONG).show()
    }

    fun moveToLoginScreen() {
        RetrofitClientInstance.removeToken(this.context!!)
        val intent= Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
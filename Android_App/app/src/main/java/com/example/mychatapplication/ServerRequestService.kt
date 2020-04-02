package com.example.mychatapplication

import com.example.mychatapplication.models.AuthToken
import com.example.mychatapplication.models.AuthenticateUser
import com.example.mychatapplication.models.EventItem
import com.example.mychatapplication.models.User
import retrofit2.Call
import retrofit2.http.*

interface ServerRequestService {

    @GET("/api/v1/events/all")
    fun getEvents(@Header("auth_token") auth_token: String): Call<List<EventItem>>

    @GET("/api/v1/event")
    fun getEvent(@Header("auth_token") auth_token: String, @Query("id") id: String): Call<EventItem>

    @POST("/api/v1/authenticate")
    fun authenticate(@Body authenticateUser: AuthenticateUser): Call<AuthToken>

    @POST("/api/v1/logout")
    fun logout(@Header("auth_token") auth_token: String): Call<String>

    @GET("/api/v1/user_info")
    fun userInfo(@Header("auth_token") auth_token: String): Call<User>
}
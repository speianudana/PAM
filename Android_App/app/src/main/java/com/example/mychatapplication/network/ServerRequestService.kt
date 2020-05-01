package com.example.mychatapplication.network

import com.example.mychatapplication.models.AuthToken
import com.example.mychatapplication.models.AuthenticateUser
import com.example.mychatapplication.models.EventItem
import com.example.mychatapplication.models.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface ServerRequestService {

    @GET("/api/v1/events/all")
    fun getEvents(@Header("auth_token") auth_token: String): Observable<List<EventItem>>

    @GET("/api/v1/event")
    fun getEvent(@Header("auth_token") auth_token: String, @Query("id") id: String): Observable<EventItem>

    @POST("/api/v1/authenticate")
    fun authenticate(@Body authenticateUser: AuthenticateUser): Observable<AuthToken>

    @POST("/api/v1/logout")
    fun logout(@Header("auth_token") auth_token: String): Observable<Any>

    @GET("/api/v1/user_info")
    fun userInfo(@Header("auth_token") auth_token: String): Observable<User>
}
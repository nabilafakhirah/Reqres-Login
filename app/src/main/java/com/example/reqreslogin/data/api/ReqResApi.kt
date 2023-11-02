package com.example.reqreslogin.data.api

import com.example.reqreslogin.data.model.LoginRequest
import com.example.reqreslogin.data.model.LoginResponse
import com.example.reqreslogin.data.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReqResApi {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("/api/users")
    suspend fun getUsers(
        @Query("page") page: Int,
    ): UserResponse
}
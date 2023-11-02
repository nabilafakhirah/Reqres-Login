package com.example.reqreslogin.data.model

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name="token")
    val token:String
)

package com.example.reqreslogin.data.model

import com.squareup.moshi.Json

data class UserResponse(
    @Json(name = "data")
    val data: List<UserDto>
) {
    data class UserDto(
        @Json(name = "avatar")
        val avatar: String?,
        @Json(name = "email")
        val email: String?,
        @Json(name = "first_name")
        val first_name: String?,
        @Json(name = "id")
        val id: Int?,
        @Json(name = "last_name")
        val last_name: String?
    )
}
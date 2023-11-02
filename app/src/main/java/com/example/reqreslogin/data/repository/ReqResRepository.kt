package com.example.reqreslogin.data.repository

import com.example.reqreslogin.data.model.LoginRequest
import com.example.reqreslogin.data.model.LoginResponse
import com.example.reqreslogin.data.model.UserResponse
import com.example.reqreslogin.utils.DataResult
import kotlinx.coroutines.flow.Flow

interface ReqResRepository {
    fun login(request: LoginRequest): Flow<DataResult<LoginResponse>>
    fun getUsers(page: Int): Flow<DataResult<UserResponse>>
}
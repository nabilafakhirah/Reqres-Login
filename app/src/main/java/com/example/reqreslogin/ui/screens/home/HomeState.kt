package com.example.reqreslogin.ui.screens.home

import androidx.paging.PagingData
import com.example.reqreslogin.data.model.UserResponse
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val userList: Flow<PagingData<UserResponse.UserDto>>? = null
)
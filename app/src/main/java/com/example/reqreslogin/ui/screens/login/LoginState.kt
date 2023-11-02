package com.example.reqreslogin.ui.screens.login

data class LoginState(
    val token: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

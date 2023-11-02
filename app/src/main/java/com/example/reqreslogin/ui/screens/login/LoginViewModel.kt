package com.example.reqreslogin.ui.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reqreslogin.data.model.LoginRequest
import com.example.reqreslogin.data.repository.ReqResRepository
import com.example.reqreslogin.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val reqResRepository: ReqResRepository
): ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun login(email: String, password: String) {
        val response = reqResRepository.login(
            LoginRequest(
                email = email,
                password = password
            )
        )
        response.onEach { result ->
            when(result) {
                is DataResult.Success -> {
                    _state.value = LoginState(
                        token = result.data?.token.orEmpty(),
                        isLoading = false,
                        isError = false,
                        errorMessage = ""
                    )
                }
                is DataResult.Error -> {
                    _state.value = LoginState(
                        isLoading = false,
                        isError = true,
                        errorMessage = result.message.orEmpty()
                    )
                }
                is DataResult.Loading -> {
                    _state.value = LoginState(
                        isLoading = true,
                        isError = false,
                        errorMessage = ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
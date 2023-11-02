package com.example.reqreslogin.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.reqreslogin.data.repository.ReqResRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reqResRepository: ReqResRepository
): ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getPositionsList()
    }

    private fun getPositionsList() {
        val userList = reqResRepository.getUsers()
        _state.value = HomeState(
            userList = userList
        )
    }
}
package com.example.reqreslogin.data.repository

import com.example.reqreslogin.data.api.ReqResApi
import com.example.reqreslogin.data.model.LoginRequest
import com.example.reqreslogin.data.model.LoginResponse
import com.example.reqreslogin.data.model.UserResponse
import com.example.reqreslogin.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReqResRepositoryImpl @Inject constructor(
    private val reqResApi: ReqResApi
): ReqResRepository {
    override fun login(request: LoginRequest): Flow<DataResult<LoginResponse>> = flow {
        try {
            emit(DataResult.Loading())
            val result = reqResApi.login(request)
            emit(DataResult.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(DataResult.Error(
                message = "Failed to login"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                DataResult.Error(
                    message = "Failed to login"
                ))
        }
    }

    override fun getUsers(page: Int): Flow<DataResult<UserResponse>> = flow {
        try {
            emit(DataResult.Loading())
            val result = reqResApi.getUsers(page)
            emit(DataResult.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(DataResult.Error(
                message = "Failed to retrieve users"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                DataResult.Error(
                    message = "Failed to retrieve users"
                ))
        }
    }

}
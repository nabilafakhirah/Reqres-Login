package com.example.reqreslogin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.reqreslogin.data.api.ReqResApi
import com.example.reqreslogin.data.model.LoginRequest
import com.example.reqreslogin.data.model.LoginResponse
import com.example.reqreslogin.data.model.UserResponse
import com.example.reqreslogin.data.paging.UserPaging
import com.example.reqreslogin.utils.DataResult
import com.example.reqreslogin.utils.PAGE_SIZE
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

    override fun getUsers(): Flow<PagingData<UserResponse.UserDto>> = Pager (
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE
        ),
        pagingSourceFactory = {
            UserPaging(
                reqResApi = reqResApi,
            )
        }
    ).flow
}
package com.example.reqreslogin.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reqreslogin.data.api.ReqResApi
import com.example.reqreslogin.data.model.UserResponse
import com.example.reqreslogin.utils.STARTING_INDEX
import retrofit2.HttpException
import java.io.IOException

class UserPaging(
    private val reqResApi: ReqResApi
) : PagingSource<Int, UserResponse.UserDto>() {
    override fun getRefreshKey(state: PagingState<Int, UserResponse.UserDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse.UserDto> {
        val position = params.key ?: STARTING_INDEX
        return try {
            val data = reqResApi.getUsers(
                page = position
            )
            val nextKey = if (data.data.isEmpty()) {
                null
            } else {
                position + 1
            }
            val prevKey = if (position == STARTING_INDEX) null else position - 1
            LoadResult.Page(
                data = data.data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}
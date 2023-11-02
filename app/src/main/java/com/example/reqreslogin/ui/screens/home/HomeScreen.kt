package com.example.reqreslogin.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.reqreslogin.data.model.UserResponse
import com.example.reqreslogin.ui.screens.home.widget.UserItemView

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val userList = state.value.userList?.collectAsLazyPagingItems()

    Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
        if (userList != null) {
            ListContent(
                users = userList,
            )
        }
    }
}

@Composable
private fun ListContent(
    users: LazyPagingItems<UserResponse.UserDto>,
    modifier: Modifier = Modifier,
) {
    val result = handlePagingResult(users = users)
    if (result) {
        LazyColumn(
            modifier = modifier.padding(vertical = 16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = users,
                key = { user ->
                    user.email.orEmpty()
                }
            ) { position ->
                position?.let {
                    UserItemView(
                        user = it,
                    )
                }
            }
        }
    }
}

@Composable
private fun handlePagingResult(
    users: LazyPagingItems<UserResponse.UserDto>,
): Boolean {
    users.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                //LoadingView()
                false
            }
            error != null -> {
                false
            }
            users.itemCount < 1 -> {
                //EmptyView()
                false
            }
            else -> true
        }
    }
}
package com.example.reqreslogin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reqreslogin.ui.navigation.Destinations.LOGIN_PAGE_ROUTE
import com.example.reqreslogin.ui.navigation.Destinations.USER_LIST_ROUTE
import com.example.reqreslogin.ui.screens.home.HomeScreen
import com.example.reqreslogin.ui.screens.login.LoginScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    isLogin: Boolean
) {
    val startDestination = if (isLogin) {
        USER_LIST_ROUTE
    } else {
        LOGIN_PAGE_ROUTE
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(LOGIN_PAGE_ROUTE) {
            LoginScreen(navController = navController)
        }
        composable(USER_LIST_ROUTE) {
            HomeScreen(navController = navController)
        }
    }
}

object Destinations {
    const val LOGIN_PAGE_ROUTE = "login_page_route"
    const val USER_LIST_ROUTE = "user_list_route"
}
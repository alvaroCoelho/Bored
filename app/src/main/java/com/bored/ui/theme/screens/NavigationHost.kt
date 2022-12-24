package com.bored.ui.theme.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bored.ui.theme.screens.Destinations.HomeScreen
import com.bored.ui.theme.screens.Destinations.MyActivitiesScreen
import com.bored.util.Constants.HOME_SCREEN
import com.bored.util.Constants.MY_ACTITVITIES_SCREEN
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavigationHost(navController: NavHostController, scope: CoroutineScope){

    NavHost(navController = navController, startDestination = HomeScreen.route){
        composable(HomeScreen.route){
           HomeScreen(navController, scope)
        }
         composable(MyActivitiesScreen.route){
            MyActitivitiesScreen()
        }
    }

}

@Composable
fun currentRoute(navController: NavHostController): String?{
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route

}


sealed class Destinations(
    val route: String,
    val title: String
){
    object HomeScreen: Destinations(HOME_SCREEN, "Home")
    object MyActivitiesScreen: Destinations(MY_ACTITVITIES_SCREEN, "My Activities")
}
package com.bored

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bored.ui.theme.screens.Destinations
import com.bored.ui.theme.screens.Destinations.HomeScreen
import com.bored.ui.theme.screens.Destinations.MyActivitiesScreen
import com.bored.ui.theme.screens.NavigationHost
import com.bored.ui.theme.screens.currentRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {

                Row {
                    MainScreen()
                }
            }


        }
    }
}
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed))
    val navigationsItems = listOf(
        HomeScreen,
        MyActivitiesScreen
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState)},
        drawerContent = { Drawer(scope,scaffoldState,navController, items = navigationsItems)}
    ) {
        NavigationHost(navController, scope)
    }
}

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name))},
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu , contentDescription ="menu icon" )
                
            }
        }
    )
}

@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    items: List<Destinations>

){
    Column {
        val currentRoute = currentRoute(navController)
        items.forEach{ item ->
            DrawerItem(
                item = item,
                selected = currentRoute == item.route){
                navController.navigate(item.route){
                    launchSingleTop = true
                }
                scope.launch{
                    scaffoldState.drawerState.close()
                }
            }
        }
    }
}

@Composable
fun DrawerItem(
    item: Destinations,
    selected:Boolean,
    onItemClick: (Destinations) -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(10.dp)
            .clickable { onItemClick(item) }
    ) {
        Text(text = item.title)
    }

}
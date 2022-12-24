package com.bored.ui.theme.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bored.R
import com.bored.data.model.ActivityModel
import com.bored.ui.theme.viewModel.ActivityState
import com.bored.ui.theme.viewModel.RandomActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    navHostController: NavHostController,
     coroutineScope: CoroutineScope,
    viewModel: RandomActivityViewModel = hiltViewModel()
) {

    when (val currentState = viewModel.activity.value) {
        ActivityState.Loading -> RandomLoading()
        is ActivityState.Success -> RandowActivity(
            currentState.activity,
            viewModel,
            coroutineScope,
            navHostController
        )
        is ActivityState.Empty -> RandomActivityEmpty()

    }


}
@Composable
fun RandowActivity(
    activity: ActivityModel,
    viewModel: RandomActivityViewModel,
    coroutineScope: CoroutineScope,
    navHostController: NavHostController){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val context = LocalContext.current

        Row {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.select_to_search)
            )
            DropDownMenuIypesActivities(viewModel, coroutineScope)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                modifier = Modifier
                    .padding(30.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.suggested_activity)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(2.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = activity.activity
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Button(onClick = {
                activity.status = context.getString(R.string.in_progress)
                activity.timeSpent = System.currentTimeMillis()
                viewModel.insert(activity).also {
                    Toast.makeText(context, context.getString(R.string.activity_saved), Toast.LENGTH_LONG).show()
                }
                navHostController.navigate(Destinations.MyActivitiesScreen.route)
            }) {
             Text(text = stringResource(R.string.start))
            }
        }
    }


}

@Composable
fun RandomActivityEmpty() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 5.dp)
            .background(Color.White)
            .padding(0.dp, 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Empty",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}

@Composable
fun RandomLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 5.dp)
            .background(Color.White)
    ) {


        Text(
            text = stringResource(R.string.loading),
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}

@Composable
fun DropDownMenuIypesActivities(
    viewModel: RandomActivityViewModel,
    coroutineScope: CoroutineScope
){

    val listActivityType = listOf(
        stringResource(R.string.education),
        stringResource(R.string.recreational),
        stringResource(R.string.social),
        stringResource(R.string.diy),
        stringResource(R.string.charity),
        stringResource(R.string.cooking),
        stringResource(R.string.relaxation),
        stringResource(R.string.music),
        stringResource(R.string.busywork)
    )

    val disabledItem = 1
    val contextForToast = LocalContext.current.applicationContext

    var expanded by remember { mutableStateOf(false)  }

    Box(
        contentAlignment = Alignment.Center
    ) {

        IconButton(onClick = {
            expanded = true
        }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = ""
            )
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            listActivityType.forEachIndexed { itemIndex, itemValue ->
                DropdownMenuItem(
                    onClick = {

                        coroutineScope.launch{
                            viewModel.loadRandomActivityByType(itemValue)
                        }

                        Toast.makeText(contextForToast, itemValue, Toast.LENGTH_SHORT)
                            .show()
                        expanded = false
                    },
                    enabled = (itemIndex != disabledItem)
                ) {
                    Text(text = itemValue)
                }
            }
        }
    }
}

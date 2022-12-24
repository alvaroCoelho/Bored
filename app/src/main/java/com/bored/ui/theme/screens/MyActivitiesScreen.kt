package com.bored.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.bored.R
import com.bored.data.model.ActivityModel
import com.bored.ui.theme.viewModel.MyActivitiesState
import com.bored.ui.theme.viewModel.MyActivitiesViewModel
import com.bored.util.formatMilliSecondsHHmm


@Composable
fun MyActitivitiesScreen(viewModel: MyActivitiesViewModel = hiltViewModel()) {

    when (val currentState = viewModel.list.value) {
        MyActivitiesState.Loading -> ActivitiesLoading()
        is MyActivitiesState.Success -> ListMyActivities( currentState.myActivities, viewModel)
        is MyActivitiesState.Empty -> ActivitiesEmpty()
    }

}


@Composable
fun ListMyActivities(activities: List<ActivityModel>, viewModel: MyActivitiesViewModel) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {

        items(activities) { activity ->
            ItemList(activity, viewModel)
        }
    }
}

@Composable
fun ItemList(activity: ActivityModel, viewModel: MyActivitiesViewModel) {
    val context = LocalContext.current
    Column {
        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                text = activity.activity,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )
        {
            Text(
                modifier = Modifier
                    .padding(2.dp, 4.dp, 0.dp, 0.dp),
                text = activity.status
            )

            if (activity.status != context.getString(R.string.in_progress)) {
                Text(
                    modifier = Modifier
                        .padding(10.dp, 4.dp, 0.dp, 0.dp),
                    text = "Time Spent:"
                )

                val timeSpent = System.currentTimeMillis() - activity.timeSpent
                Text(
                    modifier = Modifier
                        .padding(2.dp, 4.dp, 0.dp, 0.dp),
                    text = "${formatMilliSecondsHHmm(timeSpent)} Min"
                )
            } else {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                ) {
                    Button(onClick = {
                    activity.status = context.getString(R.string.done)
                    viewModel.update(activity)
                    }) {
                        Text(text = stringResource(R.string.stop_activity))
                    }
                }
            }
        }
    }
}

@Composable
fun ActivitiesLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 5.dp)
            .background(Color.White)
    ) {


        Text(
            text = "Loading",
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}

@Composable
fun ActivitiesEmpty() {
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
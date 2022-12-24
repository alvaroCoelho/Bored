package com.bored.ui.theme.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bored.data.model.ActivityModel
import com.bored.ui.theme.useCase.GetAllActivitiesUseCase
import com.bored.ui.theme.useCase.UpdateActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyActivitiesViewModel @Inject constructor(
    private val getAllActivitiesUseCase: GetAllActivitiesUseCase,
    private val updateActivityUseCase: UpdateActivityUseCase
): ViewModel(){

    private val _list: MutableState<MyActivitiesState> = mutableStateOf(MyActivitiesState.Loading)
    val list: State<MyActivitiesState> = _list

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        getAllActivitiesUseCase.invoke().collectLatest {
            if (it.isNullOrEmpty()){
                _list.value = MyActivitiesState.Empty
            }else{
                _list.value = MyActivitiesState.Success(it)
            }
        }
    }

    fun update(activityModel: ActivityModel) = viewModelScope.launch {
        updateActivityUseCase.invoke(activityModel)
    }
}


sealed interface MyActivitiesState {
    object Loading : MyActivitiesState
    class Success(
        val myActivities: List<ActivityModel>
    ) : MyActivitiesState

    class Error(
        val Message: String
    ) : MyActivitiesState

    object Empty : MyActivitiesState

}

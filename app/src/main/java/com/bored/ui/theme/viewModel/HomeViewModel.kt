package com.bored.ui.theme.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bored.data.model.ActivityModel
import com.bored.ui.theme.useCase.GetRandomActivityUseCase
import com.bored.ui.theme.useCase.SaveActivityUseCase
import com.bored.ui.theme.useCase.SearchActivitiesByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RandomActivityViewModel @Inject constructor(
private val getRandomActivityUseCase: GetRandomActivityUseCase,
private val saveActivityUseCase: SaveActivityUseCase,
private val searchActivitiesByTypeUseCase: SearchActivitiesByTypeUseCase
): ViewModel() {

    private val _activity: MutableState<ActivityState> = mutableStateOf(ActivityState.Loading)
    val activity: State<ActivityState> = _activity

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        loadRandomActivity()
    }
    private suspend fun loadRandomActivity(){
        try {
            val response =getRandomActivityUseCase.invoke()
            _activity.value = handleResponse(response)
        }catch (t: Throwable){
            when(t){
                is IOException -> _activity.value = ActivityState.Error("conexion error")
                else  -> _activity.value = ActivityState.Error("data fail")
            }
        }
    }

    suspend fun loadRandomActivityByType(type: String){
        try {
            val response = searchActivitiesByTypeUseCase.invoke(type)
            _activity.value = handleResponse(response)
        }catch (t: Throwable){
            when(t){
                is IOException -> _activity.value = ActivityState.Error("conexion error")
                else  -> _activity.value = ActivityState.Error("data fail")
            }
        }
    }


    private fun handleResponse(response: Response<ActivityModel>):ActivityState{
        if(response.isSuccessful){
            response.body()?.let { value ->
                return ActivityState.Success(value)
            }
        }
        return ActivityState.Error(response.message())
    }

    fun insert(activity: ActivityModel) = viewModelScope.launch {
        saveActivityUseCase.invoke(activity)
    }
}

sealed interface ActivityState {
    object Loading : ActivityState
    class Success(
        val activity: ActivityModel
    ) : ActivityState

    class Error(
        val Message: String
    ) : ActivityState

    object Empty : ActivityState

}
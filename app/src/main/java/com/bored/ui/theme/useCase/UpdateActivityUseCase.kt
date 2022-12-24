package com.bored.ui.theme.useCase

import com.bored.data.model.ActivityModel
import com.bored.di.UseCaseDispatcher
import com.bored.repository.ActivityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateActivityUseCase@Inject constructor(
    private val repository: ActivityRepository,
    @UseCaseDispatcher private val dispatcher: CoroutineDispatcher
)  {
    suspend operator fun invoke(activityModel: ActivityModel) = withContext(dispatcher) {
        repository.update(activityModel)
    }
}
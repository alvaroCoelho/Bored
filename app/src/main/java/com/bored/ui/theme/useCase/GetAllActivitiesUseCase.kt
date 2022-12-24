package com.bored.ui.theme.useCase

import com.bored.data.model.ActivityModel
import com.bored.di.UseCaseDispatcher
import com.bored.repository.ActivityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllActivitiesUseCase @Inject constructor(
    private val repository: ActivityRepository,
    @UseCaseDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<List<ActivityModel>> = withContext(dispatcher){
        return@withContext repository.getAll()
    }
}
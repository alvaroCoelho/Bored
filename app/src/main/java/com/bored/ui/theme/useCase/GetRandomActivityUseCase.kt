package com.bored.ui.theme.useCase

import com.bored.data.model.ActivityModel
import com.bored.di.UseCaseDispatcher
import com.bored.repository.ActivityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetRandomActivityUseCase  @Inject constructor(
    private val repository: ActivityRepository,
    @UseCaseDispatcher private val dispatcher: CoroutineDispatcher
){
    suspend operator fun invoke(): Response<ActivityModel> = withContext(dispatcher){
        return@withContext repository.getRandomActivity()
    }
}
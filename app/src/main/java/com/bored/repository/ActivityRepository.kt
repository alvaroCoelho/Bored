package com.bored.repository

import com.bored.data.local.ActivityDao
import com.bored.data.model.ActivityModel
import com.bored.data.remote.ServiceApi
import retrofit2.Response
import javax.inject.Inject

class ActivityRepository @Inject constructor(
    private val api: ServiceApi,
    private val dao: ActivityDao
) {
    suspend fun getRandomActivity(): Response<ActivityModel>{
       return api.getRandomActivity() }

    suspend fun getActivitiesByType(type: String): Response<ActivityModel>{
        return api.getActivitiesByType(type)
    }

    suspend fun insert(activityModel: ActivityModel) = dao.insert(activityModel)
    fun getAll() = dao.getAll()
    suspend fun update(activityModel: ActivityModel) = dao.update(activityModel)
    suspend fun delete(activityModel: ActivityModel) = dao.delete(activityModel)
}
package com.bored.data.remote

import com.bored.data.model.ActivityModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("/api/activity/")
    suspend fun getRandomActivity(): Response<ActivityModel>

    @GET("/api/activity")
    suspend fun getActivitiesByType(@Query("type") type: String): Response<ActivityModel>
}
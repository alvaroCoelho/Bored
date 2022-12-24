package com.bored.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bored.data.model.ActivityModel
import kotlinx.coroutines.flow.Flow


@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(activityModel: ActivityModel): Long

    @Query("SELECT * FROM Activity ORDER BY id")
    fun getAll(): Flow<List<ActivityModel>>

    @Update
    suspend fun update(activityModel: ActivityModel)

    @Delete
    suspend fun delete(activityModel: ActivityModel)
}
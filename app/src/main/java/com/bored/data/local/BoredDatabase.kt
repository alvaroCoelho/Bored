package com.bored.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bored.data.model.ActivityModel

@Database(entities = [ActivityModel::class], version = 1, exportSchema = false )
abstract class BoredDatabase: RoomDatabase() {
    abstract fun activityDao(): ActivityDao
}
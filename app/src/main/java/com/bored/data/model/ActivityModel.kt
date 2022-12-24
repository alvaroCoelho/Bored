package com.bored.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Activity")
data class ActivityModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("activity")
    val activity: String,
    @SerializedName("accessibility")
    val accessibility: Double,
    @SerializedName("type")
    val type: String,
    @SerializedName("participants")
    val participants: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("link")
    val link: String,
    @SerializedName("key")
    val key: Long,
    var status: String,
    var timeSpent: Long

) : Serializable
package com.example.mytask

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "To_Do")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var description:String,
    val dueDate: String?,
    val priority: String?,
    val category: String?,
    var taskStatus: String? = "NEW", // Default value is NEW
)
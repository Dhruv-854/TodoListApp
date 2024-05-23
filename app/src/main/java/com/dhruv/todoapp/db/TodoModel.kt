package com.dhruv.todoapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TodoModel")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val completed: Boolean
)

package com.dhruv.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {


    @Query("SELECT * FROM TodoModel")
    fun getAll(): LiveData<List<TodoModel>>

    @Insert
    fun addTask(todoModel: TodoModel)

    @Query( "DELETE FROM TodoModel WHERE id = :id")
    fun deleteTask(id: Int)



}




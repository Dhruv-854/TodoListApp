package com.dhruv.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhruv.todoapp.db.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoVIewModel : ViewModel() {


    val todoDao = MainApplication.todoDatabase.getTodoDao()

    val todoList: LiveData<List<TodoModel>> = todoDao.getAll()

    fun addTodo(title: String , completed : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTask(
                TodoModel(
                    title = title,
                    completed = false
                )
            )
        }
    }

    fun deleteTodo(id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTask(id)
        }
    }


}
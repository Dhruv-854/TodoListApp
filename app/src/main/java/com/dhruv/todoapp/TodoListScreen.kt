package com.dhruv.todoapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dhruv.todoapp.db.TodoModel
import kotlinx.coroutines.channels.ticker

@Composable
fun TodoListScreen(modifier: Modifier = Modifier, viewModel: TodoVIewModel) {
    val todoList by viewModel.todoList.observeAsState(initial = emptyList())
    var title by remember { mutableStateOf("") }


    Box(modifier = modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text(text = "Add New Task")
                    }
                )
                Button(onClick = {
                    viewModel.addTodo(
                        title = title,
                        completed = false
                    )
                    title = ""
                }) {
                    Text(text = "Add")
                }
            }
            LazyColumn(content = {

                items(todoList) { item: TodoModel ->
                    CardView(item = item) {
                        viewModel.deleteTodo(id = item.id)
                    }

                }
            })
        }
    }
}

@Composable
fun CardView(
    modifier: Modifier = Modifier,
    item: TodoModel,
    onDelete: () -> Unit = {},
) {
    var isChecked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = isChecked, onCheckedChange = { cheked ->
                isChecked = cheked
            })
            Text(text = item.title)
            IconButton(onClick = {
                onDelete.invoke()
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}
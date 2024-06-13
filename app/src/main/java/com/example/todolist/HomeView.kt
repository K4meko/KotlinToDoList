package com.example.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    val viewModel: HomeViewViewModel = viewModel()
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }


    Column {
        Row{
            Text(text = "Welcome to your To-Do List!")
            IconButton(onClick = {isSheetOpen = true}) {
                Icon(Icons.Filled.Add, contentDescription = "Add item")
            }
        }
        if (viewModel.items.isEmpty()) {
            Text(text = "You have no items in your list!")
        } else {
            viewModel.items.forEach {
                ListItem(text = it.task)
            }
        }
    }
    if (isSheetOpen) {
        ModalBottomSheet(onDismissRequest = {isSheetOpen = false}, sheetState = sheetState, modifier = Modifier.height(400.dp)) {
          Column() {
              TextField(
                  value = viewModel.newtask.value,
                  onValueChange = { viewModel.changeTask(it) },
                  modifier = Modifier.padding(16.dp).fillMaxWidth().background(Color.Transparent),
                  placeholder = { Text("Enter new task") },
                  shape = RoundedCornerShape(12.dp),
                  colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent)

              )
              IconButton(onClick = {
                  viewModel.addItem(viewModel.newtask.value)
                  isSheetOpen = false
              }) {
                  Icon(Icons.Filled.Add, contentDescription = "Add item")
              }
          }
        }
    }
    }

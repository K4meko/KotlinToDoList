package com.example.todolist

import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.ui.theme.ToDoListTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    val viewModel: HomeViewViewModel = viewModel()
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }


    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(16.dp)
        ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)
        {
            Text(text = "Welcome to your To-Do List!",
                style = TextStyle(fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                ))
            IconButton(onClick = {isSheetOpen = true},
                modifier = Modifier
                    .clip(CircleShape) // This will make the button circular
                    .background(MaterialTheme.colorScheme.primary)



            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add item", tint = MaterialTheme.colorScheme.background)
            }
        }
        if (viewModel.items.isEmpty()) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "You have no items in your list :(")
            Text(text = "Add a new item by clicking the button above!", modifier = Modifier.padding(top = 20.dp))
        } else {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start, // Align items to the left
                modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState())
            ) {
                viewModel.items.forEach {
                    ListItem(text = it.task)
                }
            }
        }
    }
    if (isSheetOpen) {
        ModalBottomSheet(onDismissRequest = {isSheetOpen = false}, sheetState = sheetState, modifier = Modifier.height(200.dp)) {
          Column(
              Modifier
                  .padding(16.dp)
                  .fillMaxWidth()
                  .fillMaxHeight(),
              verticalArrangement = Arrangement.SpaceBetween
            ) {
              TextField(
                  value = viewModel.newtask.value,
                  onValueChange = { viewModel.changeTask(it) },
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(bottom = 16.dp)


                      .background(Color.Transparent),
                  placeholder = { Text("Enter new task") },
                  shape = RoundedCornerShape(12.dp),
                  colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent)

              )

              Button(onClick = {
                  viewModel.addItem(viewModel.newtask.value)
                  isSheetOpen = false
              }
                  , modifier = Modifier
                      .fillMaxWidth()

                      .clip(RoundedCornerShape(12.dp))) {
                    Text("Add item")
              }

          }
        }
    }


    }
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ToDoListTheme {
        HomeView()
    }
}
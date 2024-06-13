package com.example.todolist
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel


class HomeViewViewModel: ViewModel() {
    public val items = mutableStateListOf<Item>()
    public val newtask =  mutableStateOf("")

    fun addItem(task: String) {
       var _item = Item(task, false)
         items.add(_item)
    }
    fun changeTask(task: String) {
        newtask.value = task
    }


}
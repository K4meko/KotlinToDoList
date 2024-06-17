package com.example.todolist

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ListItem(text: String) {
  Row(
      modifier = Modifier.wrapContentSize(Alignment.Center),
      horizontalArrangement = Arrangement.Start,
      verticalAlignment = Alignment.CenterVertically
  ){
      val checkedState = remember { mutableStateOf(false) }
      //@State var neco:String
     Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
      Text(text = text)
  }
    
}
@Preview
@Composable
fun Preview(){
    ListItem("Hello")
}

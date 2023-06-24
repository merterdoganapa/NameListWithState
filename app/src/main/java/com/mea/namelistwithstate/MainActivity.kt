package com.mea.namelistwithstate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.mea.namelistwithstate.MyViewModel
import com.mea.namelistwithstate.ui.theme.NameListWithStateTheme

class MainActivity : ComponentActivity() {

    val viewModel by lazy{ ViewModelProvider(this).get(MyViewModel::class.java)}

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val state = viewModel.state.value
            NameListWithStateTheme() {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)) {
                        items(state.namesList.size) {
                            Text(text = state.namesList[it])
                        }
                    }

                    MyTextField(state.text,
                        onValueChanged = {
                            viewModel.updateText(it)
                        }
                        , onAddClick = {
                            viewModel.updateNamesList(state.text)
                            viewModel.updateText("")
                        })
                }

            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyTextField(str : String, onValueChanged : (String) -> Unit, onAddClick : () -> Unit) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            TextField(value = str, onValueChange = {
                onValueChanged(it)
            }, modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = ""
                        ,Modifier.clickable{onAddClick()})
                }
            )
        }
    }

}



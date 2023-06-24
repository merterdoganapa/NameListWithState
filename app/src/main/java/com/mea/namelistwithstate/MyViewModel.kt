package com.mea.namelistwithstate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    val state = mutableStateOf(MyScreenState())

    fun updateText(newText :String){
        state.value = state.value.copy(text = newText)
    }

    fun updateNamesList(newName : String){
        val currentList = state.value.namesList
        currentList.add(newName)
        state.value = state.value.copy(namesList = currentList)
    }

}


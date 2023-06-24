package com.mea.namelistwithstate

data class MyScreenState(
    var text: String = "",
    val namesList: MutableList<String> = mutableListOf()
)
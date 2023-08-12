package com.example.userssp

data class User (val id:Long, var name:String, var lastname: String, var url:String ){
    fun getFullName (): String = "$name $lastname"
}
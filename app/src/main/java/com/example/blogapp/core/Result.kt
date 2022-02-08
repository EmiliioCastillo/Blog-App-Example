package com.example.blogapp.core

import java.lang.Exception

sealed class Result <out T>{
    //Retornar 3 estados
     class Loading <out T>: Result<T>()
    data class Success <out T> (val data : T) : Result <T>()
    data class Failure (val exception: Exception) : Result<Nothing>()
}

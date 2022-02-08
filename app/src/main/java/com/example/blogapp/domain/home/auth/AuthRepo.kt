package com.example.blogapp.domain.home.auth

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseUser

interface  AuthRepo {
    suspend fun SignIn(email : String , password : String) : FirebaseUser?
    suspend fun signUp(email: String, password: String, username: String): FirebaseUser?
    suspend fun updateProfile(imageBitmap : Bitmap, username : String)
}
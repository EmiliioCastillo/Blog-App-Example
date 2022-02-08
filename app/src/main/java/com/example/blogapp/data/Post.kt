package com.example.blogapp.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Post(val profile_picture: String = "",
                val profile_name:String = "",
                @ServerTimestamp
                val creater_at : Date? = null,
                val post_image: String = "",
                val post_description : String = "")
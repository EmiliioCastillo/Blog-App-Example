package com.example.blogapp.data.remote.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.Post
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestore.*
import com.google.firebase.firestore.Query
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {
        @ExperimentalCoroutinesApi
        suspend fun getLatestPosts() : Result<List<Post>> {
                val postList = mutableListOf<Post>()
                val querySnapshot = FirebaseFirestore.getInstance().collection("posts").orderBy("created_at",
                        Query.Direction.DESCENDING ).get().await()

                for(post in querySnapshot.documents){
                        post.toObject(Post::class.java)?.let {fbPost ->
                                postList.add(fbPost)
                        }
                }
                return Result.Success(postList)
        }
}
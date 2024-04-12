package com.example.tarasovartem3.Repository
import androidx.lifecycle.LiveData
import com.example.tarasovartem3.Delegation.Post


interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
}
package com.example.tarasovartem3.Viewmodel

import androidx.lifecycle.ViewModel
//import com.example.tarasovartem3.PostRepository
//import com.example.tarasovartem3.PostRepositoryInMemoryImpl
import com.example.tarasovartem3.Repository.PostRepository
import com.example.tarasovartem3.Repository.PostRepositoryInMemoryImpl


class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like()=repository.like()
    fun share()=repository.share()
}
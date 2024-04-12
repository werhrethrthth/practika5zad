package com.example.tarasovartem3.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import com.example.tarasovartem3.R
import com.example.tarasovartem3.Viewmodel.PostViewModel
import com.example.tarasovartem3.databinding.ActivityMainBinding

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

//import kotlin.android.synthetic.main.activity_main.*

class MainActivity<TextView> : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                nrav.text = post.like.toString()
                podelytsya.text = post.share.toString()
                likeimag.setImageResource(
                    if (post.likedByMe) R.drawable.like_krasn else R.drawable.like_favorite_heart_5759
                )
                nrav.text = post.like.toString()
                when {
                    post.like in 1000..999999 -> nrav.text = "${post.like / 1000}K"
                    post.like < 1000 -> nrav.text = post.like.toString()
                    else -> nrav.text = String.format("%.1fM", post.like.toDouble() / 1000000)
                }
                podelytsya.text = post.share.toString()
                when {
                    post.share < 1000 -> podelytsya.text = post.share.toString()
                    post.share in 1000..999999 -> podelytsya.text = "${post.share / 1000}K"
                    else -> podelytsya.text = String.format(
                        "%.1fM", post.share.toDouble() / 1000000
                    )
                }
            }
            binding.likeimag.setOnClickListener {
                viewModel.like()
            }
            binding.podelitsya.setOnClickListener {
                viewModel.share()
            }
        }
    }
////
    @MainThread
    public inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }

        return ViewModelLazy(
            VM::class,
            { viewModelStore },
            factoryPromise,
            { this.defaultViewModelCreationExtras }
        )
    }
}





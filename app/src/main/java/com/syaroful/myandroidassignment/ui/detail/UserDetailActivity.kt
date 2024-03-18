package com.syaroful.myandroidassignment.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.syaroful.myandroidassignment.data.response.DetailUserResponse
import com.syaroful.myandroidassignment.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    private val viewModel by viewModels<UserDetailViewModel>()

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        if (username != null) {
            viewModel.findUserDetail(username)
        }
        viewModel.userDetail.observe(this) {
            setUserData(it)
        }
    }

    private fun setUserData(user: DetailUserResponse) {
        binding.apply {
            tvDetailUsername.text = user.login
            tvDetailUrl.text = user.url
            tvFollowingCount.text = user.following.toString()
            tvFollowersCount.text = user.followers.toString()
            Glide.with(this@UserDetailActivity)
                .load(user.avatarUrl)
                .into(imgDetailUser)
        }

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
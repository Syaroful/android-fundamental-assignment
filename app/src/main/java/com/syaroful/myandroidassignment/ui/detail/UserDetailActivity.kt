package com.syaroful.myandroidassignment.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.syaroful.myandroidassignment.R
import com.syaroful.myandroidassignment.data.response.DetailUserResponse
import com.syaroful.myandroidassignment.databinding.ActivityUserDetailBinding
import com.syaroful.myandroidassignment.ui.ViewModelFactory

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    private val viewModel by viewModels<UserDetailViewModel> {
        ViewModelFactory.getInstance(application)
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_URL = "extra_url"
        const val EXTRA_AVATAR = "EXTRA_AVATAR"
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = intent.getStringExtra(EXTRA_USERNAME)
        val avatarUrl = intent.getStringExtra(EXTRA_AVATAR)
        val url = intent.getStringExtra(EXTRA_URL)

        if (username != null) {
            viewModel.findUserDetail(username)
        }
        viewModel.userDetail.observe(this) {
            setUserData(it)
        }
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
        username?.let { viewModel.isUserFavorite(username = it) }
        val sectionsPagerAdapter = SectionsPagerAdapter(this, username ?: "")
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    private fun setUserData(user: DetailUserResponse) {
        binding.apply {
            tvDetailUsername.text = user.login
            tvDetailName.text = user.name
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
package com.syaroful.myandroidassignment.ui.favorite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.syaroful.myandroidassignment.R
import com.syaroful.myandroidassignment.data.response.ItemsItem
import com.syaroful.myandroidassignment.databinding.ActivityFavoriteBinding
import com.syaroful.myandroidassignment.databinding.ActivitySettingBinding
import com.syaroful.myandroidassignment.ui.ViewModelFactory
import com.syaroful.myandroidassignment.ui.home.UserAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter

    private val viewModel by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      setup recycleview
        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)
//
        adapter = UserAdapter()
        setFavoriteUsers()
    }

    private fun setFavoriteUsers() {
        viewModel.getFavoritesUsers().observe(this) { listFavoriteUsers ->
            val items = arrayListOf<ItemsItem>()
            listFavoriteUsers.map {
                val item = ItemsItem(
                    login = it.username,
                    url = it.url ?: "",
                    avatarUrl = it.avatarUrl ?: ""
                )
                items.add(item)
            }
            adapter.submitList(items)
        }

        binding.rvUsers.adapter = adapter
    }
}
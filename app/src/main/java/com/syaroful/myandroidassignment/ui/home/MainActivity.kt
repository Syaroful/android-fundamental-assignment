package com.syaroful.myandroidassignment.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.syaroful.myandroidassignment.R
import com.syaroful.myandroidassignment.data.response.ItemsItem
import com.syaroful.myandroidassignment.databinding.ActivityMainBinding
import com.syaroful.myandroidassignment.ui.favorite.FavoriteActivity
import com.syaroful.myandroidassignment.ui.setting.SettingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        supportActionBar?.hide()

//        setup recycleview
        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)

//        setup viewmodel observe
        mainViewModel.users.observe(this) { users ->
            setUserData(users)
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        binding.searchBar.inflateMenu(R.menu.menu_item)
        binding.searchBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favMenu -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.settingMenu -> {
                    val intent = Intent(this, SettingActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
//        setup searchbar
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchView.hide()
                    mainViewModel.findUser(searchView.text.toString())
                    false
                }
        }

    }

    private fun setUserData(users: List<ItemsItem>?) {
        if (users != null) {
            userAdapter = UserAdapter()
            userAdapter.submitList(users)
            binding.rvUsers.adapter = userAdapter
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
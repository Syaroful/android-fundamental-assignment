package com.syaroful.myandroidassignment.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syaroful.myandroidassignment.data.response.ItemsItem
import com.syaroful.myandroidassignment.data.response.UserResponse
import com.syaroful.myandroidassignment.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _users = MutableLiveData<List<ItemsItem>>()
    val users: LiveData<List<ItemsItem>> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
        private const val DEFAULT = "syaroful"
    }

    init {
        findUser()
    }

    private fun findUser(query: String? = null) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUser(query ?: DEFAULT)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _users.value = response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}

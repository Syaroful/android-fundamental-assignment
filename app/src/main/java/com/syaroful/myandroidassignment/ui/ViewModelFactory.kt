package com.syaroful.myandroidassignment.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syaroful.myandroidassignment.data.FavoriteUserRepository
import com.syaroful.myandroidassignment.di.Injection
import com.syaroful.myandroidassignment.ui.detail.UserDetailViewModel
import com.syaroful.myandroidassignment.ui.favorite.FavoriteViewModel

class ViewModelFactory(private val favoriteUserRepository: FavoriteUserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UserDetailViewModel::class.java) -> {
                UserDetailViewModel(favoriteUserRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(favoriteUserRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
        }

    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}
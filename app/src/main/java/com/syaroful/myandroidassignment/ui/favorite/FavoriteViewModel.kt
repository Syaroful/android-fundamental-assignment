package com.syaroful.myandroidassignment.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.syaroful.myandroidassignment.data.FavoriteUserRepository
import com.syaroful.myandroidassignment.data.local.entity.FavoriteUserEntity

class FavoriteViewModel(private val favoriteUserRepository: FavoriteUserRepository) : ViewModel() {

    fun getFavoritesUsers(): LiveData<List<FavoriteUserEntity>>{
        return  favoriteUserRepository.getFavoriteUsers()
    }

}
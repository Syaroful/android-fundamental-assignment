package com.syaroful.myandroidassignment.di

import android.content.Context
import com.syaroful.myandroidassignment.data.FavoriteUserRepository
import com.syaroful.myandroidassignment.data.local.room.FavoriteUserDatabase
import com.syaroful.myandroidassignment.data.retrofit.ApiConfig
import com.syaroful.myandroidassignment.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): FavoriteUserRepository {
        val apiService = ApiConfig.getApiService()
        val database = FavoriteUserDatabase.getDatabase(context)
        val dao = database.favoriteUserDao()
        val appExecutors = AppExecutors()
        return FavoriteUserRepository.getInstance(dao, appExecutors)
    }
}
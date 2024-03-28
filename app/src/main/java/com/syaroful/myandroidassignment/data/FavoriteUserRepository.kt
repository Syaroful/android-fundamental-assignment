package com.syaroful.myandroidassignment.data

import androidx.lifecycle.LiveData
import com.syaroful.myandroidassignment.data.local.entity.FavoriteUserEntity
import com.syaroful.myandroidassignment.data.local.room.FavoriteUserDao
import com.syaroful.myandroidassignment.utils.AppExecutors

class FavoriteUserRepository private constructor(
    private val favoriteUserDao: FavoriteUserDao,
    private val appExecutors: AppExecutors,
) {

    fun getFavoriteUsers(): LiveData<List<FavoriteUserEntity>> {
        return favoriteUserDao.getFavoriteUsers()
    }

    fun addFavoriteUser(user: FavoriteUserEntity) {
        appExecutors.diskIO.execute {
            favoriteUserDao.addFavoriteUser(user)
        }
    }

    fun isUserFavorite(username: String): LiveData<List<FavoriteUserEntity>> {
        return favoriteUserDao.isUserFavorited(username)
    }

    fun deleteFavoriteUser(user: FavoriteUserEntity) {
        appExecutors.diskIO.execute {
            favoriteUserDao.deleteFavoriteUser(user)
        }
    }

    companion object {
        @Volatile
        private var instance: FavoriteUserRepository? = null
        fun getInstance(
            newsDao: FavoriteUserDao,
            appExecutors: AppExecutors
        ): FavoriteUserRepository =
            instance ?: synchronized(this) {
                instance ?: FavoriteUserRepository(newsDao, appExecutors)
            }.also { instance = it }
    }


}
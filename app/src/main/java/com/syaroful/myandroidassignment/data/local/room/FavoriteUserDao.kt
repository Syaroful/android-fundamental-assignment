package com.syaroful.myandroidassignment.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.syaroful.myandroidassignment.data.local.entity.FavoriteUserEntity

@Dao
interface FavoriteUserDao {
    @Query("SELECT * FROM favorite_users ORDER BY username DESC")
    fun getNews(): LiveData<List<FavoriteUserEntity>>

    @Query("SELECT * FROM favorite_users where favorite = 1")
    fun getFavorite(): LiveData<List<FavoriteUserEntity>>

    fun insertNews(favoriteUser: List<FavoriteUserEntity>)

    @Update
    fun updateNews(favoriteUser: FavoriteUserEntity)

    @Query("DELETE FROM favorite_users WHERE favorite = 0")
    fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM favorite_users WHERE username = username AND favorite = 1)")
    fun isNewsBookmarked(title: String): Boolean
}

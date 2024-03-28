package com.syaroful.myandroidassignment.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.syaroful.myandroidassignment.data.local.entity.FavoriteUserEntity

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavoriteUser(favoriteUser: FavoriteUserEntity)

    @Query("SELECT * FROM favorite_users ORDER BY username ASC")
    fun getFavoriteUsers(): LiveData<List<FavoriteUserEntity>>

    @Update
    fun updateFavoriteUser(favoriteUser: FavoriteUserEntity)

    @Delete
    fun deleteFavoriteUser(favoriteUser: FavoriteUserEntity)

    @Query("SELECT * FROM favorite_users WHERE username = :username")
    fun isUserFavorited(username: String): LiveData<List<FavoriteUserEntity>>


}
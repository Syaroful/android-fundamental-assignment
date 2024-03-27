package com.syaroful.myandroidassignment.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syaroful.myandroidassignment.data.local.entity.FavoriteUserEntity

@Database(entities = [FavoriteUserEntity::class], version = 1, exportSchema = false)
abstract class FavoriteUserDatabase: RoomDatabase() {

}
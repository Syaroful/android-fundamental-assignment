package com.syaroful.myandroidassignment.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_users")
data class FavoriteUserEntity(
    @field:ColumnInfo(name = "username")
    @field:PrimaryKey(autoGenerate = false)
    val username: String,

    @field:ColumnInfo(name = "avatarUrl")
    val avatarUrl: String? = null,

    @field:ColumnInfo(name = "name")
    val name: String? = null,

    @field:ColumnInfo(name = "favorite")
    var isFavorite: Boolean

)

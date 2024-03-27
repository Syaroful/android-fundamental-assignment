package com.syaroful.myandroidassignment.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object Utils {
    @SuppressLint("SimpleDateFormat")
    fun toSimpleString(date: Date?): String {
        return SimpleDateFormat("EEE, dd MMM yyy").format(date)
    }
}
package com.syaroful.myandroidassignment.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class UtilsTest {

    @Test
    fun toSimpleString() {
        val dateFormat: DateFormat = SimpleDateFormat("MM/dd/yyyy")
        var date: Date? = null
        try {
            date = dateFormat.parse("02/28/2018")
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        assertEquals("Wed, 28 Feb 2018", Utils.toSimpleString(date))
    }
}
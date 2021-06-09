package com.manapps.mandroid.moviesapp_mvc_ret.utils

import java.text.SimpleDateFormat

object DateFormatter {

    fun convertStringDate(string: String?, inputFormat: String?, outputFormat: String?): String? {
        var dateTime = ""
        dateTime = try {
            val dateFormat = SimpleDateFormat(outputFormat)
            val date = SimpleDateFormat(inputFormat).parse(string)
            dateFormat.format(date)
        } catch (e: Exception) {
            ""
        }
        return dateTime
    }


}
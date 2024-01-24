package android.com.baseapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object Utils {

    fun getStringFromDate(format: String, date: Date): String {
        val outputDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return outputDateFormat.format(date)
    }
}
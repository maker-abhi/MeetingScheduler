package com.vinsol.meetingscheduler.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.parseDate(format: String? = "dd/MM/yyyy", locale: Locale = Locale.getDefault()): Date? {
    if (this == "0001-01-01") {
        return null
    }
    val df = SimpleDateFormat(format, locale)
    var fecha: Date? = null
    try {
        fecha = df.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return fecha
}

fun Date.formatDate(format: String? = "dd/MM/yyyy", locale: Locale = Locale.getDefault()): String {
    val df = SimpleDateFormat(format, locale)
    return df.format(this)
}

fun Calendar.compareOnlyTimeTo(other: Calendar): Int {
    val hourDiff = this.get(Calendar.HOUR_OF_DAY) - other.get(Calendar.HOUR_OF_DAY)
    if (hourDiff != 0) return hourDiff

    return this.get(Calendar.MINUTE) - other.get(Calendar.MINUTE)
}
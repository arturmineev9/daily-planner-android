package ru.arturmineev9.dailyplanner.core.common.datetime

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

object DateTimeUtils {

    fun getStartOfDay(timestamp: Long): Long {
        val date = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    fun getEndOfDay(timestamp: Long): Long {
        val date = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        return date.atTime(23, 59, 59, 999)
            .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    fun isToday(timestamp: Long): Boolean {
        val zoneId = ZoneId.systemDefault()
        val today = LocalDate.now(zoneId)
        val targetDate = Instant.ofEpochMilli(timestamp).atZone(zoneId).toLocalDate()
        return today == targetDate
    }

    fun getCurrentHour(): Int = LocalTime.now().hour

}

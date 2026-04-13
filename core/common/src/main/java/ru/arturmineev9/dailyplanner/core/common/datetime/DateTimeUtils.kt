package ru.arturmineev9.dailyplanner.core.common.datetime

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

object DateTimeUtils {

    private const val MAX_HOUR = 23
    private const val MAX_MINUTE = 59
    private const val MAX_SECOND = 59
    private const val MAX_MILLIS = 999

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
        return date.atTime(MAX_HOUR, MAX_MINUTE, MAX_SECOND, MAX_MILLIS)
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    }

    fun isToday(timestamp: Long): Boolean {
        val zoneId = ZoneId.systemDefault()
        val today = LocalDate.now(zoneId)
        val targetDate = Instant.ofEpochMilli(timestamp).atZone(zoneId).toLocalDate()
        return today == targetDate
    }

    fun getCurrentHour(): Int = LocalTime.now().hour
}

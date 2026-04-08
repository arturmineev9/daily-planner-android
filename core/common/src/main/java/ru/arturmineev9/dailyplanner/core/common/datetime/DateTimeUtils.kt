package ru.arturmineev9.dailyplanner.core.common.datetime

import java.time.Instant
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
}

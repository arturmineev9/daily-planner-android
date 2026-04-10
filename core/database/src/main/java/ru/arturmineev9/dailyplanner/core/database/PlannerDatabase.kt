package ru.arturmineev9.dailyplanner.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.arturmineev9.dailyplanner.core.database.dao.TaskDao

import ru.arturmineev9.dailyplanner.core.database.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PlannerDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
}

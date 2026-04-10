package ru.arturmineev9.dailyplanner.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.arturmineev9.dailyplanner.core.database.PlannerDatabase
import ru.arturmineev9.dailyplanner.core.database.dao.TaskDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "planner_db"

    @Provides
    @Singleton
    fun providePlannerDatabase(
        @ApplicationContext context: Context
    ): PlannerDatabase {
        return Room.databaseBuilder(
            context,
            PlannerDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideChatDao(database: PlannerDatabase): TaskDao {
        return database.taskDao
    }
}

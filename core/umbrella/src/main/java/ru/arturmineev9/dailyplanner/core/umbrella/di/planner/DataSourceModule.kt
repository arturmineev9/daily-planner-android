package ru.arturmineev9.dailyplanner.core.umbrella.di.planner

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.arturmineev9.dailyplanner.feature.planner.api.data.PlannerLocalDataSource
import ru.arturmineev9.dailyplanner.feature.planner.impl.data.datasource.PlannerLocalDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindPlannerDataSource(impl:
        PlannerLocalDataSourceImpl
    ): PlannerLocalDataSource
}

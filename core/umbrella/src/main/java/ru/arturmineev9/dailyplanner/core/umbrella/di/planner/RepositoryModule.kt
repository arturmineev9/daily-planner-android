package ru.arturmineev9.dailyplanner.core.umbrella.di.planner

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.repository.PlannerRepository
import ru.arturmineev9.dailyplanner.feature.planner.impl.data.repository.PlannerRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPlannerRepository(
        impl: PlannerRepositoryImpl
    ): PlannerRepository
}

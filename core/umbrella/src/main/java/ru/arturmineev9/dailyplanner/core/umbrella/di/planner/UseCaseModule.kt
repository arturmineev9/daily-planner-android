package ru.arturmineev9.dailyplanner.core.umbrella.di.planner

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.GetTasksByDateUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
/*
    @Binds
    abstract fun bindGetTasksUseCase(impl: GetTasksByDateUseCaseImpl): GetTasksByDateUseCase

    @Binds
    abstract fun bindAddTaskUseCase(impl: AddTaskUseCaseImpl): AddTaskUseCase

    @Binds
    abstract fun bindGetTaskByIdUseCase(impl: GetTaskByIdUseCaseImpl): GetTaskByIdUseCase

    @Binds
    abstract fun bindSeedTasksUseCase(impl: SeedTasksUseCaseImpl): SeedTasksUseCase
*/
}
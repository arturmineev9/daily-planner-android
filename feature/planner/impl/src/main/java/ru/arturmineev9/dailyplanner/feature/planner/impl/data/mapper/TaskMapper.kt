package ru.arturmineev9.dailyplanner.feature.planner.impl.data.mapper

import ru.arturmineev9.dailyplanner.core.database.entity.TaskEntity
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.model.Task

fun TaskEntity.toDomain(): Task {
    return Task(
        id = id,
        dateStart = dateStart,
        dateFinish = dateFinish,
        name = name,
        description = description
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        dateStart = dateStart,
        dateFinish = dateFinish,
        name = name,
        description = description
    )
}

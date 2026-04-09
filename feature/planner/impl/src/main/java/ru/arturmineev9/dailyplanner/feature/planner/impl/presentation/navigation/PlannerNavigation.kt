package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.navigation.PlannerRoute
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.navigation.TaskDetailRoute
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.navigation.CreateTaskRoute
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.ui.PlannerRoute as PlannerRouteScreen

fun NavGraphBuilder.plannerGraph(navController: NavHostController) {
    composable<PlannerRoute> {
        PlannerRouteScreen(
            navigateToDetails = { id ->
                navController.navigate(TaskDetailRoute(id))
            },
            navigateToCreateTask = {
                navController.navigate(CreateTaskRoute)
            }
        )
    }

    composable<TaskDetailRoute> {
    }

    composable<CreateTaskRoute> {
    }
}
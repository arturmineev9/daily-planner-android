package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.navigation.PlannerRoute
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.navigation.TaskDetailRoute
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.navigation.CreateTaskRoute
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.PlannerRoute as PlannerRouteScreen
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui.CreateTaskRoute as CreateTaskRouteScreen
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.details.ui.TaskDetailRoute as TaskDetailRouteScreen

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
        TaskDetailRouteScreen(
            navigateBack = { navController.popBackStack() }
        )
    }

    composable<CreateTaskRoute> {
        CreateTaskRouteScreen(
            navigateBack = { navController.popBackStack() }
        )
    }
}

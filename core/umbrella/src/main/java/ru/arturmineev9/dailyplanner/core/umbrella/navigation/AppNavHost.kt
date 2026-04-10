package ru.arturmineev9.dailyplanner.core.umbrella.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.navigation.plannerGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Any
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        plannerGraph(navController)
    }
}

package ru.arturmineev9.dailyplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.arturmineev9.dailyplanner.core.umbrella.navigation.AppNavHost
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.navigation.PlannerRoute
import ru.arturmineev9.dailyplanner.ui.theme.DailyPlannerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyPlannerTheme {
                val navController = rememberNavController()
                AppNavHost(
                    navController = navController,
                    startDestination = PlannerRoute
                )
            }
        }
    }
}

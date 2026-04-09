package ru.arturmineev9.dailyplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import ru.arturmineev9.dailyplanner.core.umbrella.navigation.AppNavHost
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.navigation.PlannerRoute
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DailyPlannerTheme {
        Greeting("Android")
    }
}
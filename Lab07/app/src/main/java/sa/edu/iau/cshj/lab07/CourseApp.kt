package sa.edu.iau.cshj.lab07

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import sa.edu.iau.cshj.lab07.ui.CampusNewsRoute
import sa.edu.iau.cshj.lab07.ui.CourseRatingRoute

@Serializable object Home
@Serializable data class CourseRating(val code: String)
@Serializable object CampusNews

@Composable
fun HomeScreen(onRateClick: () -> Unit, onNewsClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Course Feedback", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onRateClick) { Text("Rate CSC 402") }
        Button(onClick = onNewsClick) { Text("Campus news") }
    }
}

@Composable
fun HomeRoute(onRateClick: () -> Unit, onNewsClick: () -> Unit) {
    HomeScreen(onRateClick = onRateClick, onNewsClick = onNewsClick)
}

// Task 4 - one Activity, one NavHost. The NavHost calls Routes, never Screens,
// and it is the only place that touches navController.
@Composable
fun CourseApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home, modifier = modifier) {
        composable<Home> {
            HomeRoute(
                onRateClick = { navController.navigate(CourseRating("CSC 402")) },
                onNewsClick = { navController.navigate(CampusNews) } // TODO 5
            )
        }
        composable<CourseRating> { entry ->
            CourseRatingRoute(code = entry.toRoute<CourseRating>().code)
        }
        // TODO 6: the CampusNews destination; its onBackClick pops the back stack
        composable<CampusNews> {
            CampusNewsRoute(onBackClick = { navController.popBackStack() })
        }
    }
}

package sa.edu.iau.cshj.lab05.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import sa.edu.iau.cshj.lab05.ui.CourseDetailRoute
import sa.edu.iau.cshj.lab05.ui.CourseListRoute
import sa.edu.iau.cshj.lab05.ui.HomeRoute

// Task 3 - the map of screens. Only this function knows the NavController.
@Composable
fun CourseApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home, modifier = modifier) {
        composable<Home> {
            HomeRoute(onCoursesClick = { navController.navigate(CourseList) })
        }
        composable<CourseList> {
            CourseListRoute(
                onCourseClick = { code ->
                    // TODO 1: go to the detail screen for this code
                    navController.navigate(CourseDetail(code))
                }
            )
        }
        composable<CourseDetail> { backStackEntry ->
            // TODO 2: read the route object with toRoute()
            val detail: CourseDetail = backStackEntry.toRoute()
            CourseDetailRoute(
                code = detail.code, // TODO 3: the code from TODO 2
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

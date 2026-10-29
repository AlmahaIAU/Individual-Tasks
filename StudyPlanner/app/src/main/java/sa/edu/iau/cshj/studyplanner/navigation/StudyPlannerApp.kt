package sa.edu.iau.cshj.studyplanner.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import sa.edu.iau.cshj.studyplanner.ui.PlannerHomeRoute
import sa.edu.iau.cshj.studyplanner.ui.TaskDetailRoute

/** The only place that knows the NavController. Screens never receive it. */
@Composable
fun StudyPlannerApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PlannerHome, modifier = modifier) {
        composable<PlannerHome> {
            PlannerHomeRoute(
                onTaskClick = { task -> navController.navigate(TaskDetail(task.id, task.title)) }
            )
        }
        composable<TaskDetail> { backStackEntry ->
            val detail: TaskDetail = backStackEntry.toRoute()
            TaskDetailRoute(
                taskId = detail.taskId,
                title = detail.title,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

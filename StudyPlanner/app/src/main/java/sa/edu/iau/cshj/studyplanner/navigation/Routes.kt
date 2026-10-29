package sa.edu.iau.cshj.studyplanner.navigation

import kotlinx.serialization.Serializable

@Serializable
object PlannerHome // needs nothing to open

// Part 4: the detail screen opens with the task's id (plus its title to display).
@Serializable
data class TaskDetail(val taskId: Int, val title: String)

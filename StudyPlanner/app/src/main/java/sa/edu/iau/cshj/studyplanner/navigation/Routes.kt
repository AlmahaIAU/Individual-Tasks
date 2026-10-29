package sa.edu.iau.cshj.studyplanner.navigation

import kotlinx.serialization.Serializable

@Serializable
object PlannerHome // needs nothing to open

@Serializable
data class TaskDetail(val title: String) // needs the task's title

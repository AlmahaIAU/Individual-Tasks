package sa.edu.iau.cshj.lab05.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home // needs nothing to open

@Serializable
object CourseList // needs nothing to open

@Serializable
data class CourseDetail(val code: String) // needs a course code

package sa.edu.iau.cshj.studyplanner.model

/** P4.2 - one study task. Immutable: a change always produces a new object with copy. */
data class StudyTask(
    val id: Int,
    val title: String,
    val course: String,
    val isDone: Boolean = false
)

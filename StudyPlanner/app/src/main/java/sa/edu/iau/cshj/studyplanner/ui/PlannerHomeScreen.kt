package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.model.StudyTask
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

/**
 * The home screen's state. The separate total/done counters from Part 1 are gone:
 * progress is now computed from the list - one source of truth.
 */
data class PlannerUiState(
    val greeting: String = "Hello!",
    val tasks: List<StudyTask> = emptyList(),
    val newTaskTitle: String = ""
) {
    val totalTasks: Int get() = tasks.size
    val doneTasks: Int get() = tasks.count { it.isDone }
}

/** Saves PlannerUiState (including every task) into the saved-state Bundle for rotation. */
val PlannerUiStateSaver = listSaver<PlannerUiState, Any>(
    save = { state ->
        arrayListOf(
            state.greeting,
            state.newTaskTitle,
            ArrayList(state.tasks.map { arrayListOf(it.id, it.title, it.course, it.isDone) })
        )
    },
    restore = { saved ->
        @Suppress("UNCHECKED_CAST")
        val rows = saved[2] as List<List<Any>>
        PlannerUiState(
            greeting = saved[0] as String,
            newTaskTitle = saved[1] as String,
            tasks = rows.map { StudyTask(it[0] as Int, it[1] as String, it[2] as String, it[3] as Boolean) }
        )
    }
)

val sampleTasks = listOf(
    StudyTask(1, "Lab 7 exercises", "CSC 402"),
    StudyTask(2, "ER diagram for the library", "CSC 311", isDone = true),
    StudyTask(3, "Revise process scheduling", "CSC 340"),
    StudyTask(4, "Graph theory problem set", "MATH 202", isDone = true),
    StudyTask(5, "Draft the technical report", "ENG 214"),
    StudyTask(6, "Semester App - Part 4", "CSC 402")
)

/** Stateless home screen: the list is a LazyColumn of TaskItem rows (no RecyclerView, no Adapter). */
@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskClick: (Int) -> Unit,
    onDoneChange: (Int, Boolean) -> Unit,
    onNewTaskTitleChange: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Text(
            uiState.greeting,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            "${uiState.doneTasks} of ${uiState.totalTasks} done",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = uiState.newTaskTitle,
                onValueChange = onNewTaskTitleChange,
                label = { Text("New study task") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = onAddTaskClick,
                enabled = uiState.newTaskTitle.isNotBlank(),
                modifier = Modifier.padding(start = 8.dp)
            ) { Text("Add") }
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.tasks, key = { it.id }) { task ->
                TaskItem(task = task, onTaskClick = onTaskClick, onDoneChange = onDoneChange)
            }
        }
    }
}

/** The Route owns all the state; a tick produces a NEW list with map + copy. */
@Composable
fun PlannerHomeRoute(onTaskClick: (StudyTask) -> Unit, modifier: Modifier = Modifier) {
    var uiState by rememberSaveable(stateSaver = PlannerUiStateSaver) {
        mutableStateOf(PlannerUiState(greeting = "Hello, Almaha!", tasks = sampleTasks))
    }
    PlannerHomeScreen(
        uiState = uiState,
        onTaskClick = { id ->
            uiState.tasks.firstOrNull { it.id == id }?.let(onTaskClick)
        },
        onDoneChange = { id, done ->
            uiState = uiState.copy(
                tasks = uiState.tasks.map { if (it.id == id) it.copy(isDone = done) else it }
            )
        },
        onNewTaskTitleChange = { text -> uiState = uiState.copy(newTaskTitle = text) },
        onAddTaskClick = {
            val nextId = (uiState.tasks.maxOfOrNull { it.id } ?: 0) + 1
            uiState = uiState.copy(
                tasks = uiState.tasks + StudyTask(nextId, uiState.newTaskTitle.trim(), "CSC 402"),
                newTaskTitle = ""
            )
        },
        modifier = modifier
    )
}

// P4.6 - preview 3: the home screen with five tasks
@Preview(showBackground = true, name = "Home - five tasks")
@Composable
private fun PlannerHomeFiveTasksPreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState("Hello, Almaha!", tasks = sampleTasks.take(5)),
            onTaskClick = {},
            onDoneChange = { _, _ -> },
            onNewTaskTitleChange = {},
            onAddTaskClick = {}
        )
    }
}

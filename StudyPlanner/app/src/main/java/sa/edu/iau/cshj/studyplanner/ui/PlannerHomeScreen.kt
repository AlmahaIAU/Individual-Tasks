package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

/** The home screen's state. One immutable class, every field has a default. */
data class PlannerUiState(
    val greeting: String = "Hello!",
    val totalTasks: Int = 0,
    val doneTasks: Int = 0,
    val tasks: List<String> = emptyList(),
    val newTaskTitle: String = "" // Part 3: the text field's value is hoisted into the state too
)

/**
 * Part 3 - tells rememberSaveable how to put PlannerUiState into the saved-state Bundle
 * and rebuild it after rotation (a data class cannot go into a Bundle on its own).
 */
val PlannerUiStateSaver = listSaver<PlannerUiState, Any>(
    save = { arrayListOf(it.greeting, it.totalTasks, it.doneTasks, ArrayList(it.tasks), it.newTaskTitle) },
    restore = {
        @Suppress("UNCHECKED_CAST")
        PlannerUiState(
            greeting = it[0] as String,
            totalTasks = it[1] as Int,
            doneTasks = it[2] as Int,
            tasks = it[3] as List<String>,
            newTaskTitle = it[4] as String
        )
    }
)

/**
 * Stateless: everything it shows arrives in uiState, every action leaves as a lambda.
 * Even the text field holds no state of its own - its value comes from uiState.newTaskTitle.
 */
@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onFinishTaskClick: () -> Unit,
    onTaskClick: (String) -> Unit,
    onNewTaskTitleChange: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(uiState.greeting, style = MaterialTheme.typography.headlineMedium)
        Text(
            "${uiState.doneTasks} of ${uiState.totalTasks} done",
            style = MaterialTheme.typography.titleMedium
        )
        Button(
            onClick = onFinishTaskClick,
            enabled = uiState.doneTasks < uiState.totalTasks
        ) {
            Text("Finish a task")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
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
        Text("My study tasks", style = MaterialTheme.typography.titleSmall)
        uiState.tasks.forEach { title ->
            OutlinedButton(onClick = { onTaskClick(title) }, modifier = Modifier.fillMaxWidth()) {
                Text(title)
            }
        }
    }
}

/**
 * The Route owns the state. rememberSaveable (instead of remember) keeps it
 * when the Activity is recreated on rotation.
 */
@Composable
fun PlannerHomeRoute(onTaskClick: (String) -> Unit, modifier: Modifier = Modifier) {
    var uiState by rememberSaveable(stateSaver = PlannerUiStateSaver) {
        mutableStateOf(
            PlannerUiState(
                greeting = "Hello, Almaha!",
                totalTasks = 5,
                doneTasks = 0,
                tasks = listOf(
                    "Lab 5 report",
                    "Revise Kotlin null safety",
                    "CSC 311 ER diagram",
                    "Read Lecture 6 slides",
                    "Practise rememberSaveable"
                )
            )
        )
    }
    PlannerHomeScreen(
        uiState = uiState,
        onFinishTaskClick = {
            uiState = uiState.copy(doneTasks = uiState.doneTasks + 1)
        },
        onTaskClick = onTaskClick,
        onNewTaskTitleChange = { text -> uiState = uiState.copy(newTaskTitle = text) },
        onAddTaskClick = {
            uiState = uiState.copy(
                tasks = uiState.tasks + uiState.newTaskTitle.trim(),
                totalTasks = uiState.totalTasks + 1,
                newTaskTitle = ""
            )
        },
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "Nothing done yet")
@Composable
private fun PlannerHomeNothingDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                "Hello, Almaha!", totalTasks = 3, doneTasks = 0,
                tasks = listOf("Lab 5 report", "CSC 311 ER diagram", "Read Lecture 6")
            ),
            onFinishTaskClick = {}, onTaskClick = {}, onNewTaskTitleChange = {}, onAddTaskClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Everything done, typing a new task")
@Composable
private fun PlannerHomeAllDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                "Hello, Almaha!", totalTasks = 3, doneTasks = 3,
                tasks = listOf("Lab 5 report", "CSC 311 ER diagram", "Read Lecture 6"),
                newTaskTitle = "Lab 6 exercises"
            ),
            onFinishTaskClick = {}, onTaskClick = {}, onNewTaskTitleChange = {}, onAddTaskClick = {}
        )
    }
}

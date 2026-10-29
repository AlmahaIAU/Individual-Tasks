package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

/** The home screen's state. One immutable class, every field has a default. */
data class PlannerUiState(
    val greeting: String = "Hello!",
    val totalTasks: Int = 0,
    val doneTasks: Int = 0,
    val tasks: List<String> = emptyList() // P2.4
)

/** Stateless: data in, events out as lambdas, modifier last with a default. */
@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onFinishTaskClick: () -> Unit,
    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
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
        Text("My study tasks", style = MaterialTheme.typography.titleSmall)
        // A Column with forEach is enough for now - lists come in Part 4.
        uiState.tasks.forEach { title ->
            OutlinedButton(onClick = { onTaskClick(title) }, modifier = Modifier.fillMaxWidth()) {
                Text(title)
            }
        }
    }
}

/** The Route owns the state and produces a NEW state object with copy. */
@Composable
fun PlannerHomeRoute(onTaskClick: (String) -> Unit, modifier: Modifier = Modifier) {
    var uiState by remember {
        mutableStateOf(
            PlannerUiState(
                greeting = "Hello, Almaha!",
                totalTasks = 5,
                doneTasks = 0,
                tasks = listOf(
                    "Lab 5 report",
                    "Revise Kotlin null safety",
                    "CSC 311 ER diagram",
                    "Read Lecture 5 slides",
                    "Practise Navigation Compose"
                )
            )
        )
    }
    PlannerHomeScreen(
        uiState = uiState,
        onFinishTaskClick = {
            uiState = uiState.copy(doneTasks = uiState.doneTasks + 1)
        },
        onTaskClick = onTaskClick, // the NavHost decides what a task tap means
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
                tasks = listOf("Lab 5 report", "CSC 311 ER diagram", "Read Lecture 5")
            ),
            onFinishTaskClick = {},
            onTaskClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Everything done")
@Composable
private fun PlannerHomeAllDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                "Hello, Almaha!", totalTasks = 3, doneTasks = 3,
                tasks = listOf("Lab 5 report", "CSC 311 ER diagram", "Read Lecture 5")
            ),
            onFinishTaskClick = {},
            onTaskClick = {}
        )
    }
}

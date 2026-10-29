package sa.edu.iau.cshj.studyplanner.ui

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

/** P2.5 - what the detail screen shows. */
data class TaskDetailUiState(
    val title: String = ""
)

/** P2.5 - stateless: no NavController, no Context, no Intent in here. */
@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Study task", style = MaterialTheme.typography.labelLarge)
        Text(uiState.title, style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onShareClick) { Text("Share") }
        OutlinedButton(onClick = onBackClick) { Text("Back") }
    }
}

/** P2.6 - the Route builds the share Intent inside the event lambda. */
@Composable
fun TaskDetailRoute(title: String, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    TaskDetailScreen(
        uiState = TaskDetailUiState(title = title),
        onShareClick = {
            val send = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, "Today I'm studying: $title")
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(send, "Share via"))
        },
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "Detail - Lab 5 report")
@Composable
private fun TaskDetailPreview() {
    StudyPlannerTheme {
        TaskDetailScreen(TaskDetailUiState("Lab 5 report"), onShareClick = {}, onBackClick = {})
    }
}

@Preview(showBackground = true, name = "Detail - CSC 311 ER diagram")
@Composable
private fun TaskDetailSecondPreview() {
    StudyPlannerTheme {
        TaskDetailScreen(TaskDetailUiState("CSC 311 ER diagram"), onShareClick = {}, onBackClick = {})
    }
}

package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.model.StudyTask
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme

/**
 * P4.3 - one reusable, stateless row (the Compose replacement for a RecyclerView ViewHolder).
 * Data in, events out: tapping the row reports onTaskClick(id), ticking reports onDoneChange(id, checked).
 */
@Composable
fun TaskItem(
    task: StudyTask,
    onTaskClick: (Int) -> Unit,
    onDoneChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTaskClick(task.id) }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { checked -> onDoneChange(task.id, checked) }
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (task.isDone) MaterialTheme.colorScheme.onSurfaceVariant
                    else MaterialTheme.colorScheme.onSurface,
                    textDecoration = if (task.isDone) TextDecoration.LineThrough else null,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = task.course,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// P4.6 - previews 1 and 2
@Preview(showBackground = true, name = "TaskItem - not done")
@Composable
private fun TaskItemNotDonePreview() {
    StudyPlannerTheme {
        TaskItem(
            task = StudyTask(1, "Lab 7 exercises", "CSC 402"),
            onTaskClick = {},
            onDoneChange = { _, _ -> }
        )
    }
}

@Preview(showBackground = true, name = "TaskItem - done")
@Composable
private fun TaskItemDonePreview() {
    StudyPlannerTheme {
        TaskItem(
            task = StudyTask(2, "ER diagram for the library", "CSC 311", isDone = true),
            onTaskClick = {},
            onDoneChange = { _, _ -> }
        )
    }
}

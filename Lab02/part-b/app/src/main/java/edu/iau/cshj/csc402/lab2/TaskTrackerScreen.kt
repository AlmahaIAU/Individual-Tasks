package edu.iau.cshj.csc402.lab2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.iau.cshj.csc402.lab2.ui.theme.AndroidGreen
import edu.iau.cshj.csc402.lab2.ui.theme.Navy
import edu.iau.cshj.csc402.lab2.ui.theme.ScreenBackground

data class TeamTask(val id: Int, val title: String, val owner: String, val isDone: Boolean = false)

private enum class TaskFilter { All, Open, Done }

// Task 11 - Challenge: team task tracker (state lives HERE, rows are stateless)
@Composable
fun TaskTrackerScreen(modifier: Modifier = Modifier) {
    val tasks = remember {
        mutableStateListOf(
            TeamTask(1, "Write project proposal", "Sara", isDone = true),
            TeamTask(2, "Create GitHub repository", "Noura", isDone = true),
            TeamTask(3, "Design the home screen", "Almaha"),
            TeamTask(4, "Prepare the demo video", "Lama")
        )
    }
    var newTitle by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf(TaskFilter.All) }

    // Derived values - computed from the list, never hard-coded.
    val done = tasks.count { it.isDone }
    val total = tasks.size
    val open = total - done
    val visible = when (filter) {
        TaskFilter.All -> tasks.toList()
        TaskFilter.Open -> tasks.filter { !it.isDone }
        TaskFilter.Done -> tasks.filter { it.isDone }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        LabTopBar(subtitle = "Task 11 - Team Task Tracker")

        // Navy summary strip
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Navy)
                .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SummaryItem(done, "Done")
            SummaryItem(open, "Open")
            SummaryItem(total, "Total")
        }

        // Optional extension: filter chips All / Open / Done
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TaskFilter.entries.forEach { f ->
                FilterChip(selected = filter == f, onClick = { filter = f }, label = { Text(f.name) })
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(visible, key = { it.id }) { task ->
                TaskRow(
                    task = task,
                    onToggle = {
                        val index = tasks.indexOfFirst { it.id == task.id }
                        tasks[index] = task.copy(isDone = !task.isDone)
                    },
                    onDelete = { tasks.removeAll { it.id == task.id } }
                )
            }
        }

        // Optional extension: the user types the new task's title
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = newTitle,
                onValueChange = { newTitle = it },
                label = { Text("New task title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    val nextId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
                    val title = newTitle.trim().ifEmpty { "New task" }
                    tasks.add(TeamTask(nextId, title, "Almaha"))
                    newTitle = ""
                },
                colors = ButtonDefaults.buttonColors(containerColor = AndroidGreen, contentColor = Navy),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) { Text("Add task", fontWeight = FontWeight.Bold) }
        }
    }
}

@Composable
private fun SummaryItem(value: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("$value", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(label, color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
    }
}

/**
 * Stateless row: it receives the task and reports events upwards.
 * No remember, no state of its own (state hoisting).
 */
@Composable
fun TaskRow(task: TeamTask, onToggle: () -> Unit, onDelete: () -> Unit = {}) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(checkedColor = AndroidGreen)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    task.title,
                    fontWeight = FontWeight.Medium,
                    color = if (task.isDone) Color.Gray else Navy,
                    textDecoration = if (task.isDone) TextDecoration.LineThrough else null
                )
                Text(task.owner, fontSize = 12.sp, color = Color.Gray)
            }
            TextButton(onClick = onDelete) { Text("Delete", color = Color(0xFFB3261E)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskTrackerScreenPreview() {
    TaskTrackerScreen()
}

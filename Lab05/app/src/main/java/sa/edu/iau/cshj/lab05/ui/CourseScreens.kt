package sa.edu.iau.cshj.lab05.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import sa.edu.iau.cshj.lab05.ui.theme.Lab05CoursesTheme

// ---------- Home ----------
@Composable
fun HomeScreen(
    onWebsiteClick: () -> Unit,
    onCallClick: () -> Unit,
    onCoursesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("CSC Courses", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onWebsiteClick) { Text("University website") }
        Button(onClick = onCallClick) { Text("Call the department") }
        Button(onClick = onCoursesClick) { Text("Browse courses") }
    }
}

// Task 1 - the Route decides what a tap means. Intents are built here, never in the Screen.
@Composable
fun HomeRoute(onCoursesClick: () -> Unit) {
    val context = LocalContext.current
    HomeScreen(
        onWebsiteClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iau.edu.sa"))
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "No app can open this", Toast.LENGTH_SHORT).show()
            }
        },
        onCallClick = {
            // TODO 1: open the dialer, started safely in the same way as the website
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0138000000"))
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "No app can open this", Toast.LENGTH_SHORT).show()
            }
        },
        onCoursesClick = onCoursesClick
    )
}

// ---------- Course list ----------
data class CourseListUiState(
    val courses: List<String> = emptyList()
)

@Composable
fun CourseListScreen(
    uiState: CourseListUiState,
    onCourseClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Courses", style = MaterialTheme.typography.headlineMedium)
        uiState.courses.forEach { code ->
            Button(onClick = { onCourseClick(code) }) { Text(code) }
        }
    }
}

@Composable
fun CourseListRoute(onCourseClick: (String) -> Unit) {
    CourseListScreen(
        uiState = CourseListUiState(listOf("CSC 402", "CSC 231", "CSC 311")),
        onCourseClick = onCourseClick
    )
}

// ---------- Course detail ----------
data class CourseDetailUiState(
    val code: String = ""
)

@Composable
fun CourseDetailScreen(
    uiState: CourseDetailUiState,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(uiState.code, style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onShareClick) { Text("Share") }
        OutlinedButton(onClick = onBackClick) { Text("Back") }
    }
}

@Composable
fun CourseDetailRoute(code: String, onBackClick: () -> Unit) {
    val context = LocalContext.current // used in Task 4
    CourseDetailScreen(
        uiState = CourseDetailUiState(code = code),
        // Task 4 - share sheet. A chooser always has at least the system sheet, so no try/catch.
        onShareClick = {
            val send = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, "I'm taking $code this semester!")
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(send, "Share via"))
        },
        onBackClick = onBackClick
    )
}

// ---------- Previews ----------
@Preview(showBackground = true, name = "Home")
@Composable
private fun HomeScreenPreview() {
    Lab05CoursesTheme {
        HomeScreen(onWebsiteClick = {}, onCallClick = {}, onCoursesClick = {})
    }
}

@Preview(showBackground = true, name = "Course list")
@Composable
private fun CourseListScreenPreview() {
    Lab05CoursesTheme {
        CourseListScreen(
            uiState = CourseListUiState(listOf("CSC 402", "CSC 231", "CSC 311")),
            onCourseClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Course detail")
@Composable
private fun CourseDetailScreenPreview() {
    Lab05CoursesTheme {
        CourseDetailScreen(uiState = CourseDetailUiState("CSC 402"), onShareClick = {}, onBackClick = {})
    }
}

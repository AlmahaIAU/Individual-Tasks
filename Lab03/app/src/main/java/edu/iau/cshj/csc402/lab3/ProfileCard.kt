package edu.iau.cshj.csc402.lab3

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.iau.cshj.csc402.lab3.ui.theme.CSC402Lab3Theme

// Task 1 - the data the card shows
data class Student(
    val name: String,
    val program: String,
    val gpa: String,
    val email: String,
    val city: String
)

val sampleStudent = Student(
    name = "Almaha Saeed Alwadie",
    program = "BSc Computer Science",
    gpa = "4.42",
    email = "almaha.saeed.alwadie@gmail.com",
    city = "Jubail"
)

/**
 * Tasks 1-6. Required data first, modifier last with a default,
 * and the modifier is passed to the first (outermost) element.
 * No Color(0x...) and no raw sp in this file - only theme roles and the type scale.
 */
@Composable
fun StudentCard(
    student: Student,
    modifier: Modifier = Modifier
) {
    // Task 5: no containerColor override - Card already uses the surface role.
    Card(modifier = modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Avatar(initials = student.name.initials())
                Spacer(Modifier.width(12.dp))
                // weight(1f) takes the leftover width and pushes the GPA to the right edge
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = student.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = student.program,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = student.gpa,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            HorizontalDivider()
            Text(
                text = student.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = student.city,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Task 3 - a Box is used because the status dot must sit ON TOP of the avatar.
 * First child is drawn at the back, last child at the front.
 */
@Composable
fun Avatar(initials: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        // Status dot: tertiary, so it stands apart from the primary-coloured GPA.
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(14.dp)
                .border(2.dp, MaterialTheme.colorScheme.surface, CircleShape)
                .padding(2.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.tertiary)
        )
    }
}

private fun String.initials(): String =
    split(" ").filter { it.isNotBlank() }.take(2).joinToString("") { it.first().uppercase() }

// Task 7 - two previews, one composable. Task 10 extension - a 1.5x font scale preview.
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Large font", showBackground = true, fontScale = 1.5f)
@Composable
private fun StudentCardPreview() {
    CSC402Lab3Theme {
        StudentCard(student = sampleStudent, modifier = Modifier.padding(8.dp))
    }
}

@Preview(name = "Long name", showBackground = true)
@Composable
private fun StudentCardLongNamePreview() {
    CSC402Lab3Theme {
        StudentCard(
            student = sampleStudent.copy(name = "Abdulrahman Al-Mutairi"),
            modifier = Modifier.padding(8.dp)
        )
    }
}

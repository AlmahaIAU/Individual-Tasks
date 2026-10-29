package edu.iau.cshj.csc402.lab2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.iau.cshj.csc402.lab2.ui.theme.AndroidGreen
import edu.iau.cshj.csc402.lab2.ui.theme.Navy
import edu.iau.cshj.csc402.lab2.ui.theme.ScreenBackground

// Same data class as Part A Task 5
data class Course(val code: String, val title: String, val credits: Int, val days: String)

// Task 10 - A scrolling list with LazyColumn
@Composable
fun CourseListScreen(modifier: Modifier = Modifier) {
    val courses = remember {
        listOf(
            Course("CSC 402", "Mobile Application Programming", 3, "Mon / Wed"),
            Course("CSC 311", "Database Systems", 4, "Sun / Tue"),
            Course("CSC 340", "Operating Systems", 3, "Mon / Wed"),
            Course("MATH 202", "Discrete Mathematics", 3, "Sun / Thu"),
            Course("ENG 214", "Technical Writing", 2, "Tue"),
            Course("PHYS 105", "General Physics II", 4, "Sun / Tue"),
            Course("ISLM 101", "Islamic Culture", 2, "Thu")
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        LabTopBar(subtitle = "Task 10 - My Courses")
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(courses) { course -> CourseRow(course) }
        }
    }
}

@Composable
fun CourseRow(course: Course, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .fillMaxHeight()
                    .background(AndroidGreen)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 14.dp, vertical = 12.dp)
            ) {
                Text(course.code, fontWeight = FontWeight.Bold, color = Navy)
                Text(course.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(course.days, fontSize = 12.sp, color = Color.Gray)
            }
            Text(
                "${course.credits} cr",
                fontSize = 12.sp,
                color = Navy,
                modifier = Modifier
                    .padding(end = 14.dp)
                    .clip(RoundedCornerShape(50))
                    .background(AndroidGreen.copy(alpha = 0.25f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseListScreenPreview() {
    CourseListScreen()
}

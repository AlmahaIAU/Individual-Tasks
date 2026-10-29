package edu.iau.cshj.csc402.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import edu.iau.cshj.csc402.lab2.ui.theme.CSC402Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // setContent is where the Compose UI attaches to this Activity.
        setContent {
            CSC402Lab2Theme {
                val tabs = listOf("Task 6", "Task 7", "Task 8", "Task 9", "Task 10", "Task 11")
                var selected by rememberSaveable { mutableIntStateOf(0) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        SecondaryScrollableTabRow(selectedTabIndex = selected) {
                            tabs.forEachIndexed { index, title ->
                                Tab(
                                    selected = selected == index,
                                    onClick = { selected = index },
                                    text = { Text(title) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    val screenModifier = Modifier.padding(innerPadding)
                    when (selected) {
                        0 -> WelcomeScreen(screenModifier)
                        1 -> StudentIdScreen(screenModifier)
                        2 -> AttendanceCounter(screenModifier)
                        3 -> GreetingCard(screenModifier)
                        4 -> CourseListScreen(screenModifier)
                        else -> TaskTrackerScreen(screenModifier)
                    }
                }
            }
        }
    }
}

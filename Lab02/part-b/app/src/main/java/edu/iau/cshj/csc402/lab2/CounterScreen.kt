package edu.iau.cshj.csc402.lab2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.iau.cshj.csc402.lab2.ui.theme.AndroidGreen
import edu.iau.cshj.csc402.lab2.ui.theme.Navy
import edu.iau.cshj.csc402.lab2.ui.theme.ScreenBackground

// Task 8 - State: the attendance counter
@Composable
fun AttendanceCounter(modifier: Modifier = Modifier) {
    // State: Compose watches this value and recomposes when it changes.
    // Experiment (Q6): replacing this with `var count = 0` makes the number never change on screen.
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        LabTopBar(subtitle = "Task 8 - Attendance Counter")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("STUDENTS PRESENT", color = Color.Gray, fontWeight = FontWeight.Medium, letterSpacing = 2.sp)
            Spacer(Modifier.height(12.dp))
            Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Text(
                    "$count",
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Bold,
                    color = Navy,
                    modifier = Modifier.padding(horizontal = 48.dp, vertical = 16.dp)
                )
            }
            Spacer(Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = { if (count > 0) count-- },
                    enabled = count > 0,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) { Text("−", fontSize = 24.sp) }
                Spacer(Modifier.width(16.dp))
                Button(
                    onClick = { count++ },
                    colors = ButtonDefaults.buttonColors(containerColor = AndroidGreen, contentColor = Navy)
                ) { Text("+", fontSize = 24.sp) }
            }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(onClick = { count = 0 }, enabled = count > 0) { Text("Reset") }
            Spacer(Modifier.height(20.dp))
            Text(
                if (count == 0) "Tap + to check a student in." else "$count of 30 students checked in.",
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AttendanceCounterPreview() {
    AttendanceCounter()
}

package sa.edu.iau.cshj.lab04.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.lab04.ui.theme.Lab04AttendanceTheme

// Task 2 - the screen's state: every field is a val, every field has a default.
data class AttendanceUiState(
    val sessionTitle: String = "",
    val checkedInCount: Int = 0,
    val isCheckedIn: Boolean = false
)

// Task 3 - a stateless screen: data comes in as a parameter, the tap goes out as a lambda.
@Composable
fun AttendanceScreen(
    uiState: AttendanceUiState,
    onCheckIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(uiState.sessionTitle, style = MaterialTheme.typography.headlineSmall)
        // TODO 1: show the count
        Text("Checked in: ${uiState.checkedInCount}")
        // TODO 2: a Button that reports the tap, and switches off once checked in
        Button(onClick = onCheckIn, enabled = !uiState.isCheckedIn) {
            Text(if (uiState.isCheckedIn) "Checked in" else "Check in")
        }
    }
}

// Task 4 - the Route owns the state. State flows DOWN, events flow UP.
@Composable
fun AttendanceRoute(modifier: Modifier = Modifier) {
    var uiState by remember { // Week 6 topic
        mutableStateOf(AttendanceUiState("CSC 402 - Week 4", 12))
    }
    AttendanceScreen(
        uiState = uiState, // state flows DOWN
        onCheckIn = {      // events flow UP
            uiState = uiState.copy(
                checkedInCount = uiState.checkedInCount + 1,
                isCheckedIn = true
            )
        },
        modifier = modifier
    )
    // Step 4.5 experiment - with this body the screen freezes, because the new value is
    // calculated and thrown away; uiState is never given a new object, so nothing recomposes:
    // onCheckIn = { uiState.checkedInCount + 1 }
}

@Preview(showBackground = true, name = "Not checked in yet")
@Composable
private fun AttendanceScreenPreview() {
    Lab04AttendanceTheme {
        AttendanceScreen(
            uiState = AttendanceUiState("CSC 402 - Week 4", 12, false),
            onCheckIn = {}
        )
    }
}

@Preview(showBackground = true, name = "Already checked in")
@Composable
private fun AttendanceScreenCheckedInPreview() {
    Lab04AttendanceTheme {
        AttendanceScreen(
            uiState = AttendanceUiState("CSC 402 - Week 4", 13, true),
            onCheckIn = {}
        )
    }
}

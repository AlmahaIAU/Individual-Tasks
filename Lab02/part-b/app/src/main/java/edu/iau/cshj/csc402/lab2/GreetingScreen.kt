package edu.iau.cshj.csc402.lab2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.iau.cshj.csc402.lab2.ui.theme.AndroidGreen
import edu.iau.cshj.csc402.lab2.ui.theme.Navy
import edu.iau.cshj.csc402.lab2.ui.theme.ScreenBackground

// Task 9 - Text input and a reactive card
@Composable
fun GreetingCard(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }          // what is in the text field
    var submittedName by remember { mutableStateOf("") } // what was submitted with the button

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        LabTopBar(subtitle = "Task 9 - Reactive Greeting")

        Column(modifier = Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Your name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = { submittedName = name.trim() },
                enabled = name.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) { Text("Show greeting") }
            Spacer(Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    if (submittedName.isEmpty()) {
                        Text("Type your name and press Show greeting.", color = Color.Gray)
                    } else {
                        // Derived from the one piece of state we hold - no extra state for the sentence.
                        Text("Marhaba, $submittedName!", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Navy)
                        Spacer(Modifier.height(6.dp))
                        Text("Your name has ${submittedName.length} letters.", color = Color.Gray)
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "CSC 402 - Lab 2",
                            fontSize = 12.sp,
                            color = Navy,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(AndroidGreen.copy(alpha = 0.25f))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingCardPreview() {
    GreetingCard()
}

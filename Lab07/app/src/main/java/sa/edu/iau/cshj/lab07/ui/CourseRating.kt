package sa.edu.iau.cshj.lab07.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.lab07.ui.components.StarRating
import sa.edu.iau.cshj.lab07.ui.theme.Lab07CourseFeedbackTheme

data class CourseRatingUiState(val code: String = "", val stars: Float = 0f)

@Composable
fun CourseRatingScreen(
    uiState: CourseRatingUiState,
    onStarsChange: (Float) -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Rate ${uiState.code}", style = MaterialTheme.typography.headlineSmall)
        StarRating(stars = uiState.stars, onStarsChange = onStarsChange)
        Text("You gave ${uiState.stars} stars")
        Button(onClick = onResetClick) { Text("Reset") }
    }
}

@Composable
fun CourseRatingRoute(code: String, modifier: Modifier = Modifier) {
    // rememberSaveable keeps the rating across rotation (Lecture 6).
    var stars by rememberSaveable { mutableFloatStateOf(0f) }
    CourseRatingScreen(
        uiState = CourseRatingUiState(code, stars),        // state flows DOWN
        onStarsChange = { newStars -> stars = newStars },  // events flow UP
        onResetClick = { stars = 0f },                     // events flow UP
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun CourseRatingScreenPreview() {
    Lab07CourseFeedbackTheme {
        CourseRatingScreen(
            uiState = CourseRatingUiState("CSC 402", 3.5f),
            onStarsChange = {},
            onResetClick = {}
        )
    }
}

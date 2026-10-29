package sa.edu.iau.cshj.lab07.ui.components

import android.util.Log
import android.widget.RatingBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

/**
 * Task 2 - a wrapper composable that hides the old RatingBar View.
 * From the outside it looks like any other screen part: value in, event out, modifier last.
 */
@Composable
fun StarRating(
    stars: Float,
    onStarsChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context -> // runs ONCE
            Log.d("Lab07", "factory")
            RatingBar(context).apply {
                numStars = 5
                stepSize = 0.5f
                // TODO 1: report the new value ONLY when the USER changed the rating,
                // so our own update below does not echo back as a user event.
                setOnRatingBarChangeListener { _, value, fromUser ->
                    if (fromUser) onStarsChange(value)
                }
            }
        },
        // TODO 2: runs again every time `stars` changes - copies state into the View.
        update = { view ->
            Log.d("Lab07", "update $stars")
            view.rating = stars
        },
        modifier = modifier
    )
}

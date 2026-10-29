package edu.iau.cshj.csc402.lab3

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.iau.cshj.csc402.lab3.ui.theme.CSC402Lab3Theme

// Task 4 - the modifier order puzzle. Modifiers are applied from left (outer) to right (inner).

// A: background first, then padding -> the colour fills the padded area too.
@Composable
fun ChainA(modifier: Modifier = Modifier) {
    Text(
        "content", modifier = modifier
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(24.dp)
    )
}

// B: padding first, then background -> the colour only covers the text; padding stays transparent.
@Composable
fun ChainB(modifier: Modifier = Modifier) {
    Text(
        "content", modifier = modifier
            .padding(24.dp)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    )
}

// C: clip first, then background -> the background is drawn inside the rounded shape.
@Composable
fun ChainC(modifier: Modifier = Modifier) {
    Text(
        "content", modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    )
}

// Tap-target version: clickable BEFORE padding -> the padded area responds to taps.
@Composable
fun TapTargetLarge(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Text(
        "clickable { } then padding", modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { Toast.makeText(context, "Large target tapped", Toast.LENGTH_SHORT).show() }
            .padding(16.dp)
    )
}

// padding BEFORE clickable -> only the text itself responds; the padding is dead space.
@Composable
fun TapTargetSmall(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Text(
        "padding then clickable { }", modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
            .clickable { Toast.makeText(context, "Small target tapped", Toast.LENGTH_SHORT).show() }
    )
}

@Composable
fun OrderPuzzle(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        ChainA()
        ChainB()
        ChainC()
        TapTargetLarge()
        TapTargetSmall()
    }
}

@Preview(showBackground = true)
@Composable
private fun OrderPuzzlePreview() {
    CSC402Lab3Theme {
        OrderPuzzle()
    }
}

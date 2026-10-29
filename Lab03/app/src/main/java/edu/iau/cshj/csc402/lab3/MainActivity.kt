package edu.iau.cshj.csc402.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import edu.iau.cshj.csc402.lab3.ui.theme.CSC402Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CSC402Lab3Theme {
                ProfileScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

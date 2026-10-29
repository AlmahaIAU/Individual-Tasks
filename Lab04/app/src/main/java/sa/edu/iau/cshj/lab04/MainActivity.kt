package sa.edu.iau.cshj.lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import sa.edu.iau.cshj.lab04.ui.AttendanceRoute
import sa.edu.iau.cshj.lab04.ui.theme.Lab04AttendanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)   // 1. let ComponentActivity do its own setup
        enableEdgeToEdge()                   // 2. draw behind the system bars
        setContent {                         // 3. hand the screen to Compose
            Lab04AttendanceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AttendanceRoute(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

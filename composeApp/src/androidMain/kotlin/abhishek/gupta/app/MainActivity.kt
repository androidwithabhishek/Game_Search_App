package abhishek.gupta.app

import abhishek.gupta.app.ui.theme.AppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme { App() }


        }

        // Set status bar color to dark purple and white icons
        window.statusBarColor = android.graphics.Color.parseColor("#000000") // any hex color



        // Make status bar icons white (false = light icons)
        androidx.core.view.WindowInsetsControllerCompat(window, window.decorView)
            .isAppearanceLightStatusBars = false


    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
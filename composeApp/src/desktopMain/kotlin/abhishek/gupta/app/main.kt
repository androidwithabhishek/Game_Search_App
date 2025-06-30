package abhishek.gupta.app

import abhishek.gupta.app.di.initKoin
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Game App",
    ) {

        initKoin()
        App()
    }
}
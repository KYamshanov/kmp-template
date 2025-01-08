import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.jetbrains.compose.resources.painterResource
import replacementplace.app.bycompose.desktop.generated.resources.Res
import replacementplace.app.bycompose.desktop.generated.resources.app_icon
import ru.kyamshanov.replacementPlace.App
import ru.kyamshanov.replacementPlace.DefaultRootComponent
import ru.kyamshanov.replacementPlace.RootComponent


fun main() {
    //init DI

    val lifecycle = LifecycleRegistry()

    // Always create the root component outside Compose on the UI thread
    val root: RootComponent =
        runOnUiThread {
            DefaultRootComponent(
                componentContext = DefaultComponentContext(lifecycle = lifecycle),
            )
        }

    application {
        val windowState = rememberWindowState()

        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "replacementPlace",
            icon = painterResource(Res.drawable.app_icon) //for generate Res class use `gradle :app:byCompose:desktop:generateComposeResClass`
        ) {
            App(root)
        }
    }
}
package fr.oupson.memebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import fr.oupson.memebox.ui.ImageViewModel
import fr.oupson.memebox.ui.screen.AddImageScreen
import fr.oupson.memebox.ui.screen.AppScreen
import fr.oupson.memebox.ui.theme.MemeBoxTheme

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageViewModel by viewModels<ImageViewModel>()
        imageViewModel.loadA()

        enableEdgeToEdge()

        setContent {
            val darkTheme = isSystemInDarkTheme()
            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.TRANSPARENT,
                        android.graphics.Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim,
                        darkScrim,
                    ) { darkTheme },
                )
                onDispose {}
            }

            val navController = rememberNavController()
            MemeBoxTheme {
                NavHost(navController = navController, startDestination = "image_list") {
                    composable("image_list") {
                        AppScreen(imageViewModel, onAddNavigate = {
                            navController.navigate(
                                "add_image/${
                                    it
                                }"
                            )
                        })
                    }
                    composable(
                        "add_image/{tempImageId}",
                        arguments = listOf(navArgument("tempImageId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val tempImageId = backStackEntry.arguments?.getLong("tempImageId")!! // TODO
                        AddImageScreen(tempImageId, imageViewModel, onAddedImage = { imageId ->

                        })
                    }
                    composable(
                        "image/{imageId}", arguments = listOf(navArgument("imageId") { type = NavType.LongType })
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MemeBoxTheme {
        Greeting("Android")
    }
}

/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)
package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.MainScreen
import com.example.ui.screens.RegisterScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.theme.DxdGamingTheme
import com.example.ui.theme.DxdPureBlack
import com.example.viewmodel.DxdViewModel
import com.example.viewmodel.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DxdGamingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DxdPureBlack
                ) {
                    DxdAppNavigation()
                }
            }
        }
    }
}

@Composable
fun DxdAppNavigation(
    viewModel: DxdViewModel = viewModel()
) {
    val currentScreen by viewModel.currentScreen.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DxdPureBlack)
    ) {
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "rootNavigationTransition"
        ) { screen ->
            when (screen) {
                is Screen.Splash -> {
                    SplashScreen(
                        onNavigateNext = {
                            viewModel.navigateTo(Screen.Main)
                        }
                    )
                }

                is Screen.Login -> {
                    BackHandler {
                        viewModel.navigateTo(Screen.Main)
                    }
                    LoginScreen(
                        onLoginSuccess = { gamerTag ->
                            viewModel.login(gamerTag)
                        },
                        onNavigateToRegister = {
                            viewModel.navigateTo(Screen.Register)
                        },
                        onExploreAsGuest = {
                            viewModel.login("GUEST_OPERATOR")
                        }
                    )
                }

                is Screen.Register -> {
                    BackHandler {
                        viewModel.navigateTo(Screen.Login)
                    }
                    RegisterScreen(
                        onRegisterSuccess = { tag, game ->
                            viewModel.register(tag, game)
                        },
                        onNavigateBackToLogin = {
                            viewModel.navigateTo(Screen.Login)
                        }
                    )
                }

                is Screen.Main -> {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}

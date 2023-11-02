package com.example.fizzbuzz.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fizzbuzz.presentation.screens.end_game.EndGameScreen
import com.example.fizzbuzz.presentation.screens.end_game.EndGameScreenIntent
import com.example.fizzbuzz.presentation.screens.end_game.EndGameViewModel
import com.example.fizzbuzz.presentation.screens.game.GameScreen
import com.example.fizzbuzz.presentation.screens.history.HistoryScreen
import com.example.fizzbuzz.presentation.screens.home.HomeScreen
import com.example.fizzbuzz.presentation.screens.leaderboard.LeaderboardScreen
import com.example.fizzbuzz.presentation.screens.sign_in.SignInScreen
import com.example.fizzbuzz.presentation.screens.sign_up.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.SignIn.route) {
            SignInScreen(
                navigateToHomeScreen = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignUpScreen = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }
        composable(route = Screen.SignUp.route) {
            SignUpScreen(
                navigateToHomeScreen = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignInScreen = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToGameScreen = { navController.navigate(Screen.Game.route) },
                navigateToLeaderboardScreen = { navController.navigate(Screen.Leaderboard.route) },
                navigateToHistoryScreen = { navController.navigate(Screen.History.route) },
                navigateToSignInScreen = {
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = Screen.Game.route) {
            GameScreen(navigateToEndGameScreen = { score ->
                navController.navigate("${Screen.EndGame.route}/$score")
                {
                    popUpTo(Screen.Game.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            route = "${Screen.EndGame.route}/{score}",
        ) {
            val score = it.arguments?.getString("score")?.toInt()!!

            val viewModel = hiltViewModel<EndGameViewModel>()
            viewModel.processIntent(EndGameScreenIntent.SetScore(score))

            EndGameScreen(navigateToHomeScreen = {
                navController.popBackStack()
            })
        }

        composable(route = Screen.Leaderboard.route) {
            LeaderboardScreen()
        }

        composable(route = Screen.History.route) {
            HistoryScreen()
        }

    }
}
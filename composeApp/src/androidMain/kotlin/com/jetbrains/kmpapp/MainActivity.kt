package com.jetbrains.kmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetbrains.kmpapp.screens.CountriesListScreen
import com.jetbrains.kmpapp.screens.DetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    CountriesListScreen(navigateToDetails = { objectId ->
                        navController.navigate("details/$objectId")
                    })
                }
                composable(
                    "details/{objectId}",
                    arguments = listOf(navArgument("objectId") { type = NavType.IntType })
                ) { backstack ->
                    DetailScreen(
                        objectId = backstack.arguments?.getInt("objectId")!!,
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

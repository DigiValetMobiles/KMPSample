package com.paragon.kmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.paragon.kmpapp.screens.cachinglist.CachingListScreen
import com.paragon.kmpapp.screens.contientdetail.ContinentDetailScreen
import com.paragon.kmpapp.screens.continentslist.ContinentsListScreen

/**
 * Main Activity of the app
 * */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            /**
             * Nav Controller for navigation in the app
             * */
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "cachingList") {

                /**
                 * CachingListScreen
                 * */
                composable("cachingList") {
                    CachingListScreen(navigateToContinents = { cachingType ->
                        navController.navigate("continentsList/$cachingType")
                    })
                }

                /**
                 * ContinentsListScreen
                 * */
                composable(
                    "continentsList/{cachingType}",
                    arguments = listOf(navArgument("cachingType") { type = NavType.StringType })
                ) { backstack ->
                    ContinentsListScreen(
                        navigateToDetails = { continentCode ->
                            /*
                            * Not showing the continent details screen now
                            * */

                            /*navController.navigate("details/$continentCode")*/
                        },
                        fetchType = backstack.arguments?.getString("cachingType")!!,
                    )
                }

                /**
                 * ContinentDetailScreen
                 * */
                composable(
                    "details/{continentCode}",
                    arguments = listOf(navArgument("continentCode") { type = NavType.StringType })
                ) { backstack ->
                    ContinentDetailScreen(continentCode = backstack.arguments?.getString("continentCode")!!,
                        navigateBack = {
                            navController.popBackStack()
                        })
                }
            }
        }
    }
}

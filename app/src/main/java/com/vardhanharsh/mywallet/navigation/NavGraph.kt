package com.vardhanharsh.mywallet.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vardhanharsh.mywallet.screens.Add
import com.vardhanharsh.mywallet.screens.Categories
import com.vardhanharsh.mywallet.screens.Expenses
import com.vardhanharsh.mywallet.screens.Report
import com.vardhanharsh.mywallet.screens.Settings

@Composable
fun Navigation(navController: NavHostController , innerPadding: PaddingValues ){
//    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "expenses"){
        composable("expenses"){
            Expenses(navController)
        }
        // Report
        composable("report"){
            Report(navController, innerPadding)
        }
        // Add
        composable("add"){
            Add(navController , innerPadding)
        }
        // Settings
        composable("settings"){
            Settings(navController)
        }
        // Settings -> Categories
        composable("settings/categories"){
            Categories(navController,  innerPadding)
        }
    }
}
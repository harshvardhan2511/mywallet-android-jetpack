package com.vardhanharsh.mywallet

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vardhanharsh.mywallet.navigation.Navigation
import com.vardhanharsh.mywallet.ui.theme.MyWalletTheme
import com.vardhanharsh.mywallet.ui.theme.topAppBarBackground

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWalletTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()
                // holds details like the destination's arguments, lifecycle state, and other relevant information associated with that particular destination
                val backStackEntry = navController.currentBackStackEntryAsState()



                Scaffold(

//                    topBar = {
//                        MediumTopAppBar(
//                            colors = TopAppBarDefaults.mediumTopAppBarColors(
//                                containerColor = Color.White
//                            ),
//                            modifier = Modifier.fillMaxHeight(),
//                            title = { Text(text = "Expense")}
//
//                        )
//                    },

                    bottomBar = {
                                NavigationBar(
                                    containerColor = topAppBarBackground
                                ) {
                                    // Expenses Icon
                                    NavigationBarItem(
                                        selected = backStackEntry.value?.destination?.route == "expenses",
                                        onClick = {  navController.navigate("expenses") },
                                        label = {
                                            Text(text = "Expenses")
                                        },
                                        icon = {
                                            Icon(painterResource(id = R.drawable.upload_icon) , contentDescription = "")
                                        }

                                    )
                                    NavigationBarItem(
                                        selected = backStackEntry.value?.destination?.route == "report",
                                        onClick = {  navController.navigate("report") },
                                        label = {
                                            Text(text = "Report")
                                        },
                                        icon = {
                                            Icon(painterResource(id = R.drawable.bar_chart) , contentDescription = "")
                                        }

                                    )
                                    NavigationBarItem(
                                        selected = backStackEntry.value?.destination?.route == "add",
                                        onClick = {  navController.navigate("add") },
                                        label = {
                                            Text(text = "Add")
                                        },
                                        icon = {
                                            Icon(painterResource(id = R.drawable.add) , contentDescription = "")
                                        }

                                    )
                                    NavigationBarItem(
                                        selected = backStackEntry.value?.destination?.route?.startsWith("settings")?:false,
                                        onClick = { navController.navigate("settings") },
                                        label = {
                                            Text(text = "Settings")
                                        },
                                        icon = {
                                            Icon(painterResource(id = R.drawable.settings) , contentDescription = "")
                                        }

                                    )

                                }

                    },
                    content = { innerPadding ->

                        Navigation(navController ,innerPadding)


                    }
                    )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    MyWalletTheme {
        Surface {
        Greeting("Android")
        }
    }
}
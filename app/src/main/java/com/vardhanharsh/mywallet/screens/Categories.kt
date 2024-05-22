@file:Suppress("PreviewMustBeTopLevelFunction")

package com.vardhanharsh.mywallet.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.vardhanharsh.mywallet.components.TableRow
import com.vardhanharsh.mywallet.components.UnstyledTextField
import com.vardhanharsh.mywallet.ui.theme.BackgroundElevated
import com.vardhanharsh.mywallet.ui.theme.DividerColor
import com.vardhanharsh.mywallet.ui.theme.MyWalletTheme
import com.vardhanharsh.mywallet.ui.theme.Shapes
import com.vardhanharsh.mywallet.ui.theme.Typography
import com.vardhanharsh.mywallet.ui.theme.topAppBarBackground
import com.vardhanharsh.mywallet.viewmodels.CategoriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categories(navController: NavController, vm: CategoriesViewModel = viewModel()) {

    val uiState by vm.uiState.collectAsState()

    val colorPickerController = rememberColorPickerController()


    Scaffold(

        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Categories") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = topAppBarBackground
                ),
                navigationIcon = {

                    Surface(
                        onClick = navController::popBackStack,
                        color = Color.Transparent
                    ) {
                        Row(modifier = Modifier.padding(vertical = 10.dp)) {
                            Icon(
                                Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                                contentDescription = "Settings"
                            )
                            Text(text = "Settings")
                        }
                    }

                }
            )
        },

        content = { innerPadding ->

            Column(modifier = Modifier.padding(innerPadding)) {
                // Order of modifier settings matter. Everything is build layer over layer.
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(shape = Shapes.large)
                        .background(BackgroundElevated)
                ) {
                    TableRow(text = "Groceries")
                    HorizontalDivider(thickness = 1.dp, color = DividerColor)

                    TableRow(text = "Bills")
                    HorizontalDivider(thickness = 1.dp, color = DividerColor)

                    TableRow(text = "Take out")
                    HorizontalDivider(thickness = 1.dp, color = DividerColor)

                    TableRow(text = "Subscriptions")
                    HorizontalDivider(thickness = 1.dp, color = DividerColor)

                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    if (uiState.colorPickerShowing) {
                        Dialog(onDismissRequest = vm::hideColorPicker) {
                            Surface(color = BackgroundElevated, shape = Shapes.large) {
                                Column(
                                    modifier = Modifier.padding(all = 30.dp)
                                ) {
                                    Text("Select a color", style = Typography.titleLarge)

                                    // Row for showing alphaTile
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 24.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        AlphaTile(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(60.dp)
                                                .clip(RoundedCornerShape(6.dp)),
                                            controller = colorPickerController
                                        )
                                    }

                                    // Colour Picking Dial
                                    HsvColorPicker(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(300.dp)
                                            .padding(10.dp),
                                        controller = colorPickerController,
                                        onColorChanged = { envelope ->
                                            vm.setNewCategoryColor(envelope.color)
                                        },
                                    )

                                    // Brightness Adjusting Slider
                                    BrightnessSlider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                            .height(35.dp),
                                        controller = colorPickerController
                                    )

                                    //Submit Button
                                    TextButton(
                                        onClick = vm::hideColorPicker,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 24.dp),
                                    ) {
                                        Text("Done")
                                    }

                                }
                            }
                        }

                    }

                    // Small Circle, on tapping: opens color picking dialog
                    Surface(
                        onClick = vm::showColorPicker,
                        shape = CircleShape,
                        color = uiState.newCategoryColor,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.White
                        ),
                        modifier = Modifier.size(width = 24.dp, height = 24.dp)
                    ) {}

                    Surface(
                        color = BackgroundElevated,
                        modifier = Modifier
                            .height(45.dp)
                            .weight(1f)
                            .padding(start = 16.dp),
                        shape = Shapes.large,
                    ) {

                        Column(
                            modifier = Modifier.fillMaxHeight(1.0f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            UnstyledTextField(
                                value = uiState.newCategoryName,
                                onValueChange = vm::setNewCategoryName,
                                placeholder = { Text(
                                    "Category name"
                                ) },
                                arrangement = Arrangement.Start,
                                modifier = Modifier
                                    .fillMaxWidth().fillMaxHeight(),
                                maxLines = 1,
                            )
                        }
                    }
                    IconButton(
                        onClick = vm::createNewCategory,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {
                        Icon(
                            Icons.Rounded.Send,
                            "Create category"
                        )
                    }
                }
            }

        }
    )
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategories() {
    MyWalletTheme {
        Categories(navController = rememberNavController())
    }
}

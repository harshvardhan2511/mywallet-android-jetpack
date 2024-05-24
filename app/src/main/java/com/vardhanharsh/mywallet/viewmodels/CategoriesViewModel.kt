package com.vardhanharsh.mywallet.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.vardhanharsh.mywallet.models.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class CategoriesState(
    val newCategoryColor: Color = Color.White,
    val newCategoryName: String = "",
    val colorPickerShowing: Boolean = false,
    val categories: List<Category> = listOf(

        Category("Hobbies", Color.Red),
        Category("Food", Color.Green),
        Category("Travel", Color.Blue),
        Category("Work", Color.Yellow),
    )
)


class CategoriesViewModel:ViewModel(){

    private val _uiState = MutableStateFlow(CategoriesState())
    val uiState: StateFlow<CategoriesState> = _uiState.asStateFlow()

    fun setNewCategoryColor(color: Color) {
        _uiState.update { currentState ->
            currentState.copy(
                newCategoryColor = color
            )
        }
    }

    fun setNewCategoryName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                newCategoryName = name
            )
        }
    }

    fun showColorPicker() {
        _uiState.update { currentState ->
            currentState.copy(
                colorPickerShowing = true
            )
        }
    }

    fun hideColorPicker() {
        _uiState.update { currentState ->
            currentState.copy(
                colorPickerShowing = false
            )
        }
    }



    fun createNewCategory() {
        // TODO: Save New Category to Local DB
//        viewModelScope.launch(Dispatchers.IO) {
//            db.write {
//                this.copyToRealm(Category(
//                    _uiState.value.newCategoryName,
//                    _uiState.value.newCategoryColor
//                ))
//            }
//            _uiState.update { currentState ->
//                currentState.copy(
//                    newCategoryColor = Color.White,
//                    newCategoryName = ""
//                )
//            }
//        }


        val newCategoriesList = mutableListOf(
            Category(
                _uiState.value.newCategoryName,
                _uiState.value.newCategoryColor
            )
        )
        newCategoriesList.addAll(
            _uiState.value.categories,
        )

        _uiState.update { currentState ->
            currentState.copy(
                categories = newCategoriesList,
                newCategoryColor = Color.White,
                newCategoryName = ""
            )
        }

    }




}
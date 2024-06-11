package com.vardhanharsh.mywallet.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vardhanharsh.mywallet.models.Category
import com.vardhanharsh.mywallet.ui.theme.Shapes
import com.vardhanharsh.mywallet.ui.theme.Typography

@Composable
fun CategoryBadge(category: Category, modifier: Modifier = Modifier) {

    val newColor = category.color.copy(0.25f)

    Surface(
        shape = Shapes.large,
        color = newColor,
        modifier = modifier,
    ) {
        Text(
            category.name,
            color = category.color,
            style = Typography.bodySmall,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}
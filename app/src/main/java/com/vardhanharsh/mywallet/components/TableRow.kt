package com.vardhanharsh.mywallet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vardhanharsh.mywallet.R
import com.vardhanharsh.mywallet.ui.theme.Destructive
import com.vardhanharsh.mywallet.ui.theme.Typography
import com.vardhanharsh.mywallet.ui.theme.textPrimary

@Composable
fun TableRow(
    modifier: Modifier = Modifier,
    text: String? = null,
    hasArrow: Boolean = false,
    isDestructive: Boolean = false,
    detailContent: (@Composable RowScope.() -> Unit)? = null,
    content: (@Composable RowScope.() -> Unit)? = null
) {

    val textColor = if (isDestructive) Destructive else textPrimary

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (text != null) {
            Text(
                text = text,
                style = Typography.bodyMedium,
                color = textColor,
                modifier = Modifier.padding(vertical = 16.dp)
            )

        }

        if (content != null) {
            content()
        }

        if (hasArrow) {
            Icon(
                painterResource(id = R.drawable.right_arrow),
                contentDescription = "Right Arrow",
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }



        if (detailContent != null) {
            detailContent()
        }


    }

}
package com.vardhanharsh.mywallet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vardhanharsh.mywallet.R
import com.vardhanharsh.mywallet.ui.theme.Destructive
import com.vardhanharsh.mywallet.ui.theme.Typography
import com.vardhanharsh.mywallet.ui.theme.textPrimary

@Composable
fun TableRow(text: String, onClick: (String) -> Unit = {}, hasArrow: Boolean = false, isDestructive: Boolean = false){

    val textColor = if(isDestructive) Destructive else textPrimary



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(text)
            }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "$text", style = Typography.bodyMedium, color = textColor)

        if(hasArrow) {
            Icon(painterResource(id = R.drawable.right_arrow),
                contentDescription = "Right Arrow",
                modifier = Modifier.padding(end = 4.dp)
                )
        }


    }

}
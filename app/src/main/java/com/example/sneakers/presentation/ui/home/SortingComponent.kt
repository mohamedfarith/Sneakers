package com.example.sneakers.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sneakers.R
import com.example.sneakers.ui.theme.DarkGray
import com.example.sneakers.ui.theme.Typography

@Composable
fun SortingComponent() {

    Row(
        modifier = Modifier
            .padding(top  = 10.dp,start = 20.dp,end = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            textAlign = TextAlign.Center,
            text = "Popular",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            style = Typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {},
            elevation = ButtonDefaults.elevation(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                modifier = Modifier.padding(end = 10.dp),
                textAlign = TextAlign.Center,
                text = "Sort by",
                color = DarkGray,
                fontFamily = FontFamily.SansSerif,
                style = Typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_down),
                tint = DarkGray,
                contentDescription = ""
            )
        }

    }
}

@Preview
@Composable
fun SortingComponentPreview() {
    SortingComponent()
}

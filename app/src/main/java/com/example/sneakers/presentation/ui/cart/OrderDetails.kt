package com.example.sneakers.presentation.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sneakers.constants.AppConstants
import com.example.sneakers.presentation.ui.SneakerUiDto
import com.example.sneakers.ui.theme.DarkGray
import com.example.sneakers.ui.theme.ThemeOrange
import com.example.sneakers.ui.theme.Typography

@Composable
fun OrderDetails(mData: List<SneakerUiDto>) {
    var amount = 0
    mData.mapNotNull { it.price.replace("$", "").toIntOrNull() ?: 0 }
        .forEach { price -> amount += price }
    Column(modifier = Modifier.background(color = Color.White)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            textAlign = TextAlign.Start,
            text = "Order Details",
            fontFamily = FontFamily.SansSerif,
            style = Typography.titleLarge,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textAlign = TextAlign.Start,
            text = "Sub total : $$amount",
            fontFamily = FontFamily.SansSerif,
            style = Typography.titleMedium,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            color = DarkGray,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            textAlign = TextAlign.Start,
            text = "Taxes and Charges : $50",
            fontFamily = FontFamily.SansSerif,
            style = Typography.titleMedium,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            color = DarkGray,
            overflow = TextOverflow.Ellipsis
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Start,
                text = "Total : $${amount+50}",
                fontFamily = FontFamily.SansSerif,
                style = Typography.titleLarge,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                color = ThemeOrange,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            androidx.compose.material.Button(
                modifier = Modifier.padding(20.dp),
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = ThemeOrange)
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    textAlign = TextAlign.Center,
                    text = "Checkout",
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    style = Typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}

@Preview
@Composable
fun OrderDetailsPrev() {
    OrderDetails(mData = arrayListOf())
}
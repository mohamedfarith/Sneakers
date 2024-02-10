package com.example.sneakers.presentation.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sneakers.R
import com.example.sneakers.data.models.cart.SneakerItemDao
import com.example.sneakers.data.models.home.SneakerItemDto
import com.example.sneakers.presentation.ui.SneakerUiDto
import com.example.sneakers.presentation.ui.toSneakerUiDto
import com.example.sneakers.ui.theme.DarkGray
import com.example.sneakers.ui.theme.ThemeIconDarkOrange
import com.example.sneakers.ui.theme.ThemeIconLightOrange
import com.example.sneakers.ui.theme.ThemeOrange
import com.example.sneakers.ui.theme.Typography

@Composable
fun CartItemComponent(sneakerUiDto: SneakerUiDto, removeFromCartClicked: (SneakerUiDto) -> Unit) {
    Box() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .shadow(shape = RoundedCornerShape(size = 20.dp), elevation = 2.dp)
                .background(Color.White),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.37f),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                            .height(100.dp)
                            .background(
                                shape = RoundedCornerShape(100.dp),
                                color = ThemeIconLightOrange
                            )
                            .padding(15.dp)
                            .background(
                                shape = RoundedCornerShape(100.dp),
                                color = ThemeIconDarkOrange
                            )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.product_item),
                        modifier = Modifier.fillMaxWidth(),
                        contentDescription = "Product image"
                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = sneakerUiDto.title,
                        fontFamily = FontFamily.SansSerif,
                        style = Typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        textAlign = TextAlign.Start,
                        text = sneakerUiDto.price,
                        color = DarkGray,
                        fontFamily = FontFamily.SansSerif,
                        style = Typography.titleSmall,
                        fontWeight = FontWeight.Normal
                    )
                }


            }

        }
        Icon(
            modifier = Modifier
                .clickable { removeFromCartClicked(sneakerUiDto) }
                .rotate(45f)
                .align(Alignment.TopEnd),
            painter = painterResource(id = R.drawable.add_icon),
            tint = ThemeOrange,
            contentDescription = "Remove from Cart"
        )
    }

}

@Preview
@Composable
fun CartItemComponentPreview() {
    CartItemComponent(SneakerItemDto().toSneakerUiDto()){}
}
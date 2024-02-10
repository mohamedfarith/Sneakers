package com.example.sneakers.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sneakers.R
import com.example.sneakers.presentation.ui.SneakerUiDto
import com.example.sneakers.ui.theme.ThemeIconDarkOrange
import com.example.sneakers.ui.theme.ThemeIconLightOrange
import com.example.sneakers.ui.theme.ThemeOrange
import com.example.sneakers.ui.theme.Typography

@Composable
fun SneakerItemComponent(
    sneakerItem: SneakerUiDto,
    onCardClicked: (SneakerUiDto) -> Unit,
    addToCartClicked: (SneakerUiDto) -> Unit
) {

    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), shape = RoundedCornerShape(20.dp), backgroundColor = Color.White
    ) {
        Box(modifier = Modifier.padding(10.dp)) {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clickable {
                        onCardClicked(sneakerItem)
                    },

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp)
                            .height(130.dp)
                            .background(
                                shape = RoundedCornerShape(100.dp),
                                color = ThemeIconLightOrange
                            )
                            .padding(25.dp)
                            .background(
                                shape = RoundedCornerShape(100.dp),
                                color = ThemeIconDarkOrange
                            )
                    )
//                    AsyncImage(
//                        model = sneakerItem.images.getOrNull(0)?:let { "" },
//                        modifier = Modifier.fillMaxWidth(),
//                        contentDescription = "App"
//                    )
                    Image(
                        painter = painterResource(id = R.drawable.product_item),
                        modifier = Modifier.fillMaxWidth(),
                        contentDescription = "App"
                    )
                    
                }

                //title
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    textAlign = TextAlign.Center,
                    text = sneakerItem.title,
                    fontFamily = FontFamily.SansSerif,
                    style = Typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    textAlign = TextAlign.Center,
                    text = sneakerItem.price,
                    color = ThemeOrange,
                    fontFamily = FontFamily.SansSerif,
                    style = Typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

            }

            Icon(
                modifier = Modifier.clickable { addToCartClicked(sneakerItem) },
                painter = painterResource(id = R.drawable.add_icon),
                tint = ThemeOrange,
                contentDescription = "Add to cart"
            )
        }


    }
}

@Preview
@Composable
fun SneakerComponentPreview() {

}
package com.example.sneakers.presentation.ui.home

import android.util.Log
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakers.R
import com.example.sneakers.constants.AppConstants
import com.example.sneakers.presentation.ui.SneakerUiDto
import com.example.sneakers.ui.theme.DarkGray
import com.example.sneakers.ui.theme.LightBlue
import com.example.sneakers.ui.theme.Purple40
import com.example.sneakers.ui.theme.ThemeIconDarkOrange
import com.example.sneakers.ui.theme.ThemeIconLightOrange
import com.example.sneakers.ui.theme.ThemeOrange
import com.example.sneakers.ui.theme.Typography
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun ItemDescriptionScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    addItemToCart: (SneakerUiDto) -> Unit
) {
    val height = LocalConfiguration.current.screenHeightDp
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val data = viewModel.sneakerData.collectAsState().value
    val mData = viewModel.sneakerListData.collectAsState().value
    Log.d("ITEM_DESCRIPTION", "ItemDescriptionScreen: $mData")
    data?.let {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            item {
                CarouselComponent(modifier = Modifier.height((height / 2.4).dp), onBackPressed = {
                    backPressedDispatcher?.onBackPressed()
                })
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height.dp)
                        .shadow(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp), elevation = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        ItemDetails(title = data.title, description = data.description)
                        SizeComponent(listOf("10", "8", "7"))
                        ColorComponent(listOf(ThemeIconLightOrange, Purple40, LightBlue))
                        AddToCartComponent(data, addItemToCart)

                    }
                }
            }
        }
    }

}

@Composable
fun ItemDetails(title: String, description: String) {
    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            textAlign = TextAlign.Center,
            text = title,
            fontFamily = FontFamily.SansSerif,
            style = Typography.titleLarge,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            textAlign = TextAlign.Start,
            text = description,
            color = DarkGray,
            fontFamily = FontFamily.SansSerif,
            style = Typography.titleSmall,
            fontWeight = FontWeight.Normal
        )

    }
}

@Composable
fun AddToCartComponent(data: SneakerUiDto, addItemToCart: (SneakerUiDto) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            text = "Price :",
            color = DarkGray,
            fontFamily = FontFamily.SansSerif,
            style = Typography.titleMedium,
            fontWeight = FontWeight.Normal
        )
        Text(
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            text = data.price,
            color = ThemeOrange,
            fontFamily = FontFamily.SansSerif,
            style = Typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))

        androidx.compose.material.Button(
            modifier = Modifier.padding(20.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = { addItemToCart(data) },
            colors = ButtonDefaults.buttonColors(backgroundColor = ThemeOrange)
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                textAlign = TextAlign.Center,
                text = "Add to Cart",
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                style = Typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColorComponent(colorList: List<Color>) {
    LazyRow(Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        item {
            Text(
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center,
                text = "Color : ",
                color = DarkGray,
                fontFamily = FontFamily.SansSerif,
                style = Typography.titleMedium,
                fontWeight = FontWeight.Normal
            )
        }
        item {
            val selected = remember { mutableStateOf(colorList[0]) }
            Row {
                colorList.forEach { color ->
                    FilterChip(colors = ChipDefaults.filterChipColors(backgroundColor = color),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(10.dp),
                        selected = color == selected.value,
                        onClick = { selected.value = color }) {
                        if (selected.value == color)
                            Icon(
                                modifier = Modifier.size(30.dp).padding(5.dp),
                                painter = painterResource(id = R.drawable.check_icon),
                                tint = Color.White,
                                contentDescription = "Chip"
                            )
                        else {
                            Spacer(modifier = Modifier.width(30.dp))
                        }
                    }
                }
            }
        }

    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SizeComponent(sizeList: List<String>) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Text(
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center,
                text = "Size(uk) : ",
                color = DarkGray,
                fontFamily = FontFamily.SansSerif,
                style = Typography.titleMedium,
                fontWeight = FontWeight.Normal
            )
        }
        item {
            val selected = remember { mutableStateOf("") }
            Row {
                sizeList.forEach { currentSize ->

                    Text(
                        modifier = Modifier
                            .width(80.dp)
                            .padding(10.dp)
                            .background(
                                shape = RoundedCornerShape(10.dp),
                                color = if (selected.value == currentSize) ThemeOrange else Color.Transparent
                            )
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(10.dp),
                                color = ThemeOrange
                            )
                            .padding(10.dp)
                            .clickable {
                                selected.value = currentSize
                            }, textAlign = TextAlign.Center,
                        text = currentSize,
                        style = Typography.titleMedium,
                        color = if (selected.value == currentSize) Color.White else ThemeOrange
                    )
                }
            }
        }

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselComponent(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {

    val pagerState = rememberPagerState()
    Column(modifier = modifier) {
        Icon(
            modifier = Modifier
                .padding(10.dp)
                .size(30.dp)
                .rotate(90f)
                .clickable { onBackPressed() },
            painter = painterResource(id = R.drawable.arrow_down),
            tint = ThemeOrange,
            contentDescription = ""
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(count = 3, state = pagerState) {
                Box(modifier = Modifier.fillMaxWidth(0.5f), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 10.dp)
                            .height(180.dp)
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
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.product_item),
                        contentDescription = "App"
                    )
                }

            }
            Spacer(modifier = Modifier.padding(15.dp))
            HorizontalPagerIndicator(
                pagerState = pagerState,
                indicatorWidth = 60.dp,
                indicatorHeight = 5.dp,
                spacing = 8.dp
            )
        }
    }


}


@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun ItemDescriptionScreenPreview() {
    Surface(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Column {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .rotate(90f)
                    .clickable { },
                painter = painterResource(id = R.drawable.arrow_down),
                tint = ThemeOrange,
                contentDescription = ""
            )
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(count = 3) {
                    Box(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp, horizontal = 10.dp)
                                .height(180.dp)
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
                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painterResource(id = R.drawable.product_item),
                            contentDescription = "Product Image"
                        )
                    }

                }
                Spacer(modifier = Modifier.padding(15.dp))
                HorizontalPagerIndicator(
                    pagerState = rememberPagerState(),
                    indicatorWidth = 60.dp,
                    indicatorHeight = 5.dp,
                    spacing = 8.dp
                )
            }
        }

    }
}


package com.example.sneakers.presentation.ui.cart

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.sneakers.R
import com.example.sneakers.presentation.ui.SneakerUiDto
import com.example.sneakers.presentation.ui.toSneakerItemDao
import com.example.sneakers.presentation.ui.toSneakerUiDto
import com.example.sneakers.ui.theme.ThemeOrange
import com.example.sneakers.ui.theme.Typography

@Composable
fun CartScreen(cartViewModel: CartViewModel = hiltViewModel()) {
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
    val latestEvent = remember {
        mutableStateOf(Lifecycle.Event.ON_ANY)
    }
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _, event -> latestEvent.value = event }
        lifecycleOwner.addObserver(lifecycleObserver)
        onDispose { lifecycleOwner.removeObserver(lifecycleObserver) }
    }

    if (latestEvent.value == Lifecycle.Event.ON_RESUME) {
        cartViewModel.getCartItems()
    }

    val data = cartViewModel.cartItemList.collectAsState().value

    Surface {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                CartTopBar(onBackPressed = {
                    backPressedDispatcher?.onBackPressed()
                })
            }
            items(data.size) { index ->
                data[index]?.toSneakerUiDto()?.let { item ->
                    CartItemComponent(item) {
                        cartViewModel.removeItemFromCart(it.toSneakerItemDao())
                    }
                }
            }
            item {
                val mData = data.mapNotNull { it?.toSneakerUiDto() }
                OrderDetails(mData)
            }
        }
    }


}

@Composable
fun CartTopBar(onBackPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier
                .size(30.dp)
                .rotate(90f)
                .clickable { onBackPressed() },
            painter = painterResource(id = R.drawable.arrow_down),
            tint = ThemeOrange,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.weight(0.6f))
        Text(
            modifier = Modifier.weight(1f),
            text = "Cart",
            color = ThemeOrange,
            style = Typography.headlineLarge,
            fontWeight = FontWeight.Medium
        )

    }
}

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen()
}
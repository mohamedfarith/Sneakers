package com.example.sneakers.presentation.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.sneakers.R
import com.example.sneakers.constants.AppConstants
import com.example.sneakers.data.models.cart.SneakerItemDao
import com.example.sneakers.data.models.home.SneakerListDto
import com.example.sneakers.network.NetworkState
import com.example.sneakers.presentation.ui.SneakerUiDto
import com.example.sneakers.presentation.ui.cart.CartViewModel
import com.example.sneakers.presentation.ui.toSneakerItemDao
import com.example.sneakers.presentation.ui.toSneakerUiDto
import com.example.sneakers.ui.theme.ThemeOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    addToCartClicked: (SneakerItemDao) -> Unit,
    nextScreenRoute: (String, SneakerUiDto) -> Unit
) {
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
        homeViewModel.fetchSneakerData()
    }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CollapsableTopBar(scrollBehavior = scrollBehavior, onSearchClicked = {

            })
        }) {
        when (val state = homeViewModel.sneakerListData.collectAsState().value) {
            is NetworkState.Failure -> {
                //handle failure state
            }

            NetworkState.Loading -> {
                //handle loading state
            }

            is NetworkState.Success -> {
                val data = state.data as? SneakerListDto
                data?.let { sneakerdata ->
                    val list = sneakerdata.list
                    Column(
                        modifier = Modifier
                            .padding(it)
                    ) {
                        SortingComponent()
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(10.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                        ) {
                            items(list.size) {
                                SneakerItemComponent(list[it].toSneakerUiDto(),
                                    addToCartClicked = { sneakerUiDto ->
                                        addToCartClicked(
                                            sneakerUiDto.toSneakerItemDao()
                                        )
                                    },
                                    onCardClicked = { sneakerItem ->
                                        nextScreenRoute(
                                            AppConstants.ITEM_DESCRIPTION,
                                            sneakerItem
                                        )
                                    })
                            }
                        }

                    }
                }

            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsableTopBar(scrollBehavior: TopAppBarScrollBehavior, onSearchClicked: () -> Unit) {
    MediumTopAppBar(
        title = {
            Row {
                Text(
                    modifier = Modifier
                        .padding(20.dp)
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    text = "SNEAKERSHIP",
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = ThemeOrange
                    )
                )
                Icon(
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable { onSearchClicked() },
                    tint = ThemeOrange,
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search"
                )
            }


        },
        navigationIcon = {},
        actions = {},
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.White)
    )
}

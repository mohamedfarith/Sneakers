package com.example.sneakers.presentation.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.sneakers.constants.AppConstants
import com.example.sneakers.data.models.home.SneakerItemDto
import com.example.sneakers.network.NetworkState
import com.example.sneakers.presentation.ui.SneakerUiDto
import com.example.sneakers.presentation.ui.home.SneakerItemComponent
import com.example.sneakers.presentation.ui.toSneakerUiDto
import com.example.sneakers.ui.theme.LightGray
import com.example.sneakers.ui.theme.ThemeOrange
import com.example.sneakers.ui.theme.Typography

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    addToCartClicked: (SneakerUiDto) -> Unit,
    nextScreenRoute: (String, SneakerUiDto) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
    val latestEvent = remember {
        mutableStateOf(Lifecycle.Event.ON_ANY)
    }
    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _, event -> latestEvent.value = event }
        lifecycleOwner.addObserver(lifecycleObserver)
        onDispose { lifecycleOwner.removeObserver(lifecycleObserver) }
    }

    if (latestEvent.value == Lifecycle.Event.ON_RESUME) {
        searchViewModel.fetchSneakerData()
    }
    val searchText = searchViewModel.searchText.collectAsState()
    val sneakersList = searchViewModel.sneakerList.collectAsState(NetworkState.Loading)
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(30.dp),
            colors = androidx.compose.material3.TextFieldDefaults.colors(
                unfocusedContainerColor = LightGray,
                focusedContainerColor = LightGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            value = TextFieldValue(
                text = searchText.value,
                selection = TextRange(searchText.value.length)
            ),
            onValueChange = { search -> searchViewModel.onSearchTextChange(search.text) },
            placeholder = {
                Text(text = "Search")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        when (val state = sneakersList.value) {
            is NetworkState.Failure -> {

            }

            NetworkState.Loading -> {

            }

            is NetworkState.Success -> {
                val data = state.data as? List<SneakerItemDto?>?
                data?.let { newData ->
                    if (newData.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(
                                modifier = Modifier.padding(top = 10.dp),
                                textAlign = TextAlign.Center,
                                color = ThemeOrange,
                                text = "No Item found",
                                fontFamily = FontFamily.SansSerif,
                                style = Typography.titleMedium,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(10.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                        ) {
                            items(newData.size) {
                                newData[it]?.let { seekerData ->
                                    SneakerItemComponent(seekerData.toSneakerUiDto(),
                                        addToCartClicked = { sneakerUiDto ->
                                            addToCartClicked(sneakerUiDto)
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

}
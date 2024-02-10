package com.example.sneakers.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sneakers.R
import com.example.sneakers.constants.AppConstants
import com.example.sneakers.presentation.ui.cart.CartScreen
import com.example.sneakers.presentation.ui.cart.CartViewModel
import com.example.sneakers.presentation.ui.home.HomeScreen
import com.example.sneakers.presentation.ui.home.HomeViewModel
import com.example.sneakers.presentation.ui.home.ItemDescriptionScreen
import com.example.sneakers.presentation.ui.home.NavBottomBarWrapper
import com.example.sneakers.presentation.ui.home.NavigationItem
import com.example.sneakers.presentation.ui.search.SearchScreen
import com.example.sneakers.ui.theme.SneakersTheme
import com.example.sneakers.ui.theme.ThemeOrange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var cartViewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.white)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            val context = LocalContext.current
            val surfaceModifier = Modifier
            val navController = rememberNavController()
            homeViewModel = hiltViewModel()
            cartViewModel = hiltViewModel()

            LaunchedEffect(key1 = Unit) {
                coroutineScope.launch {
                    cartViewModel.toastMessage.collect { message ->
                        Toast.makeText(
                            context,
                            message,
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
            }
            androidx.compose.material.Scaffold(
                bottomBar = { NavBottomBarWrapper(navController) },
                floatingActionButton = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    val selected = currentRoute == NavigationItem.Home.route
                    Icon(
                        painter = painterResource(id = R.drawable.home_unselected_icon),
                        "Fab",
                        tint = if (selected) Color.White else ThemeOrange,
                        modifier = Modifier
                            .size(55.dp)
                            .animateContentSize()
                            .clip(RoundedCornerShape(30.dp))
                            .background(color = if (selected) ThemeOrange else Color.White)
                            .padding(if (selected) 15.dp else 12.dp)
                            .clickable {
                                navController.navigate(route = NavigationItem.Home.route)
                            })
                },
                isFloatingActionButtonDocked = true,
                floatingActionButtonPosition = androidx.compose.material.FabPosition.Center
            ) {
                Surface(modifier = surfaceModifier.padding(it)) {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationItem.Home.route
                    ) {
                        composable(NavigationItem.Home.route) {
                            BackHandler {
                                finish()
                            }
                            HomeScreen(
                                homeViewModel = homeViewModel,
                                cartViewModel,
                                nextScreenRoute = { screenRoute, item ->
                                    homeViewModel.setSneakerData(item)
                                    navController.navigate(screenRoute)

                                },
                                addToCartClicked = { itemDao ->
                                    cartViewModel.addItemToCart(itemDao.toSneakerItemDao())
                                },
                                onSearchClicked = {
                                    navController.navigate(AppConstants.SEARCH)
                                })
                        }
                        composable(AppConstants.ITEM_DESCRIPTION) {
                            ItemDescriptionScreen(viewModel = homeViewModel) { item ->
                                cartViewModel.addItemToCart(item.toSneakerItemDao())

                            }
                        }
                        composable(NavigationItem.Cart.route) {
                            CartScreen()

                        }
                        composable(AppConstants.SEARCH) {

                            SearchScreen(addToCartClicked = { itemDao ->
                                cartViewModel.addItemToCart(
                                    itemDao.toSneakerItemDao()
                                )
                            }, nextScreenRoute = { screenRoute, item ->
                                homeViewModel.setSneakerData(item)
                                navController.navigate(screenRoute)
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SneakersTheme {
        Greeting("Android")
    }
}
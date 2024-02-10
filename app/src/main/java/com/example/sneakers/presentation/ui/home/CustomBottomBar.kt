package com.example.sneakers.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sneakers.R
import com.example.sneakers.constants.AppConstants
import com.example.sneakers.ui.theme.ThemeOrange
import com.example.sneakers.ui.theme.WhiteTransparent

@Composable
fun NavBottomBarWrapper(navController: NavController) {
    BottomAppBar(
        modifier = Modifier
            .height(60.dp).shadow(shape = RoundedCornerShape(40.dp,40.dp,0.dp,0.dp), elevation = 10.dp)
            .clip(RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp)),
        cutoutShape = null,
        backgroundColor = Color.Transparent,
        elevation = 20.dp
    ) {
        BottomNavigationBar(navController = navController)
    }
}

sealed class NavigationItem(val route: String, val icon: Int? = null) {
    data object Home : NavigationItem(AppConstants.HOME)
    data object Cart : NavigationItem(AppConstants.CART, icon = R.drawable.cart_unselected_icon)

}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(backgroundColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItemList = listOf(
            NavigationItem.Cart
        )
        Spacer(modifier = Modifier.weight(1f))
        navigationItemList.forEach { item ->
            val selected = currentRoute == item.route
            BottomNavigationItem(selected = selected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                },
                icon = {
                    item.icon?.let { resId ->
                        val iconModifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(color = if (selected) ThemeOrange else Color.White)
                            .padding(if (selected) 8.dp else 4.dp)
                        Icon(
                            modifier = iconModifier,
                            painter = painterResource(id = resId),
                            tint = if (selected) Color.White else ThemeOrange,
                            contentDescription = item.route
                        )
                    }

                })

        }
    }
}
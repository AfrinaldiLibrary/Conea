package com.afrinaldi.conea

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.afrinaldi.conea.ui.navigation.NavigationItem
import com.afrinaldi.conea.ui.navigation.Screen
import com.afrinaldi.conea.ui.screen.about.AboutScreen
import com.afrinaldi.conea.ui.screen.detail.DetailScreen
import com.afrinaldi.conea.ui.screen.favorite.FavoriteScreen
import com.afrinaldi.conea.ui.screen.home.HomeScreen

@Composable
fun ConeaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(navigateToDetail = { name ->
                    navController.navigate(Screen.Detail.createRouteToDetail(name))
                })
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("name") {
                    type = NavType.StringType
                }),
            ) {
                val name = it.arguments?.getString("name")
                DetailScreen(name = name!!)
            }
            composable(
                route = Screen.Favorites.route
            ) {
                FavoriteScreen(navigateToDetail = {name ->
                    navController.navigate(Screen.Detail.createRouteToDetail(name))
                })
            }
            composable(route = Screen.About.route) {
                AboutScreen()
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(id = R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.favorites),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorites
            ),
            NavigationItem(
                title = stringResource(id = R.string.about),
                icon = Icons.Default.AccountCircle,
                screen = Screen.About
            ),
        )
        BottomNavigation {
            navigationItem.map {
                BottomNavigationItem(
                    selected = currentRoute == it.screen.route,
                    onClick = {
                        navController.navigate(it.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.title
                        )
                    },
                    label = {
                        Text(text = it.title)
                    },
                )
            }
        }
    }
}
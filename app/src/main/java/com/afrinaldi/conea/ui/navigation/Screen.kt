package com.afrinaldi.conea.ui.navigation

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Profile: Screen("profile")
}

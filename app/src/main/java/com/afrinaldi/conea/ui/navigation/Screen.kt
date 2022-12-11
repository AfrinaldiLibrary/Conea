package com.afrinaldi.conea.ui.navigation

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Detail: Screen("home/{name}") {
        fun createRouteToDetail (name : String) = "home/$name"
    }
    object About: Screen("about_page")
}

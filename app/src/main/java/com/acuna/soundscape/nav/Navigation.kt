package com.acuna.soundscape.nav

import androidx.navigation.NavController
import com.acuna.soundscape.nav.Destinations.Home

object Destinations {
    const val Home = "home"
    const val Details = "details/{id}"
}

class Action(private val navController: NavController) {
    val home: () -> Unit = { navController.navigate(Home) }
    val details: (id: String) -> Unit = { id ->
        navController.navigate("details/${id}")
    }
}
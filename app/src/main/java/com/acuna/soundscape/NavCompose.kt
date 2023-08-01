package com.acuna.soundscape

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acuna.soundscape.nav.Action
import com.acuna.soundscape.nav.Destinations.Details
import com.acuna.soundscape.nav.Destinations.Home
import com.acuna.soundscape.ui.theme.SoundScapeTheme
import com.acuna.soundscape.ui.view.screens.albumList.AlbumListScreen
import com.acuna.soundscape.ui.view.screens.details.DetailsScreen

@Composable
fun NavCompose() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController)}
    
    SoundScapeTheme {
        NavHost(navController = navController, startDestination = Home) {
            composable(Home) {
                AlbumListScreen { id ->
                    actions.details(id)
                }
            }
            composable(Details) { backStackEntry ->
                DetailsScreen(
                    id = backStackEntry.arguments?.getString("id"),
                    viewModel = hiltViewModel()
                )
            }
        }
        
    }
}
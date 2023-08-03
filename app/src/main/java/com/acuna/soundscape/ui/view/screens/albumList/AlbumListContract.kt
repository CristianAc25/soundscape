package com.acuna.soundscape.ui.view.screens.albumList

import com.acuna.soundscape.ui.view.screens.UiEvent

// Ui event triggered by the View
sealed class Event : UiEvent {
    data class SearchAlbumsByQuery(val searchQuery: String) : Event()
}
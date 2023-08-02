package com.acuna.soundscape.ui.view.screens.albumList

import com.acuna.soundscape.ui.view.screens.UiEvent

// Event to trigger by the View
sealed class Event : UiEvent {
    data class searchAlbumsByQuery(val searchQuery: String) : Event()
}
package com.acuna.soundscape.ui.view.screens.details

import com.acuna.soundscape.ui.view.screens.UiEvent

sealed class Event : UiEvent {
    data class GetAlbumsDetailsById(val id: String) : Event()
}
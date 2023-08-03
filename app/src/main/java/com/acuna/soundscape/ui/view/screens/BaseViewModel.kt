package com.acuna.soundscape.ui.view.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * Base class for [ViewModel] instances that supports a variety of per-operation states.
 */
abstract class BaseViewModel<Event : UiEvent> : ViewModel() {

  private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
  private val event = _event.asSharedFlow()

  init {
    subscribeEvents()
  }

  /**
   * Start listening to Event
   */
  private fun subscribeEvents() {
    viewModelScope.launch {
      event.collect {
        handleEvent(it)
      }
    }
  }

  /**
   * Handle each event
   */
  protected abstract fun handleEvent(event: Event)


  /**
   * Set new Event
   */
  fun setEvent(event: Event) {
    val newEvent = event
    viewModelScope.launch { _event.emit(newEvent) }
  }
}
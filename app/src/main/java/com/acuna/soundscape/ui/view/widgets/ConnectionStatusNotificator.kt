package com.acuna.soundscape.ui.view.widgets

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.acuna.soundscape.R
import com.acuna.soundscape.utils.ConnectionState
import com.acuna.soundscape.utils.currentConnectivityState
import com.acuna.soundscape.utils.observeConnectivityAsFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current

    return produceState(initialValue = context.currentConnectivityState) {
        context.observeConnectivityAsFlow().collect { value = it }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun ConnectivityStatus(scope: CoroutineScope, snackbarHostState: SnackbarHostState, context: Context) {
    val lastConnectionStatus = remember {
        mutableStateOf(true)
    }
    val connection by connectivityState()

    val isConnected = connection === ConnectionState.Available

    if (lastConnectionStatus.value != isConnected) {
        lastConnectionStatus.value = isConnected

        scope.launch {
            snackbarHostState.showSnackbar(
                if (isConnected)
                    context.getString(R.string.online_again)
                else
                    context.getString(R.string.no_internet_connection)
            )
        }
    }
}
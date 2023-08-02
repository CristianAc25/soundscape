package com.acuna.soundscape.utils

/**
    Transforms integer values into the corresponding [minutes:seconds] string representation.
**/
val Int.toMinutes: String
    get() {
        val minutes = this / 60
        val remainingSeconds = this % 60
        return "$minutes:${remainingSeconds.toString().padStart(2, '0')}"
    }


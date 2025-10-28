package com.lugra.gestor_ficheros

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PSP_Gestor_De_Ficheros",
    ) {
        App()
    }
}
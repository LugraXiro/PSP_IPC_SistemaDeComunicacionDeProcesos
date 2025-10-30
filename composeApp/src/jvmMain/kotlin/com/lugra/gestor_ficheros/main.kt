package com.lugra.gestor_ficheros

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.lugra.logic.Compilador

fun main() = application {
    // Compilar productores y consumidor antes de abrir interfaz
    println("Iniciando compilación automática de productores y consumidores...")
    Compilador.compilarTodo()
    println("Compilación completada.\n")

    // Abrir la interfaz principal
//    Window(
//        onCloseRequest = ::exitApplication,
//        title = "PSP_Gestor_De_Ficheros",
//    ) {
//        App()
//    }
}
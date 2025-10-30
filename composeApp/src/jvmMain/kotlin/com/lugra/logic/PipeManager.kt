package com.lugra.logic

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Scanner

object PipeManager {

    private const val BASE_PATH = "composeApp/src/jvmMain/kotlin/com/lugra/ejecutables/"

    fun iniciar() {
        val sc = Scanner(System.`in`)
        println("Selecciona el productor:")
        println("1. Productor de NÚMEROS aleatorios")
        println("2. Productor de LETRAS aleatorias")
        print("> ")

        when (sc.nextLine().trim()) {
            "1" -> ejecutarProductor("ProdNumerosAleatorios.jar")
            "2" -> ejecutarProductor("ProdLetrasAleatorias.jar")
            else -> println("Opción no válida.")
        }
    }

    private fun ejecutarProductor(nombreJar: String) {
        val sc = Scanner(System.`in`)
        print("¿Cuántos valores quieres generar? ")
        val cantidad = sc.nextLine()

        println("\nLanzando productor: $nombreJar\n")

        val productor = ProcessBuilder(
            "java", "-jar", "$BASE_PATH$nombreJar", cantidad
        ).start()

        val consumidor = ProcessBuilder(
            "java", "-jar", "${BASE_PATH}Consumidor.jar"
        ).start()

        // Bloque: conectar productor → consumidor
        Thread {
            productor.inputStream.copyTo(consumidor.outputStream)
            consumidor.outputStream.close()
        }.start()

        // Bloque: leer la salida del consumidor
        val reader = BufferedReader(InputStreamReader(consumidor.inputStream))
        reader.lines().forEach { println(it) }

        productor.waitFor()
        consumidor.waitFor()

        println("\nEjecución completada.")
    }
}

fun main() {
    PipeManager.iniciar()
}

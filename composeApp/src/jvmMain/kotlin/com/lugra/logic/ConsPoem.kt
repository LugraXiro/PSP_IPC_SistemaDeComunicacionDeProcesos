package com.lugra.logic

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Consumidor de poemas.
 * Recibe versos de texto, los cuenta y los muestra uno a uno.
 */
fun main() {
    println("[ConsumidorPoemas] Iniciado.\nEsperando versos...\n")

    val inBuf = BufferedReader(InputStreamReader(System.`in`))
    var versos = 0
    var line: String?

    while (inBuf.readLine().also { line = it } != null) {
        val v = line!!.trim()
        if (v.isNotEmpty()) {
            versos++
            println("Verso #$versos: \"$v\"")
        }
    }

    println("\nTotal de versos: $versos")
    println("[ConsumidorPoemas] Finalizado.")
}

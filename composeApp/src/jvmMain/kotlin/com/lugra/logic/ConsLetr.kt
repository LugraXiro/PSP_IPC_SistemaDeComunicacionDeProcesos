package com.lugra.logic

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Consumidor de letras aleatorias.
 * Cuenta las letras recibidas y muestra el total.
 */
fun main() {
    println("[ConsumidorLetras] Iniciado.\nEsperando letras...\n")

    val inBuf = BufferedReader(InputStreamReader(System.`in`))
    var count = 0
    var line: String?

    while (inBuf.readLine().also { line = it } != null) {
        val v = line!!.trim()
        if (v.matches(Regex("[A-Za-z]"))) {
            count++
            println("Letra recibida: $v | Total letras: $count")
        }
    }

    println("\nTotal de letras: $count")
    println("[ConsumidorLetras] Finalizado.")
}

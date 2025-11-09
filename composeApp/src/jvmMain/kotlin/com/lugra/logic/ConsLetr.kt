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
    var combinacion = ""
    var line: String?

    while (inBuf.readLine().also { line = it } != null) {
        val v = line!!.trim()
        if (v.matches(Regex("[A-Za-z]"))) {
            count++
            combinacion += v
            println("Letra recibida: $v | Combinación: $combinacion | Conteo $count")
            System.out.flush()
            Thread.sleep(300)
        }
    }

    println("\nCombinación de letas final: $combinacion")
    println("\nTotal de letras: $count")
    println("[ConsumidorLetras] Finalizado.")
}

package com.lugra.logic

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Consumidor de números aleatorios.
 * Lee números del flujo estándar, los suma y muestra el total.
 */
fun main() {
    println("[ConsumidorNumeros] Iniciado.\nEsperando números...\n")

    val inBuf = BufferedReader(InputStreamReader(System.`in`))
    var sum = 0
    var line: String?

    while (inBuf.readLine().also { line = it } != null) {
        val n = line!!.trim().toIntOrNull() ?: continue
        sum += n
        println("Recibido número: $n | Total parcial: $sum")
        System.out.flush()
        Thread.sleep(300)
    }

    println("\nSuma final: $sum")
    println("[ConsumidorNumeros] Finalizado.")
}

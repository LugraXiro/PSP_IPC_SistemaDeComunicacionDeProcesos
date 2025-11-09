package com.lugra.logic

import kotlin.random.Random

class ProdPoemasAleatorios {

    // Listas base: puedes ampliarlas
    private val sujetos = listOf(
        "El viento", "La luna", "Mi alma", "El río", "El tiempo", "Tu voz"
    )
    private val acciones = listOf(
        "susurra", "recuerda", "espera", "cae", "despierta", "olvida"
    )
    private val complementos = listOf(
        "sobre el agua", "en la noche", "sin rumbo", "bajo la lluvia", "entre las sombras"
    )

    // Genera un verso combinando elementos al azar
    fun producir(): String {
        val sujeto = sujetos.random()
        val accion = acciones.random()
        val complemento = complementos.random()
        return "$sujeto $accion $complemento."
    }
}

fun main(args: Array<String>) {
    val cantidadVersos = if (args.isNotEmpty()) args[0].toIntOrNull() ?: 4 else 4
    val productor = ProdPoemasAleatorios()

    repeat(cantidadVersos) {
        println(productor.producir()) // println → una línea por verso
        Thread.sleep(Random.nextLong(400, 900)) // simula ritmo de producción
    }
}

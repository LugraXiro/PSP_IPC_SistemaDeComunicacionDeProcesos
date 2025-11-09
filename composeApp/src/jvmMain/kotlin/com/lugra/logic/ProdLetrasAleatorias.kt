package com.lugra.logic

class ProdLetrasAleatorias {

    // Genera una letra aleatoria entre 'A' y 'Z'
    fun producir(): String {
        val letra = ('A'..'Z').random()
        return letra.toString()
    }
}

fun main(args: Array<String>) {
    val cantidadLetrasProducidas = if (args.isNotEmpty()) args[0].toIntOrNull() ?: 4
    else 4

    val productor = ProdLetrasAleatorias()

    repeat(cantidadLetrasProducidas) {
        println(productor.producir())
        System.out.flush()
        Thread.sleep(300) // simula una producción continua, no instantánea
    }
}
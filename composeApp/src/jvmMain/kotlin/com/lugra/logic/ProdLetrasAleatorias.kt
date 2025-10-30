package com.lugra.logic

class ProdLetrasAleatorias {

    // Genera una letra aleatoria entre 'A' y 'Z'
    fun producir(): String {
        val letra = ('A'..'Z').random()
        return letra.toString()
    }
}

fun main(args: Array<String>) {
    val cantidadLetrasProducidas = if (args.isNotEmpty()) args[0].toIntOrNull() ?: 5
    else 5

    val productor = ProdLetrasAleatorias()

    repeat(cantidadLetrasProducidas) {
        print(productor.producir())
        Thread.sleep(300) // simula una producción continua, no instantánea
    }
}
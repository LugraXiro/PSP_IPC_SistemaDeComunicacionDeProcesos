package com.lugra.logic

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var suma = 0
    var contadorLetras = 0
    var contadorVersos = 0

    var linea: String?
    while (reader.readLine().also { linea = it } != null) {
        val valor = linea!!.trim()

        when {
            // Caso 1: número entero
            valor.toIntOrNull() != null -> {
                val numero = valor.toInt()
                suma += numero
                println("Recibido número: $numero | Total parcial: $suma")
            }

            // Caso 2: letras individuales (A-Z o a-z)
            valor.matches(Regex("[A-Za-z]")) -> {
                contadorLetras++
                println("Recibida letra: $valor | Total letras: $contadorLetras")
            }

            // Caso 3: verso o frase (contiene espacios o palabras)
            valor.contains(" ") -> {
                contadorVersos++
                println("Verso recibido #$contadorVersos: \"$valor\"")
            }

            // Caso no identificado
            else -> println("Dato no reconocido: $valor")
        }
    }

    println("\n--- Resumen final ---")
    println("Suma total de números: $suma")
    println("Total de letras: $contadorLetras")
    println("Total de versos: $contadorVersos")
}

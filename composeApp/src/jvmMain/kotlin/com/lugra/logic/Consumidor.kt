package com.lugra.logic

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var suma = 0
    var linea: String?
    /*
    linea: String? significa que puede ser nulo.
     */

    while (reader.readLine().also { linea = it } != null){
        /*
        reader.readLine() -> lee una línea desde la entrada estándar (stdin)
            devuelve null cuando ya no hay más datos (cuando por ejemplo el productor termina y se cierra de golpe

        .also { linea=it } -> also ejecuta {} con el valor recién leído (it) sin interrumpir el flujo
            En este caso, sirve para guardar el valor den la variable linea.
            Equivale a:
                linea = reader.readLine()
                while (linea != null) {
                    ...
                    linea = reader.readLine()
                }

         */
        val numero = linea!!.toIntOrNull()
        /*
        linea!! -> fuerza a usar el valor no nulo (porque sabemos que si entró al bucle, no es null)
        .toIntOrNull() -> intenta convertir el texto a entero; si no puede, devuelve null.
         */
        if (numero != null) {
            suma += numero
            println("Recibido: $numero | Total parcial: $suma")
        }

        /*
        QUÉ HACE EL BUCLE:
            Lee continuamente líneas de texto.
            Guarda cada línea en linea.
            Si la línea no es null, la intenta convertir a número.
            Si es válida, la suma.
            Cuando el productor termina (no hay más datos → null), el bucle finaliza.
         */
    }

    println("Suma final: $suma")
}
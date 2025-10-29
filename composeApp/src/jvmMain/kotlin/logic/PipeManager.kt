package logic

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`)
    print("Cuántos números quieres generar?")
    val cantidad = sc.nextLine()

    val productor = ProcessBuilder("java", "-jar", "ProductorNumerosAleatorios.jar", cantidad).start()
    val consumidor = ProcessBuilder("java", "-jar", "Consumidor.jar").start()

    // Conectar salida del productor con entrada del consumidor
    Thread {
        productor.inputStream.copyTo(consumidor.outputStream)
        consumidor.outputStream.close()
    }.start()

    // Mostrar la salida del consumidor en consola
    val reader = BufferedReader(InputStreamReader(consumidor.inputStream))
    reader.lines().forEach { println(it) }

    productor.waitFor()
    consumidor.waitFor()
}
package com.lugra.logic

import java.io.BufferedReader
import java.io.InputStreamReader

object PipeManager {
    private const val BASE = "composeApp/src/jvmMain/kotlin/com/lugra/ejecutables/"

    /**
     * Ejecuta un productor y un consumidor conectados mediante pipes.
     * Cada línea de salida del consumidor se envía al callback [onOutput],
     * para mostrarse en la interfaz Compose en tiempo real.
     *
     * @param productorJar Nombre del archivo .jar del productor.
     * @param consumidorJar Nombre del archivo .jar del consumidor.
     * @param cantidad Cantidad de valores a generar por el productor.
     * @param onOutput Callback que recibe cada línea de salida.
     */
    fun ejecutar(
        productorJar: String,
        consumidorJar: String,
        cantidad: String = "5",
        onOutput: (String) -> Unit
    ) {
        try {
            // Lanzar productor y consumidor
            val productor = ProcessBuilder(
                "java", "-Dfile.encoding=UTF-8", "-jar", "$BASE$productorJar", cantidad
            ).start()

            val consumidor = ProcessBuilder(
                "java", "-Dfile.encoding=UTF-8", "-jar", "$BASE$consumidorJar"
            ).start()

            // Hilo que conecta la salida del productor con la entrada del consumidor
            val pipeThread = Thread {
                productor.inputStream.copyTo(consumidor.outputStream)
                consumidor.outputStream.close()
            }
            pipeThread.start()

            // Leer salida del consumidor línea a línea, en tiempo real
            val reader = BufferedReader(InputStreamReader(consumidor.inputStream, Charsets.UTF_8))
            var linea: String?
            while (reader.readLine().also { linea = it } != null) {
                onOutput(linea!!)
            }

            // Esperar a que ambos procesos terminen correctamente
            pipeThread.join()
            productor.waitFor()
            consumidor.waitFor()

            onOutput("\nEjecución completada.\n")

        } catch (e: Exception) {
            onOutput("Error: ${e.message}")
        }
    }
}

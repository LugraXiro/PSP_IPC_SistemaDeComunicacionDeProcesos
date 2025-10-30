package com.lugra.logic

import java.io.File

object Compilador {
    private const val KOTLINC = "kotlinc"
    private const val RUTA_FUENTE = "composeApp/src/jvmMain/kotlin/com/lugra/logic"
    private const val OUTPUT_DIR = "composeApp/src/jvmMain/kotlin/com/lugra/ejecutables"


    private val archivosACompilar = listOf(
        "Consumidor",
        "ProdLetrasAleatorias",
        "ProdNumerosAleatorios",
    )

    /**
     * Compila todos los archivos definidos en "archivosACompilar"
     * Se ejecuta automáticamente antes en main antes de abrir la UI
     */
    fun compilarTodo() {
        println("Iniciando compilación de productores y consumidores...\n")

        archivosACompilar.forEach { nombreArchivo ->
            compilar(nombreArchivo)
        }

        println("\nProceso de compilación terminado." )
    }

    /**
     * Compila un archivo .kt a .jar usando kotlinc.
     * Los guarda en la carpeta ejecutables.
     * @param nombreArchivo -> Nombre del archivo sin extensión (.kt)
     */
    private fun compilar(nombreArchivo: String) {
        val archivoFuente = "$RUTA_FUENTE/$nombreArchivo.kt"
        val salidaJar = "$OUTPUT_DIR/$nombreArchivo.jar"

        println("Compilando: $archivoFuente -> $salidaJar")

        val comando = listOf(
            KOTLINC,
            archivoFuente,
            "-include-runtime", //Incluye la librería de Kotlin dentro del .jar para que sea auto_ejecutable
            "-d", // parámetro que indica el archivo de salida. "salidaJar" en este caso
            salidaJar
        )

        try{
            val proceso = ProcessBuilder(comando).redirectErrorStream(true).start()

            val salida = proceso.inputStream.bufferedReader().readText()
            if (salida.isNotBlank()) println(salida)

            val codigo = proceso.waitFor()
            if (codigo == 0) {
                println("Compilación correcta: $nombreArchivo.jar\n")
            } else {
                println("ERROR al compilar $nombreArchivo.kt (código $codigo)\n")
            }

        } catch(e: Exception){
            println("ERROR ejecutando kotlinc: ${e.message}\n")
        }
    }
}
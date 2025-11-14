package com.lugra.logic

import java.io.File
import com.lugra.logic.getOS

object Compilador {
    private val KOTLINC: String =
        when (getOS()) {
            OS.WINDOWS -> "C:\\Program Files\\Kotlin\\kotlinc\\bin\\kotlinc.bat"
            OS.LINUX   -> "kotlinc"
            OS.MACOS   -> "/usr/local/bin/kotlinc"
            OS.UNKNOWN -> "kotlinc"
        }
    private const val RUTA_FUENTE = "composeApp/src/jvmMain/kotlin/com/lugra/logic"
    private const val OUTPUT_DIR = "composeApp/src/jvmMain/kotlin/com/lugra/ejecutables"


    private val archivosACompilar = listOf(
        "ProdLetrasAleatorias",
        "ProdNumerosAleatorios",
        "ProdPoemaAleatorio",
        "ConsLetr",
        "ConsNum",
        "ConsPoem",
    )

    /**
     * Compila todos los archivos definidos en "archivosACompilar".
     * Antes de compilar, limpia la carpeta de ejecutables para evitar residuos.
     * Se ejecuta automáticamente antes en main() antes de abrir la UI.
     */
    fun compilarTodo() {
        val rutaSalida = "composeApp/src/jvmMain/kotlin/com/lugra/ejecutables"
        val carpeta = java.io.File(rutaSalida)

        // 1. Borrar todo lo que haya dentro de la carpeta de ejecutables
        if (carpeta.exists() && carpeta.isDirectory) {
            carpeta.listFiles()?.forEach { archivo ->
                if (archivo.isFile) archivo.delete()
            }
            println("Carpeta de ejecutables limpiada correctamente.\n")
        } else {
            carpeta.mkdirs()
            println("Carpeta de ejecutables no existía, se ha creado.\n")
        }

        // 2. Compilar todos los archivos definidos
        println("Iniciando compilación de productores y consumidores...\n")
        archivosACompilar.forEach { nombreArchivo ->
            compilar(nombreArchivo)
        }

        println("\nProceso de compilación terminado.")
    }


    /**
     * Compila un archivo .kt a .jar usando kotlinc.
     * Los guarda en la carpeta ejecutables.
     * @param nombreArchivo -> Nombre del archivo sin extensión (.kt)
     */
    private fun compilar(nombreArchivo: String) {
        val archivoFuente = "$RUTA_FUENTE/$nombreArchivo.kt"
        val salidaJar = "$OUTPUT_DIR/$nombreArchivo.jar"
        val kotlinc = KOTLINC

        println("Compilando: $archivoFuente -> $salidaJar")

        // Ruta base del compilador Kotlin instalado en tu sistema
        val kotlinHome = "C:\\Program Files\\Kotlin\\kotlinc"
        val cp = listOf(
            "$kotlinHome\\lib\\kotlin-stdlib.jar",
            "$kotlinHome\\lib\\kotlin-stdlib-jdk7.jar",
            "$kotlinHome\\lib\\kotlin-stdlib-jdk8.jar"
        ).joinToString(";")

        val comando = listOf(
            kotlinc,
            archivoFuente,
            "-classpath", cp,
            "-include-runtime",
            "-d", salidaJar
        )

        try {
            val pb = ProcessBuilder(comando)
            pb.environment()["PATH"] = System.getenv("PATH") // hereda PATH real del sistema
            val proceso = pb.redirectErrorStream(true).start()

            val salida = proceso.inputStream.bufferedReader().readText()
            if (salida.isNotBlank()) println(salida)

            val codigo = proceso.waitFor()
            if (codigo == 0) {
                println("Compilación correcta: $nombreArchivo.jar\n")
            } else {
                println("ERROR al compilar $nombreArchivo.kt (código $codigo)\n")
            }
        } catch (e: Exception) {
            println("ERROR ejecutando kotlinc: ${e.message}\n")
        }
    }

}
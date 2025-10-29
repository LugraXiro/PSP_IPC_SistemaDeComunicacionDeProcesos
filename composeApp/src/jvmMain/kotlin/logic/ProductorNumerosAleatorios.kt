package logic

class ProductorNumerosAleatorios : Productor {

    override fun producir() : String {
        val numero = (1..100).random()
        return numero.toString()
    }
}

fun main (args: Array<String>) {
    val cantidadNumerosProducidos = if ( args.isNotEmpty() ) args[0].toIntOrNull() ?: 5
        // ?: 5 else 5 -> parece redundante pero no, actúan a distintos niveles
                                    // ?: 5 cubre el caso de que el argumento no sea numérico
                                    else 5
                                    // si el argumento no existe, asigna el valor 5

    val productor = ProductorNumerosAleatorios()

    repeat(cantidadNumerosProducidos) {
        print(productor.producir())
        Thread.sleep(300) // espera 0,3 segundos en generar otro número.
                                // interesante para simular una producción continua y no instantánea de datos
    }
}
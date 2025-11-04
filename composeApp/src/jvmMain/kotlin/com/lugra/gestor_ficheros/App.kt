package com.lugra.gestor_ficheros

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Row(Modifier.fillMaxSize()) {
            val productores = listOf(
                "Productor de Números Aleatorios",
                "Productor de Letras Aleatorias"
            )
            var productorSeleccionado by remember { mutableStateOf(productores.first()) }
            var salida by remember { mutableStateOf("Esperando datos...\n") }

            // BARRA LATERAL IZQUIERDA
            Column(
                modifier = Modifier
                    .width(220.dp)
                    .fillMaxHeight()
                    .background(Color(46, 58, 70))
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título
                Text(
                    "Productores",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Lista de productores
                productores.forEach { prod ->
                    val seleccionado = productorSeleccionado == prod
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(
                                color = if (seleccionado) Color(0xFF4A90E2) else Color(0xFF3A4752),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { productorSeleccionado = prod }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = prod,
                            color = if (seleccionado) Color.White else Color(0xFFB0BEC5),
                            fontSize = 14.sp
                        )
                    }
                }

                // Espaciador para empujar el botón al fondo
                Spacer(modifier = Modifier.weight(1f))

                // Botón INICIAR
                Button(
                    onClick = {
                        println("Iniciar presionado: $productorSeleccionado")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Iniciar", fontSize = 16.sp)
                }
            }

            // TERMINAL CENTRAL (ahora sí dentro del Row)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color(0xFF1E1E1E))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        salida,
                        color = Color(0xFF00FF00),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

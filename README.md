# Sistema de Comunicación entre Procesos (PSP)
Proyecto de Programación de Servicios y Procesos

Este proyecto demuestra cómo dos procesos independientes (productor y consumidor) pueden comunicarse mediante **pipes**, enviando datos de uno a otro en tiempo real.  
La interfaz está desarrollada con **Compose Desktop**, y permite seleccionar el productor, indicar la cantidad de datos y visualizar el consumo en directo.

---

## 🧩 Objetivos del proyecto

- Ejecutar procesos externos desde Kotlin usando `ProcessBuilder`.
- Comunicar productor y consumidor mediante pipes (`stdout → stdin`).
- Visualizar en pantalla los datos procesados en tiempo real.
- Permitir añadir nuevos productores/consumidores sin cambiar la lógica base.
- Cumplir los requisitos del módulo PSP.

---

## 🛠 Tecnologías utilizadas

- **Kotlin** (lenguaje principal)
- **Compose Desktop** (interfaz gráfica)
- **Java JVM 21**
- **ProcessBuilder (Java)** para ejecutar procesos
- **Pipes estándar** (`stdout`, `stdin`) para comunicación
- **Gradle 8.14.3** (generado por JetBrains Multiplatform Wizard)
- **Compilador interno kotlinc** para generar los `.jar` de productores/consumidores
- **Git + GitHub** (control de versiones)
- **Notion** (seguimiento del proyecto)

---

## 🧱 Arquitectura del sistema

El proyecto utiliza una arquitectura modular basada en procesos independientes:

### **1. Productores**
Programas independientes `.jar` que generan datos (números, letras, texto…).

### **2. Consumidores**
Procesos independientes `.jar` que reciben los datos y los procesan (sumas, conteos…).

### **3. PipeManager**
Módulo central que:
- ejecuta productor y consumidor,
- conecta sus flujos mediante pipes,
- procesa la salida del consumidor y la envía a la interfaz.

### **4. Interfaz gráfica (Compose)**
Permite seleccionar el productor, elegir la cantidad de datos y ver la salida en tiempo real.

---

## 🚀 Funcionalidades principales

- Selección de distintos productores desde la interfaz.
- Indicación de cuántos datos generar (por ejemplo, 5, 10, 15…).
- Ejecución simultánea de productor y consumidor como procesos externos.
- Visualización en tiempo real de los datos procesados.
- Mensajes de finalización y control básico de errores.
- Posibilidad de añadir nuevos productores y consumidores fácilmente.

---

## 📘 Manual de uso (rápido)

### 1. Clonar el repositorio
```bash
git clone https://github.com/LugraXiro/PSP_IPC_SistemaDeComunicacionDeProcesos.git
```
### 2. Abrir el proyecto
Abrir el proyecto con **IntelliJ IDEA 2025.2.2** o una versión compatible.  
Es importante asegurarse de que la **JVM configurada en Gradle** sea la adecuada (recomendado OpenJDK 21).

### 3. Ejecutar la aplicación
Para lanzar la aplicación, simplemente ejecuta el archivo:
```bash
composeApp/src/jvmMain/kotlin/com/lugra/gestor_ficheros/main.kt
```

Al iniciar:

1. Se compilan automáticamente los productores y consumidores (`Compilador.kt`).
2. Se abre la interfaz gráfica del proyecto.

### 4. Usar la interfaz
- Selecciona uno de los productores disponibles en la barra lateral.
- Introduce la cantidad de datos que deseas generar.
- Pulsa **Iniciar**.
- Observa cómo los datos generados son consumidos y mostrados en la terminal integrada.

---

## ➕ Cómo añadir nuevos productores/consumidores

1. Crear un archivo `.kt` para el productor y otro para el consumidor.
2. Guardarlos en:  
```bash
composeApp/src/jvmMain/kotlin/com/lugra/logic
```
3. Añadir sus nombres en la lista `archivosACompilar` del archivo `Compilador.kt`.
4. Verificar que la ruta al compilador `kotlinc` es correcta según tu sistema operativo.
5. Editar `App.kt`:
- Añadir el nombre del nuevo productor a la lista `productores`.
- Añadir una entrada en el mapa `pares`, relacionando el nombre de la caja con el `.jar` del productor y el `.jar` del consumidor.
6. Ejecutar el proyecto para que se compile todo automáticamente.

---

## 🧪 Pruebas realizadas

- Comunicación correcta entre productor y consumidor mediante pipes.
- Funcionamiento validado en Linux y Windows.
- Comprobación con valores altos:
- Por cada **200 datos**, aproximadamente **1 minuto** de espera debido a la simulación `Thread.sleep(300)`.
- Verificación del formato UTF-8:
- Linux: correcto.
- Windows: problemas con la ñ y tildes.
- UI responsiva: se adapta correctamente al redimensionar la ventana.

---

## 📌 Conclusiones

El sistema cumple los requisitos del módulo PSP y demuestra efectivamente la comunicación entre procesos usando pipes.  
La estructura del proyecto es modular y facilita añadir nuevos productores/consumidores.  
Las principales dificultades surgieron en la configuración de `kotlinc` y en la gestión del texto UTF-8 en Windows.

---

## 📚 Referencias

- Android Developers. *Jetpack Compose Documentation.*  
  https://developer.android.com/develop/ui/compose/documentation

- JetBrains. *Kotlin Documentation.*  
  https://kotlinlang.org/docs/home.html

- JetBrains. *Getting Started with IntelliJ IDEA.*  
  https://www.jetbrains.com/help/idea/getting-started.html

- IES Cotarelo Valedor. *Aula Virtual PSP.*  
  https://centros.edu.xunta.gal/iescotarelovilagarcia/aulavirtual/

- OpenAI. *ChatGPT (versión GPT-5.1).*  
  https://chat.openai.com

---

## 🤖 Uso de IA en el proyecto (resumen)

Se empleó IA para:
- Aclaración de conceptos técnicos.
- Explicar piezas de código para mejorar su documentación.
- Diagnóstico de errores como rutas o classpaths.
- Ayuda en formateo de la memoria (numeración, subtítulos, etc.).





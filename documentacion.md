## **DOCUMENTACION**

**acxel.canales@unah.hn**



Hacer un programa en Java que lea 8 calificaciones ingresadas por el usuario, las valida y genera un reporte estadístico con los resultados mostrados en consola.



##### **Métodos usados en el programa**



###### **1.- main(String\[] args)**

Punto de entrada del programa. Inicializa el Scanner, muestra el encabezado y controla el bucle principal. Si la validación falla, pregunta al usuario si desea intentar nuevamente. Termina cuando la entrada es valida o el usuario decide salir.

###### 

**2.- esNumero(String valor)**
-> static boolean esNumero(String valor)

Verifica carácter por carácter si una cadena representa un entero valido, aceptando signo "+" o "-" al inicio. Retorna `true` si es valido, `false` en caso contrario.

|Entrada|Retorna|
|-|-|
|"85"|true|
|"-5"|true|
|"noventa"|false|
|""|false|
|null|false|



**3.- lineaHorizontal()**
-> static void lineaHorizontal()

Imprime una línea del tipo "+----...----+" usando la constante "ANCHO" (52) para definir el largo. Se usa como borde superior e inferior del cuadro.



###### **4.- fila(String contenido)**

\-> static void fila(String contenido)

Imprime una línea con bordes "|" en ambos lados. Rellena con espacios hasta completar exactamente "ANCHO" caracteres, garantizando que el borde derecho quede siempre alineado. Usa "printf" con "%-52s".



**5.- filaVacia()**
-> static void filaVacia()

Llama a "fila("")" para imprimir una línea en blanco con los bordes laterales.
Se usa como separador dentro del reporte.



**6.- leerCalificaciones(Scanner scanner)**
-> static int\[] leerCalificaciones(Scanner scanner)

Lee una línea de texto, la divide por comas y convierte cada parte en entero
usando "esNumero()". Si un valor no es numérico, lo marca como "Integer.MIN\_VALUE" para ser detectado luego en la validación. Retorna un entero con tantos elementos como partes haya en la entrada.



**7.- validarCalificaciones(int\[] calificaciones)**
-> static boolean validarCalificaciones(int\[] calificaciones)

Recorre el arreglo y detecta tres tipos de error:

* Valor no numérico
* Valor fuera del rango \[0, 100]
* Cantidad distinta de 8 elementos

Acumula los mensajes de error en un String y los imprime si hay al menos uno. Retorna "true" si todo es valido, "false" si hay errores.



###### **8.- calcularPromedio(int\[] calificaciones)**

\-> static double calcularPromedio(int\[] calificaciones)

Suma todos los elementos del arreglo y divide entre su longitud.
Promedio = (suma de calificaciones\[i]) / n; donde n = 8



**9.- encontrarMaximo(int\[] calificaciones)**
-> static int encontrarMaximo(int\[] calificaciones)

Recorre el arreglo comparando cada elemento con el maximo actual. Retorna el valor mas alto encontrado.



**10.- encontrarMinimo(int\[] calificaciones)**
-> static int encontrarMinimo(int\[] calificaciones)

Igual que "encontrarMaximo" pero guarda el valor mas bajo. Retorna el minimo.



**11.- contarAprobados(int\[] calificaciones)**
-> static int contarAprobados(int\[] calificaciones)

Cuenta cuantos elementos son mayores o iguales a 70. Retorna ese conteo como
entero. Los reprobados se calculan como 8 - aprobados, directamente en "mostrarReporte".



**12.- mostrarReporte(int\[] calificaciones)**
-> static void mostrarReporte(int\[] calificaciones)

Llama a todos los métodos de calculo y presenta los resultados dentro del cuadro. Usa "printf" para alinear cada valor numérico al borde derecho. Imprime las calificaciones manualmente calculando los espacios necesarios para mantener el borde alineado.



##### **Flujo del Programa**


Inicio
  |
  v
Encabezado
  |
  v
+---------------------------+  
|  Entrada del usuario      | <-- Lee línea, divide por comas, convierte a int\[]     
+---------------------------+
  |
  v
+---------------------------+
|  validarCalificaciones()  |  -- Rango \\\[0,100]?
|  Verifica los datos       |  -- Exactamente 8 valores?
+---------------------------+  -- Valores numéricos?
  |              |
  | valido       | invalido
  |              v
  |        Mostrar errores
  |              |
  |        Reintentar? (s/n)
  |            /    \\
  |           s      n
  |           |      |
  |    (volver a    Fin
  |     leer)
  v
+---------------------------+
|  mostrarReporte()         |  -- calcularPromedio()
|  Generar estadisticas     |  -- encontrarMaximo()
+---------------------------+  -- encontrarMinimo()
  |                            -- contarAprobados()
  v
Fin


##### **Instrucciones de Compilación y Ejecución**

###### **---> Compilar**

Ubicarse en la carpeta donde esta el archivo y ejecutar:
javac EstadisticasDeCalificaciones.java

Esto genera el archivo EstadisticasDeCalificaciones.class en la misma carpeta.

###### 

###### **---> Ejecutar**

java EstadisticasDeCalificaciones




##### **Ejemplos de Salida**

###### 

###### **1.- Caso valido.**

Datos ingresados: 85, 92, 78, 88, 95, 75, 82, 90
+----------------------------------------------------+
|  SISTEMA DE CALIFICACIONES ESTUDIANTILES           |
+----------------------------------------------------+

Ingrese 8 calificaciones separadas por comas:
+----------------------------------------------------+
|         REPORTE DE CALIFICACIONES                  |
+----------------------------------------------------+
|                                                    |
|  Calificaciones ingresadas:                        |
|  85 - 92 - 78 - 88 - 95 - 75 - 82 - 90             |
|                                                    |
|  --- ESTADISTICAS ---                              |
|                                                    |
|  Promedio:              85.63                      |
|  Calificación maxima:   95                         |
|  Calificación minima:   75                         |
|                                                    |
|  --- ANALISIS DE RESULTADOS ---                    |
|                                                    |
|  Cantidad de aprobados  (>=70): 8                  |
|  Cantidad de reprobados  (<70): 0                  |
|  Porcentaje de aprobados:       100.0%             |
|                                                    |
+----------------------------------------------------+


###### **2.- Caso invalido.**

Datos ingresados: 85, 92, 120, -10, 95


X ERROR DE VALIDACION
Calificaciones ingresadas: 85, 92, 120, -10, 95

Problemas detectados:
  1. Calificación fuera de rango en posición 3: 120
     -> Las calificaciones deben estar entre 0 y 100

  2. Calificación fuera de rango en posición 4: -10
     -> Las calificaciones deben estar entre 0 y 100

  3. Cantidad insuficiente de calificaciones: 5
     -> Se requieren exactamente 8 calificaciones

Acciones:
  --> Intenta nuevamente
  --> Verifica que cada calificación este entre 0 y 100
  --> Asegúrate de ingresar 8 valores separados por comas




import java.util.Scanner;
 
public class CalculadoraDeCalificaciones {
 
    static final int ANCHO = 52;
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
 
        System.out.println(lineaHorizontal());
        System.out.println(fila("  SISTEMA DE CALIFICACIONES ESTUDIANTILES"));
        System.out.println(lineaHorizontal());
 
        while (continuar) {
            int[] calificaciones = leerCalificaciones(scanner);
 
            if (validarCalificaciones(calificaciones)) {
                mostrarReporte(calificaciones);
                continuar = false;
            } else {
                System.out.print("\nDesea intentar nuevamente? (s/n): ");
                String respuesta = scanner.nextLine().trim().toLowerCase();
                if (!respuesta.equals("s")) {
                    continuar = false;
                    System.out.println("\nPrograma finalizado.");
                }
            }
        }
 
        scanner.close();
    }
 
    // Verifica si una cadena representa un numero entero valido (con signo opcional)
    static boolean esNumero(String valor) {
        if (valor == null || valor.length() == 0) return false;
        int inicio = (valor.charAt(0) == '-' || valor.charAt(0) == '+') ? 1 : 0;
        if (inicio == valor.length()) return false;
        for (int i = inicio; i < valor.length(); i++) {
            if (valor.charAt(i) < '0' || valor.charAt(i) > '9') return false;
        }
        return true;
    }
 
    // Construye una linea horizontal: +---...---+
    static String lineaHorizontal() {
        StringBuilder sb = new StringBuilder();
        sb.append('+');
        for (int i = 0; i < ANCHO; i++) sb.append('-');
        sb.append('+');
        return sb.toString();
    }
 
    // Construye una fila con bordes '|' y el contenido alineado al ancho fijo
    static String fila(String contenido) {
        if (contenido.length() > ANCHO) {
            contenido = contenido.substring(0, ANCHO);
        }
        int espacios = ANCHO - contenido.length();
        StringBuilder sb = new StringBuilder();
        sb.append('|');
        sb.append(contenido);
        for (int i = 0; i < espacios; i++) sb.append(' ');
        sb.append('|');
        return sb.toString();
    }
 
    // Fila en blanco
    static String filaVacia() {
        return fila("");
    }
 
    // Lee 8 calificaciones ingresadas por el usuario separadas por comas
    static int[] leerCalificaciones(Scanner scanner) {
        System.out.println("\nIngrese 8 calificaciones separadas por comas:");
        String linea = scanner.nextLine().trim();
 
        String[] partes = linea.split(",");
        int[] calificaciones = new int[partes.length];
 
        for (int i = 0; i < partes.length; i++) {
            String valor = partes[i].trim();
            if (esNumero(valor)) {
                calificaciones[i] = Integer.parseInt(valor);
            } else {
                System.out.println("ERROR: El valor \"" + valor + "\" en la posicion " + (i + 1) + " no es un numero valido.");
                calificaciones[i] = Integer.MIN_VALUE;
            }
        }
 
        return calificaciones;
    }
 
    // Valida las calificaciones segun las reglas del programa
    static boolean validarCalificaciones(int[] calificaciones) {
        boolean valido = true;
        int errorCount = 1;
        StringBuilder errores = new StringBuilder();
 
        for (int i = 0; i < calificaciones.length; i++) {
            if (calificaciones[i] == Integer.MIN_VALUE) {
                errores.append("  ").append(errorCount++).append(". Valor no numerico en posicion ").append(i + 1).append("\n");
                errores.append("     -> Solo se permiten valores numericos enteros\n\n");
                valido = false;
            } else if (calificaciones[i] < 0 || calificaciones[i] > 100) {
                errores.append("  ").append(errorCount++).append(". Calificacion fuera de rango en posicion ").append(i + 1).append(": ").append(calificaciones[i]).append("\n");
                errores.append("     -> Las calificaciones deben estar entre 0 y 100\n\n");
                valido = false;
            }
        }
 
        if (calificaciones.length != 8) {
            errores.append("  ").append(errorCount++).append(". Cantidad ");
            errores.append(calificaciones.length < 8 ? "insuficiente" : "excesiva");
            errores.append(" de calificaciones: ").append(calificaciones.length).append("\n");
            errores.append("     -> Se requieren exactamente 8 calificaciones\n\n");
            valido = false;
        }
 
        if (!valido) {
            System.out.println("\nX ERROR DE VALIDACION\n");
            System.out.print("Calificaciones ingresadas: ");
            for (int i = 0; i < calificaciones.length; i++) {
                System.out.print(calificaciones[i] == Integer.MIN_VALUE ? "?" : calificaciones[i]);
                if (i < calificaciones.length - 1) System.out.print(", ");
            }
            System.out.println("\n\nProblemas detectados:");
            System.out.print(errores);
            System.out.println("Acciones:");
            System.out.println("  --> Intenta nuevamente");
            System.out.println("  --> Verifica que cada calificacion este entre 0 y 100");
            System.out.println("  --> Asegurate de ingresar 8 valores separados por comas");
        }
 
        return valido;
    }
 
    // Calcula el promedio
    static double calcularPromedio(int[] calificaciones) {
        double suma = 0;
        for (int cal : calificaciones) suma += cal;
        return suma / calificaciones.length;
    }
 
    // Encuentra el maximo
    static int encontrarMaximo(int[] calificaciones) {
        int maximo = calificaciones[0];
        for (int i = 1; i < calificaciones.length; i++)
            if (calificaciones[i] > maximo) maximo = calificaciones[i];
        return maximo;
    }
 
    // Encuentra el minimo
    static int encontrarMinimo(int[] calificaciones) {
        int minimo = calificaciones[0];
        for (int i = 1; i < calificaciones.length; i++)
            if (calificaciones[i] < minimo) minimo = calificaciones[i];
        return minimo;
    }
 
    // Cuenta aprobados (>= 70)
    static int contarAprobados(int[] calificaciones) {
        int aprobados = 0;
        for (int cal : calificaciones)
            if (cal >= 70) aprobados++;
        return aprobados;
    }
 
    // Genera el reporte con bordes ASCII alineados
    static void mostrarReporte(int[] calificaciones) {
        double promedio   = calcularPromedio(calificaciones);
        int    maximo     = encontrarMaximo(calificaciones);
        int    minimo     = encontrarMinimo(calificaciones);
        int    aprobados  = contarAprobados(calificaciones);
        int    reprobados = calificaciones.length - aprobados;
        double porcentaje = ((double) aprobados / calificaciones.length) * 100;
 
        StringBuilder listaCals = new StringBuilder();
        for (int i = 0; i < calificaciones.length; i++) {
            listaCals.append(calificaciones[i]);
            if (i < calificaciones.length - 1) listaCals.append(" - ");
        }
 
        System.out.println();
        System.out.println(lineaHorizontal());
        System.out.println(fila("         REPORTE DE CALIFICACIONES"));
        System.out.println(lineaHorizontal());
        System.out.println(filaVacia());
        System.out.println(fila("  Calificaciones ingresadas:"));
        System.out.println(fila("  " + listaCals.toString()));
        System.out.println(filaVacia());
        System.out.println(fila("  --- ESTADISTICAS ---"));
        System.out.println(filaVacia());
        System.out.println(fila(String.format("  Promedio:              %.2f", promedio)));
        System.out.println(fila(String.format("  Calificacion maxima:   %d", maximo)));
        System.out.println(fila(String.format("  Calificacion minima:   %d", minimo)));
        System.out.println(filaVacia());
        System.out.println(fila("  --- ANALISIS DE RESULTADOS ---"));
        System.out.println(filaVacia());
        System.out.println(fila(String.format("  Cantidad de aprobados  (>=70): %d", aprobados)));
        System.out.println(fila(String.format("  Cantidad de reprobados  (<70): %d", reprobados)));
        System.out.println(fila(String.format("  Porcentaje de aprobados:       %.1f%%", porcentaje)));
        System.out.println(filaVacia());
        System.out.println(lineaHorizontal());
    }

}

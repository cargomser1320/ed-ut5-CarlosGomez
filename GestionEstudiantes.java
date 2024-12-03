import java.util.Scanner;

// Clase para representar a un Estudiante
class Estudiante {
    String nombre;
    int edad;
    double notaFinal;

    // Constructor de la clase Estudiante
    Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.notaFinal = -1; // Inicializa la nota a -1 para indicar que aún no ha sido asignada
    }

    // Método para representar al estudiante como un String
    public String toString() {
        // Si la nota es -1, se indica que no ha sido calificado
        String notaTexto = (notaFinal == -1) ? "Sin calificar" : String.format("%.2f", notaFinal);
        return "Nombre: " + nombre + ", Edad: " + edad + ", Nota Final: " + notaTexto;
    }
}

public class GestionEstudiantes {
    // Array de estudiantes
    static Estudiante[] estudiantes;
    // Scanner para leer la entrada del usuario
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicializa el array de estudiantes
        inicializarEstudiantes();
        // Muestra el menú y ejecuta las opciones
        mostrarMenu();
    }

    // Método para inicializar el array de estudiantes
    public static void inicializarEstudiantes() {
        estudiantes = new Estudiante[] {
                new Estudiante("Amaya", 25),
                new Estudiante("Iván", 27),
                new Estudiante("Carlos", 28),
                new Estudiante("Manuel", 22),
                new Estudiante("Oswaldo", 29)
        };
    }

    // Método para mostrar el menú y gestionar las opciones del usuario
    public static void mostrarMenu() {
        int opcion;
        do {
            // Muestra las opciones del menú
            System.out.println("\nMenú de Gestión de Estudiantes");
            System.out.println("1. Introducir nota de un estudiante");
            System.out.println("2. Calcular la nota media del curso");
            System.out.println("3. Mostrar información de los estudiantes");
            System.out.println("4. Mostrar estudiantes por inicial");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            // Lee la opción seleccionada
            opcion = leerEntero();

            // Control de flujo para ejecutar la opción seleccionada
            switch (opcion) {
                case 1 -> introducirNota();              // Opción 1: Introducir nota
                case 2 -> calcularNotaMedia();           // Opción 2: Calcular media
                case 3 -> mostrarInformacionEstudiantes(); // Opción 3: Mostrar estudiantes
                case 4 -> mostrarEstudiantesPorInicial(); // Opción 4: Mostrar estudiantes por inicial
                case 5 -> System.out.println("Saliendo del programa..."); // Opción 5: Salir
                default -> System.out.println("Opción inválida. Intente de nuevo."); // Opción inválida
            }
        } while (opcion != 5); // Repite hasta que se seleccione la opción 5
    }

    // Método para introducir la nota de un estudiante
    public static void introducirNota() {
        // Muestra la lista de estudiantes
        System.out.println("\nSeleccione un estudiante:");
        for (int i = 0; i < estudiantes.length; i++) {
            System.out.println((i + 1) + ". " + estudiantes[i].nombre);
        }

        // Lee el índice del estudiante seleccionado
        System.out.print("Ingrese el número del estudiante: ");
        int index = leerEntero() - 1;
        if (index >= 0 && index < estudiantes.length) {
            // Solicita la nota del estudiante
            System.out.print("Ingrese la nota final (0-10): ");
            double nota = leerDouble();
            if (nota >= 0 && nota <= 10) {
                estudiantes[index].notaFinal = nota; // Asigna la nota al estudiante
                System.out.println("Nota ingresada correctamente.");
            } else {
                // Si la nota no es válida
                System.out.println("Nota fuera de rango. Intente de nuevo.");
            }
        } else {
            // Si el índice no es válido
            System.out.println("Estudiante no válido. Intente de nuevo.");
        }
    }

    // Método para calcular y mostrar la nota media del curso
    public static void calcularNotaMedia() {
        double suma = 0;
        int contador = 0;
        for (Estudiante e : estudiantes) {
            if (e.notaFinal >= 0) {
                suma += e.notaFinal;
                contador++;
            }
        }
        // Calcula la media, si hay estudiantes con nota
        double media = (contador > 0) ? suma / contador : 0;
        System.out.printf("La nota media del curso es: %.2f\n", media);
    }

    // Método para mostrar la información de todos los estudiantes
    public static void mostrarInformacionEstudiantes() {
        System.out.println("\nInformación de los estudiantes:");
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }
        // Llama a calcular la media después de mostrar la información
        calcularNotaMedia();
    }

    // Método para mostrar estudiantes cuyo nombre empieza con una letra dada
    public static void mostrarEstudiantesPorInicial() {
        System.out.print("Ingrese una letra inicial: ");
        char inicial = scanner.next().charAt(0); // Lee la letra inicial

        System.out.println("\nEstudiantes que empiezan con '" + inicial + "':");
        boolean encontrados = false;
        for (Estudiante e : estudiantes) {
            if (e.nombre.toUpperCase().charAt(0) == Character.toUpperCase(inicial)) {
                System.out.println(e); // Muestra la información del estudiante
                encontrados = true;
            }
        }

        // Si no se encontraron estudiantes con esa inicial
        if (!encontrados) {
            System.out.println("No se encontraron estudiantes con esa inicial.");
        }
        // Muestra la media de los estudiantes seleccionados
        calcularNotaMedia();
    }

    // Método para leer un entero del usuario
    public static int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Intente de nuevo: ");
            scanner.next(); // Descarta la entrada incorrecta
        }
        return scanner.nextInt();
    }

    // Método para leer un número decimal (double)
    public static double leerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Entrada inválida. Intente de nuevo: ");
            scanner.next(); // Descarta la entrada incorrecta
        }
        return scanner.nextDouble();
    }
}

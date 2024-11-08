import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Clase que representa un libro en la biblioteca
class Libro {
    String titulo; // Título del libro
    String autor;
    String genero;
    boolean disponible;

    // Constructor de la clase Libro
    public Libro(String titulo, String autor, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponible = true; // El libro está disponible inicialmente
    }

    // Metodo para mostrar la información del libro en formato String

    public String toString() {
        return "Titulo: " + titulo + ", Autor: " + autor + ", Genero: " + genero + ", Disponivilidad: " + disponible;
    }
}
// Clase que representa un préstamo de un libro
class Prestamo {
    Libro libro; // Libro prestado
    Date fechadelprestamo; // Fecha en la que se hizo el préstamo


    // Constructor de la clase Prestamo
    public Prestamo(Libro book) {
        this.libro = book;
        this.fechadelprestamo = new Date();
    }

    // Metodo para mostrar la información del préstamo en formato String
    public String toString() {
        return "Libro: " + libro.titulo + "Fecha del préstamo: " + fechadelprestamo;
    }
}
// Clase que representa la biblioteca y sus funcionalidades
class libreria {
    private List<Libro> libros;  // Lista de libros disponibles en la biblioteca
    private List<Prestamo> historialprestamos; // Historial de préstamos realizados

    // Constructor de la clase libreria
    public libreria() {
        libros = new ArrayList<>(); // Inicializa la lista de libros
        historialprestamos = new ArrayList<>(); // Inicializa el historial de préstamos
        añadirLibros(); // Añade libros de ejemplo a la biblioteca
    }
    // MEtodo privado que añade libros de ejemplo a la biblioteca
    private void añadirLibros() {
        libros.add(new Libro("Titulo1", "autor3", "gen"));
        libros.add(new Libro("Titulo2", "autor2", "gen"));
        libros.add(new Libro("Titulo3", "autor", "gen"));
    }

    // Metodo para mostrar el stock de libros en la biblioteca
    public void verStock() {
        System.out.println("Libros disponibles:");
        for (Libro libro : libros) {
            System.out.println(libro);// Muestra cada libro y su disponibilidad
        }
    }
    // Metodo para solicitar un préstamo de un libro por su título
    public void PedirPrestamo(String titulo) {
        for (Libro libro : libros) {
            // Verifica si el título coincide y el libro está disponible
            if (libro.titulo.equalsIgnoreCase(titulo) && libro.disponible) {
                libro.disponible = false; // Marca el libro como no disponible
                historialprestamos.add(new Prestamo(libro)); // Añade el préstamo al historial
                System.out.println("Usted ha tomado prestado con éxito:" + libro.titulo);
                return;
            }
        }
        System.out.println("Libro no disponible para préstamo.");
    }
    // Metodo para devolver un libro por su título
    public void DevolverLibro(String titulo) {
        for (Prestamo historial : historialprestamos) {
            // Verifica si el título coincide y el libro está prestado
            if (historial.libro.titulo.equalsIgnoreCase(titulo) && !historial.libro.disponible) {
                historial.libro.disponible = true; // Marca el libro como disponible
                System.out.println("Has regresado exitosamente: " + historial.libro.titulo);
                return;
            }
        }
        System.out.println("Este libro no fue prestado o no existe.");
    }

    // Metodo para ver el historial de préstamos realizados
    public void Verhistorialprestamos() {
        System.out.println("Historial de préstamos:");
        for (Prestamo prestamo : historialprestamos) {
            System.out.println(prestamo);
        }
    }
}
 // Clase principal que ejecuta el programa de la biblioteca
public class Part1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        libreria libreria = new libreria();
        boolean salir = false; // Variable para controlar el bucle principal

        // Bucle principal del programa
        while (!salir) {
            // Muestra el menú de opciones
            System.out.println("\n--- Biblioteca ---");
            System.out.println("1. Ver stock de libros");
            System.out.println("2. Solicitar un préstamo");
            System.out.println("3. Devolver un libro");
            System.out.println("4. Ver historial de préstamos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt(); // Lee la opción seleccionada por el usuario
            scanner.nextLine(); // Consume la línea para evitar errores de entrada

            // Ejecuta la opción seleccionada
            switch (opcion) {
                case 1:
                    libreria.verStock(); // Muestra el stock de libro
                    break;
                case 2:
                    System.out.print("Introduzca el título del libro que desea pedir prestado: "); // Solicita un préstamo de libro
                    String loanTitle = scanner.nextLine();
                    libreria.PedirPrestamo(loanTitle);
                    break;
                case 3:
                    System.out.print("Introduzca el título del libro que desea devolver: "); // Devuelve un libro
                    String titulodevolucion = scanner.nextLine();
                    libreria.DevolverLibro(titulodevolucion);
                    break;
                case 4:
                    libreria.Verhistorialprestamos(); // Muestra el historial de préstamos
                    break;
                case 5:
                    salir = true;
                    System.out.println("Saliendo de la biblioteca..."); // Cambia la variable salir a true para terminar el bucl
                    break;
                default:
                    System.out.println("Opción no válida."); // Mensaje para opciones no válidas
            }
        }
    }
}

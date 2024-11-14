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
        return "Titulo: " + titulo + ", Autor: " + autor + ", Genero: " + genero + ", Disponibilidad: " + (disponible ? "Disponible" : "No disponible");
    }
}

// Clase que representa un préstamo de un libro
class Prestamo {
    Libro libro; // Libro prestado
    Date fechaDelPrestamo; // Fecha en la que se hizo el préstamo

    // Constructor de la clase Prestamo
    public Prestamo(Libro book) {
        this.libro = book;
        this.fechaDelPrestamo = new Date();
    }

    // Metodo para mostrar la información del préstamo en formato String
    public String toString() {
        return "Libro: " + libro.titulo + ", Fecha del préstamo: " + fechaDelPrestamo;
    }
}

// Clase que representa la biblioteca y sus funcionalidades para Trabajador
class Libreria {
    private List<Libro> libros;  // Lista de libros disponibles en la biblioteca
    private List<Prestamo> historialPrestamos; // Historial de préstamos realizados

    // Constructor de la clase Libreria
    public Libreria() {
        libros = new ArrayList<>(); // Inicializa la lista de libros
        historialPrestamos = new ArrayList<>(); // Inicializa el historial de préstamos
        añadirLibros(); // Añade libros de ejemplo a la biblioteca
    }

    // Metodo privado que añade libros de ejemplo a la biblioteca
    private void añadirLibros() {
        libros.add(new Libro("Titulo1", "autor3", "gen"));
        libros.add(new Libro("Titulo2", "autor2", "gen"));
        libros.add(new Libro("Titulo3", "autor", "gen"));
    }

    // Metodo para mostrar el stock de libros en la biblioteca
    public void verStock() {
        System.out.println("Libros disponibles:");
        for (Libro libro : libros) {
            System.out.println(libro); // Muestra cada libro y su disponibilidad
        }
    }

    // Metodo para solicitar un préstamo de un libro por su título
    public void pedirPrestamo(String titulo) {
        for (Libro libro : libros) {
            // Verifica si el título coincide y el libro está disponible
            if (libro.titulo.equalsIgnoreCase(titulo) && libro.disponible) {
                libro.disponible = false; // Marca el libro como no disponible
                historialPrestamos.add(new Prestamo(libro)); // Añade el préstamo al historial
                System.out.println("Usted ha tomado prestado con éxito: " + libro.titulo);
                return;
            }
        }
        System.out.println("Libro no disponible para préstamo.");
    }

    // Metodo para devolver un libro por su título
    public void devolverLibro(String titulo) {
        for (Prestamo historial : historialPrestamos) {
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
    public void verHistorialPrestamos() {
        System.out.println("Historial de préstamos:");
        for (Prestamo prestamo : historialPrestamos) {
            System.out.println(prestamo);
        }
    }
}

// Clase que representa la biblioteca y sus funcionalidades para Usuario
class BibliotecaApp {
    static ArrayList<Libro> libros = new ArrayList<>();

    static void agregarLibro(String titulo, String autor, String genero) {
        libros.add(new Libro(titulo, autor, genero));
        System.out.println("Libro agregado: " + titulo);
    }

    static void eliminarLibro(String tituloLibro) {
        Libro libro = buscarLibro(tituloLibro);
        if (libro != null) {
            libros.remove(libro);
            System.out.println("Libro eliminado: " + tituloLibro);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    static void verStock() {
        System.out.println("Stock de libros:");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    static Libro buscarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.titulo.equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // Agregar algunos libros iniciales para pruebas
        agregarLibro("Titulo1", "autor3", "gen");
        agregarLibro("Titulo2", "autor2", "gen");
        agregarLibro("Titulo3", "autor", "gen");

        // Menú de selección de rol
        System.out.println("\n--- Aquí puedes seleccionar tu rol ---");
        System.out.println("1. Usuario");
        System.out.println("2. Trabajador");
        System.out.println("Seleccione su rol:");
        int rol = scanner.nextInt();
        scanner.nextLine();

        if (rol == 1) { // Usuario
            while (!salir) {
                System.out.println("\n--- Biblioteca (Usuario) ---");
                System.out.println("1. Ver stock de libros");
                System.out.println("2. Agregar libro al stock");
                System.out.println("3. Eliminar libro del stock");
                System.out.println("4. Otras funciones (en desarrollo)");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        verStock();
                        break;
                    case 2:
                        System.out.print("Título del libro: ");
                        String tituloAgregar = scanner.nextLine();
                        System.out.print("Autor del libro: ");
                        String autorAgregar = scanner.nextLine();
                        System.out.print("Género del libro: ");
                        String generoAgregar = scanner.nextLine();
                        agregarLibro(tituloAgregar, autorAgregar, generoAgregar);
                        break;
                    case 3:
                        System.out.print("Título del libro a eliminar: ");
                        String tituloEliminar = scanner.nextLine();
                        eliminarLibro(tituloEliminar);
                        break;
                    case 4:
                        System.out.println("Esta opción está en desarrollo.");
                        break;
                    case 5:
                        salir = true;
                        System.out.println("Saliendo de la biblioteca...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } else if (rol == 2) { // Trabajador
            Libreria libreria = new Libreria();
            salir = false;

            while (!salir) {
                System.out.println("\n--- Biblioteca (Trabajador) ---");
                System.out.println("1. Ver stock de libros");
                System.out.println("2. Solicitar un préstamo");
                System.out.println("3. Devolver un libro");
                System.out.println("4. Ver historial de préstamos");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        libreria.verStock();
                        break;
                    case 2:
                        System.out.print("Introduzca el título del libro que desea pedir prestado: ");
                        String loanTitle = scanner.nextLine();
                        libreria.pedirPrestamo(loanTitle);
                        break;
                    case 3:
                        System.out.print("Introduzca el título del libro que desea devolver: ");
                        String tituloDevolucion = scanner.nextLine();
                        libreria.devolverLibro(tituloDevolucion);
                        break;
                    case 4:
                        libreria.verHistorialPrestamos();
                        break;
                    case 5:
                        salir = true;
                        System.out.println("Saliendo de la biblioteca...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } else {
            System.out.println("Rol no válido.");
        }
    }
}
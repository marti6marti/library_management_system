import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {
    static ArrayList<Libro> libros = new ArrayList<>();

    static class Libro {
        // Estoy creando las variables necesarias
        String titulo;
        String autor;
        String genero;
        boolean disponible = true;
        
        /*Aqui estoy creando una clase que define como tiene que ser la estructura
         y ademas define como tiene que ser el order, se llama constructor
         this lo que hace es que dice que estamos difiniendo el titulo o autor o genero
         a la varibles que tenemos arriba, lo explicare mejor en la classe, pero eso sirve para simplificar
         y usar el mismo nombre de variable.*/
        
        Libro(String titulo, String autor, String genero) {
            this.titulo = titulo;
            this.autor = autor;
            this.genero = genero;
        }
        
        //Aqui definimos de como tiene que ser el String depues de llamar la funcion al final
        public String toString() {
            return titulo + " - " + autor + " [" + genero + "] - " + (disponible ? "Disponible" : "No disponible");
        }
    }
    
    // Creo una funcion que agrega libros, y el void lo que hace es que no devuelve ningun valor solamente lo hace
    // cuando se le llama
    
    //Ademas al llamar libros "una lista(array)" y escribir add (new Libro-.....) agregamos a la lista nuevo libro
    static void agregarLibro(String titulo, String autor, String genero) {
        libros.add(new Libro(titulo, autor, genero));
        System.out.println("Libro agregado: " + titulo);
    }
    
    //hago algo parecido que antes, pero si os fijais primero llamo la funcion que tengo un poco mas despues,
    // lo que hace la funcion es buscar un libro por su titulo, despues devuelve y elimina el libro si titulo coincide
    static void eliminarLibro(String tituloLibro) {
        Libro libro = buscarLibro(tituloLibro);
        if (libro != null) {
            libros.remove(libro);
            System.out.println("Libro eliminado: " + tituloLibro);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
    
    // Para ver el stok lo unico que se necesita es for y dentro el nombre de varibale : y el nombre de la lista
    static void verStock() {
        System.out.println("Stock de libros:");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    // Ahora lo que dije antes, vemos si hay algun titulo que coincida con el nombre, y si es asi, devuelve las
    // varibales que tiene el Libro ese.
    static Libro buscarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.titulo.equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    // nada mas que agregar, se crea el scaner pq lo que habia antes eran las funciones y ahora creo la
    // varibale de salir y luego llamo la funcion de agregar los libros.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // Agregar algunos libros iniciales para pruebas
        agregarLibro("Titulo1", "autor3", "gen");
        agregarLibro("Titulo2", "autor2", "gen");
        agregarLibro("Titulo3", "autor", "gen");

        // Menú de opciones
        while (!salir) {
            System.out.println("\n--- Biblioteca ---");
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
    }
}

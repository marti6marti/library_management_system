


//Código para gestionar los libros: 

public class Libro {
	private String titulo;
	private String autor;
	private String isbn;
	private boolean prestado;

	public Libro(String titulo, String autor, String isbn) {
    	this.titulo = titulo;
    	this.autor = autor;
    	this.isbn = isbn;
    	this.prestado = false;
	}

	// Getters and Setters
	public String getTitulo() {
    	return titulo;
	}

	public String getAutor() {
    	return autor;
	}

	public String getIsbn() {
    	return isbn;
	}

	public boolean isPrestado() {
    	return prestado;
	}

	public void setPrestado(boolean prestado) {
    	this.prestado = prestado;
	}
}


//Código para gestionar los usuarios:

public class Usuario {
	private String nombre;
	private String dni;

	public Usuario(String nombre, String dni) {
    	this.nombre = nombre;
    	this.dni = dni;
	}

	// Getters and Setters
	public String getNombre() {
    	return nombre;
	}

	public String getDni() {
    	return dni;
	}
}

//Para gestionar los préstamos:

import java.util.Date;

public class Prestamo {
	private Libro libro;
	private Usuario usuario;
	private Date fechaPrestamo;
	private Date fechaDevolucion;

	public Prestamo(Libro libro, Usuario usuario, Date fechaPrestamo, Date fechaDevolucion) {
    	this.libro = libro;
    	this.usuario = usuario;
    	this.fechaPrestamo = fechaPrestamo;
    	this.fechaDevolucion = fechaDevolucion;
	}

	// Getters and Setters
	public Libro getLibro() {
    	return libro;
	}

	public Usuario getUsuario() {
    	return usuario;
	}

	public Date getFechaPrestamo() {
    	return fechaPrestamo;
	}

	public Date getFechaDevolucion() {
    	return fechaDevolucion;
	}
}

//Para gestionar la biblioteca:

import java.util.*;

public class Biblioteca {
	private List<Libro> libros;
	private List<Usuario> usuarios;
	private List<Prestamo> prestamos;

	public Biblioteca() {
    	this.libros = new ArrayList<>();
    	this.usuarios = new ArrayList<>();
    	this.prestamos = new ArrayList<>();
	}

	// Métodos para agregar libros, usuarios, y préstamos
	public void agregarLibro(Libro libro) {
    	libros.add(libro);
	}

	public void agregarUsuario(Usuario usuario) {
    	usuarios.add(usuario);
	}

	public void registrarPrestamo(Libro libro, Usuario usuario) {
    	if (!libro.isPrestado()) {
        	libro.setPrestado(true);
        	Prestamo prestamo = new Prestamo(libro, usuario, new Date(), null);
        	prestamos.add(prestamo);
    	} else {
        	System.out.println("El libro ya está prestado.");
    	}
	}

	public void devolverLibro(Libro libro) {
    	for (Prestamo prestamo : prestamos) {
        	if (prestamo.getLibro().equals(libro) && prestamo.getFechaDevolucion() == null) {
            	prestamo.setFechaDevolucion(new Date());
            	libro.setPrestado(false);
            	break;
        	}
    	}
	}

	public void mostrarLibrosPrestados() {
    	for (Prestamo prestamo : prestamos) {
        	if (prestamo.getFechaDevolucion() == null) {
            	System.out.println("Libro: " + prestamo.getLibro().getTitulo() + " - Usuario: " + prestamo.getUsuario().getNombre());
        	}
    	}
	}

	public void historialPrestamos() {
    	for (Prestamo prestamo : prestamos) {
        	System.out.println("Libro: " + prestamo.getLibro().getTitulo() + " - Usuario: " + prestamo.getUsuario().getNombre() + " - Fecha Préstamo: " + prestamo.getFechaPrestamo() + " - Fecha Devolución: " + prestamo.getFechaDevolucion());
    	}
	}

	// Generación de informes estadísticos
	public void generarInformeEstadistico() {
    	Map<String, Integer> conteoLibros = new HashMap<>();

    	for (Prestamo prestamo : prestamos) {
        	String titulo = prestamo.getLibro().getTitulo();
        	conteoLibros.put(titulo, conteoLibros.getOrDefault(titulo, 0) + 1);
    	}

    	System.out.println("Informe Estadístico: Libros más prestados");
    	for (Map.Entry<String, Integer> entry : conteoLibros.entrySet()) {
        	System.out.println("Libro: " + entry.getKey() + " - Veces Prestado: " + entry.getValue());
    	}
	}

	public static void main(String[] args) {
    	Biblioteca biblioteca = new Biblioteca();

    	// Crear algunos libros y usuarios
    	Libro libro1 = new Libro("El Quijote", "Miguel de Cervantes", "123456789");
    	Libro libro2 = new Libro("Cien Años de Soledad", "Gabriel García Márquez", "987654321");
    	Usuario usuario1 = new Usuario("Juan Pérez", "12345678A");
    	Usuario usuario2 = new Usuario("María García", "87654321B");

    	// Agregar libros y usuarios a la biblioteca
    	biblioteca.agregarLibro(libro1);
    	biblioteca.agregarLibro(libro2);
    	biblioteca.agregarUsuario(usuario1);
    	biblioteca.agregarUsuario(usuario2);

    	// Registrar algunos préstamos
    	biblioteca.registrarPrestamo(libro1, usuario1);
    	biblioteca.registrarPrestamo(libro2, usuario2);

    	// Mostrar libros prestados actualmente
    	biblioteca.mostrarLibrosPrestados();

    	// Mostrar historial de préstamos
    	biblioteca.historialPrestamos();

    	// Generar informe estadístico
    	biblioteca.generarInformeEstadistico();
	}
}


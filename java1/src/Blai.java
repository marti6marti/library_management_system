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

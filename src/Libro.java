// Learn more: https://www.baeldung.com/java-8-date-time-intro
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Libro {
	
	
	private String titulo;
	private String editorial;
	private LocalDate publicacion;
	private int paginas;
	
	public Libro(String titulo, String editorial, String publicacion)
	{
		this.titulo = titulo;
		this.editorial = editorial;
		this.publicacion = LocalDate.parse(publicacion);
		this.paginas = 0;
	}
	
	public Libro(String titulo, String editorial, String publicacion, int paginas)
	{
		this.titulo = titulo;
		this.editorial = editorial;
		this.publicacion = LocalDate.parse(publicacion);
		this.paginas = paginas;
	}	

	public String getEditorial()
	{
		return this.editorial;
	}
	
	public String getTitulo()
	{
		return this.titulo;
	}
	
	public LocalDate getPublicacion()
	{
		return this.publicacion;
	}

	public int getPaginas()
	{
		return this.paginas;
	}

	public void setEditorial(String value)
	{
		this.editorial = value;
	}
	
	public void setTitulo(String value)
	{
		this.titulo = value;
	}
	
	public void setPublicacion(String value)
	{
		this.publicacion = LocalDate.parse(value);
	}
	
	public void setPaginas(int value)
	{
		this.paginas = value;
	}

	public String toString() 
	{
		return 
			" Libro ("
				+ "\n\tTitulo: " + this.getTitulo() 
				+ "\n\tEditorial: " + this.getEditorial() 
				+ "\n\tFecha de publicacion: " + this.getPublicacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				+ "\n\tNº de páginas: " + (this.getPaginas() == 0? "N/D": this.getPaginas())  
			+ "\n )";
	}
	
	public static List<Object> loadDefaultObjects()
	{
		List<Object> libros = new ArrayList<Object>();
		
		libros.add(new Libro("La historia interminable", "ALFAGUARA", "1979-09-01"));
		libros.add(new Libro("Trampa 22", "Literatura Random House", "1979-09-01")); // Joseph Heller
		libros.add(new Libro("De corazones y cerebros", "Baile del Sol", "1979-09-01", 644)); // César Martín Ortiz
		libros.add(new Libro("Monjas y Soldados", "Impedimenta", "1979-09-01", 600)); // Iris Murdoch
		libros.add(new Libro("Memorias de un antihéroe", "Las afueras", "1979-09-01", 128)); // Kornel Filipowicz 
		libros.add(new Libro("La costa de Chicago", "Pálido Fuego", "1979-09-01", 189)); // Stuart Dybek
		libros.add(new Libro("Los errantes", "Anagrama", "1979-09-01",400)); // Olga Tokarczuk
		libros.add(new Libro("Un apartamento en Urano. Crónicas del cruce", "Anagrama", "1979-09-01", 320)); // Paul B. Preciado 
		libros.add(new Libro("Lagunas", "Pepitas de Calabaza", "1979-09-01", 256)); // Sarah Hepola
		libros.add(new Libro("Milkman", "Alianza de Novelas", "1979-09-01", 352)); // Anna Burns 
		libros.add(new Libro("El quijote", "Juan de la Cuesta", "1605-01-01")); // Miguel de Cervantes
		
//		Libro[] libros = {
//				new Libro("La historia interminable", "ALFAGUARA", "1979-09-01"),
//				new Libro("Trampa 22", "Literatura Random House", "1979-09-01"), // Joseph Heller
//				new Libro("De corazones y cerebros", "Baile del Sol", "1979-09-01", 644), // César Martín Ortiz
//				new Libro("Monjas y Soldados", "Impedimenta", "1979-09-01", 600), // Iris Murdoch
//				new Libro("Memorias de un antihéroe", "Las afueras", "1979-09-01", 128), // Kornel Filipowicz 
//				new Libro("La costa de Chicago", "Pálido Fuego", "1979-09-01", 189), // Stuart Dybek
//				new Libro("Los errantes", "Anagrama", "1979-09-01",400), // Olga Tokarczuk
//				new Libro("Un apartamento en Urano. Crónicas del cruce", "Anagrama", "1979-09-01", 320), // Paul B. Preciado 
//				new Libro("Lagunas", "Pepitas de Calabaza", "1979-09-01, 256"), // Sarah Hepola
//				new Libro("Milkman", "Alianza de Novelas", "1979-09-01", 352), // Anna Burns 
//				new Libro("El quijote", "Juan de la Cuesta", "1605-01-01"), // Miguel de Cervantes
//		};
//		
		return libros;
	}
}

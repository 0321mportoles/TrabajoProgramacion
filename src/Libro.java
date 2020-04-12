// Learn more: https://www.baeldung.com/java-8-date-time-intro
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Libro {
	private String titulo;
	private String editorial;
	private LocalDate publicacion;
	
	public Libro(String titulo, String editorial, String publicacion)
	{
		this.titulo = titulo;
		this.editorial = editorial;
		this.publicacion = LocalDate.parse(publicacion);
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
	
	public String toString() {
		return 
			" Libro ("
				+ "\n\tTitulo: " + this.getTitulo() 
				+ "\n\tEditorial: " + this.getEditorial() 
				+ "\n\tFecha de publicacion: " + this.getPublicacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) 
			+ "\n )";
	}
}

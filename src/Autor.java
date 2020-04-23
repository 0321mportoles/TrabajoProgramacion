import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Autor {		
	
	// atributos 
	private String nombreCompleto;
	private LocalDate fechaNacimiento; 
	private String pais;
		
	//constructor
	public Autor(String nombreCompleto, String fechaNacimiento, String pais) {
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
		this.pais = pais;
	}
	//constructor con nombre 
	public Autor(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	// getters y setters
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String toString() 
	{
		return this.getNombreCompleto() + " - " + this.getPais();
	}
	
	public static List<Object> loadDefaultObjects()
	{
		List<Object> autores = new ArrayList<Object>();
		
		autores.add(new Autor("Miguel de Cervantes"));
		autores.add(new Autor("Anna Burns"));

		return autores;
	}	
}



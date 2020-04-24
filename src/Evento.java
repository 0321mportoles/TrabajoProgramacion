import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Evento {
	// atributos
	private String nombre;
	private String lugar;
	private LocalDate fecha;
	
	
	//constructor
	public Evento(String nombre, String lugar, String fecha) {
		this.nombre = nombre;
		this.lugar = lugar;
		this.fecha = LocalDate.parse(fecha);
	}
	
	//getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = LocalDate.parse(fecha);
	}
	
	public String toString() 
	{
		return this.getNombre() + " en " + this.getLugar()+ " el " + this.getFecha();
	}
	
	public static List<Object> loadDefaultObjects()
	{
		List<Object> eventos = new ArrayList<Object>();
		
		eventos.add(new Evento("Firma del libro: Trampa 22", "Biblioteca publica municipal Zaragoza" , "2020-06-15"));
		eventos.add(new Evento("Presentacion libro:  El dragón bailongo", "Hotel Gran Via, Zaragoza", "2020-06-20"));

		return eventos;
	}
	
	
	
	
}

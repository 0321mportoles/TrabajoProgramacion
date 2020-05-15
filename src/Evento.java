import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Evento extends Object {
	
	// Constantes
	public static final Integer LONGITUD_MAX_CADENA = 30;
	public static final Integer LONGITUD_MAX_FECHA = 10;
	public static final int LONGITUD_MAX_OBJ_BYTES = 200;
	
	// atributos
	private String nombre;
	private String lugar;
	private LocalDate fecha;
	private Libro libro;
	
	
	//constructor
	public Evento(String nombre, String lugar, String fecha, Libro libro) {
		this.nombre = nombre;
		this.lugar = lugar;
		this.fecha = LocalDate.parse(fecha);
		this.libro = libro;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public void setFecha(String fecha) {
		this.fecha = LocalDate.parse(fecha);
	}
	
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public String toString() 
	{
		return this.getNombre() + " en " + this.getLugar()+ " el " + this.getFecha();
	}

	public static List<Object> buscarEventoPorNombre(List<Object> lista, String buscando) {
		List<Object> autores = new ArrayList<Object>();
		Object o;
		System.out.println("Buscando eventos por nombre que contenga: '" + buscando + "'");
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Evento) o).getNombre().toLowerCase().contains(buscando.toLowerCase())) {
				autores.add(o);
			}
		}
		
		return autores;
	}

	public static List<Object> buscarEventoPorLugar(List<Object> lista, String buscando) {
		List<Object> eventos = new ArrayList<Object>();
		Object o;
		System.out.println("Buscando eventos por lugar que contenga: '" + buscando + "'");
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Evento) o).getLugar().toLowerCase().contains(buscando.toLowerCase())) {
				eventos.add(o);
			}
		}
		
		return eventos;
	}

	public static List<Object> buscarEventoPorFecha(List<Object> lista, String buscando) {
		List<Object> eventos = new ArrayList<Object>();
		Object o;
		System.out.println("Buscando eventos por fecha '" + buscando + "'");
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Evento) o).getFecha().isAfter(LocalDate.parse(buscando))) {
				eventos.add(o);
			}
		}
		return eventos;
	}

	public static List<Object> buscarEventosPorLibro(List<Object> lista, String buscando) {
		List<Object> eventos = new ArrayList<Object>();
		Object o;
		System.out.println("Buscando eventos por libros '" + buscando + "'");
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Evento) o).getLibro().getTitulo().toLowerCase().contains(buscando.toLowerCase())) {
				eventos.add(o);
			}
		}
		return eventos;
	}
	
	public String escribirAFicheroDeTexto() {
		return this.getNombre() + "," + this.getLugar() + "," + this.getFecha() + "," + this.getLibro().getTitulo();
	}

	public static Evento leerDesdeLineaDeTexto(String linea, List<Object> libros) throws Exception {
		String nombre = "", lugar = "", fecha= "";
		Libro libro;
		List<Object> listadoLibros = null;
		
		int  pInicio = 0, pFinal = 0;
		int i = 0;
		
		pFinal = linea.indexOf(',');
		
		if (pFinal != -1) {
			while (pFinal != -1) {
				String palabra = linea.substring(pInicio, pFinal).trim();
				pInicio = pFinal + 1;
				pFinal = linea.indexOf(',', pInicio);
				
				if (i == 0) {
					nombre = palabra;
				} else if (i == 1) {
					lugar = palabra;
				} else if (i == 2) {
					fecha = palabra;
				}
				
				i++;
			}
			
			String palabra = linea.substring(pInicio).trim();
			
			listadoLibros = Libro.buscarLibrosPorTitulo(libros, palabra);
			
			if (listadoLibros.size() != 1) {
				if (listadoLibros.size() == 0) {
					throw new Exception("El libro " + palabra + " no ha sido creado previamente");
				} else {
					throw new Exception("Hay " + listadoLibros.size() + " libros que coinciden con la busqueda '" + palabra + "'");
				}
			}
		}
		
		if (i != 3) {
			throw new Exception("La linea de fichero no está bien formada");
		}
		
		return new Evento(nombre, lugar, fecha, (Libro) listadoLibros.get(0));

	}

		
}

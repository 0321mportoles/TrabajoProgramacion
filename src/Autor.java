import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Autor extends Object {		
	
	// Constantes
	public static final Integer LONGITUD_MAX_CADENA = 30;
	public static final Integer LONGITUD_MAX_FECHA = 10;
	public static final int LONGITUD_MAX_OBJ_BYTES = 140;
	
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
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String toString() 
	{
		String fecha = (this.getFechaNacimiento() == null)? "": " - " + this.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return this.getNombreCompleto() + fecha +  " - " + this.getPais();
	}
	
	public static List<Object> loadDefaultObjects()
	{
		List<Object> autores = new ArrayList<Object>();
		
		autores.add(new Autor("Miguel de Cervantes"));
		autores.add(new Autor("Anna Burns"));

		return autores;
	}
	public static List<Object> buscarAutoresPorNombre(List<Object> lista, String buscando) {
		List<Object> autores = new ArrayList<Object>();
		Object o;
		System.out.println("Buscando autor por nombre que contenga: '" + buscando + "'");
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Autor) o).getNombreCompleto().toLowerCase().contains(buscando.toLowerCase())) {
				autores.add(o);
			}
		}

		return autores;
	}
	
	public static List<Object> buscarAutorPorPais(List<Object> lista, String buscando) {
		List<Object> autores = new ArrayList<Object>();
		Object o;
		System.out.println("Buscando autor por pais que contenga: '" + buscando + "'");
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Autor) o).getPais().toLowerCase().contains(buscando.toLowerCase())) {
				autores.add(o);
			}
		}
		
		return autores;
	}
	
	public static List<Object> buscarAutorPorFecha(List<Object> lista, String buscando) {
		List<Object> autores = new ArrayList<Object>();
		Object o;
		System.out.println("Buscando autor por fecha de nacimiento '" + buscando + "'");
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Autor) o).getFechaNacimiento().isAfter(LocalDate.parse(buscando))) {
				autores.add(o);
			}
		}
		return autores;
	}
	
	public String escribirAFicheroDeTexto() {
		return this.getNombreCompleto() + "," + this.getFechaNacimiento() + "," + this.getPais();
	}
	
	public static Autor leerDesdeLineaDeTexto(String linea) throws Exception {
		String nombre = "", fecha = "", pais = "";

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
					fecha = palabra;
				}
				
				i++;
			}
			
			String palabra = linea.substring(pInicio).trim();
			pais = palabra;
		}
		
		if (i != 2) {
			throw new Exception("La linea de fichero no está bien formada");
		}
		
		// procesar linea y asociar a nombre, pais y fecha los datos leidos de la linea
		return new Autor(nombre, fecha, pais);
	}

}



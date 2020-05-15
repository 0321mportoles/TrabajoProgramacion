// Learn more: https://www.baeldung.com/java-8-date-time-intro
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Libro extends Object {
	
	// Constantes
	public static final Integer LONGITUD_MAX_CADENA = 30;
	public static final Integer LONGITUD_MAX_FECHA = 10;
	public static final int LONGITUD_MAX_OBJ_BYTES = 204;
	
	
	// atributos 
	private String titulo;
	private String editorial;
	private LocalDate publicacion;
	private int paginas;
	private Autor autor;
	
	// constructor sin paginas
	public Libro(String titulo, String editorial, String publicacion, Autor autor)
	{
		this.titulo = titulo;
		this.editorial = editorial;
		this.publicacion = LocalDate.parse(publicacion);
		this.paginas = 0;
		this.autor = autor;
	}
	
	// constructor completo
	public Libro(String titulo, String editorial, String publicacion, int paginas, Autor autor)
	{
		this.titulo = titulo;
		this.editorial = editorial;
		this.publicacion = LocalDate.parse(publicacion);
		this.paginas = paginas;
		this.autor = autor;
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

	public Autor getAutor() {
		return autor;
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

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String toString() 
	{
		return 
			" Libro ("
				+ "\n\tTitulo: " + this.getTitulo() 
				+ "\n\tEditorial: " + this.getEditorial() 
				+ "\n\tFecha de publicacion: " + this.getPublicacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				+ "\n\tNº de páginas: " + (this.getPaginas() == 0? "N/D": this.getPaginas())
				+ "\n\tAutor: " + this.getAutor().getNombreCompleto()
			+ "\n )";
	}
	
	public static List<Object> buscarLibrosPorTitulo(List<Object> lista, String buscando) {
		List<Object> libros = new ArrayList<Object>();
		Object o;
		
		System.out.println("Buscando libro por titulo que contenga: '" + buscando + "'");
		
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Libro) o).getTitulo().toLowerCase().contains(buscando.toLowerCase())) {
				libros.add(o);
			}
		}
		
		return libros;
	}
	
	public static List<Object> buscarLibrosPorEditorial(List<Object> lista, String buscando) {
		List<Object> libros = new ArrayList<Object>();
		Object o;
		System.out.println("Buscando libro por editorial que contenga: '" + buscando + "'");
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Libro) o).getEditorial().toLowerCase().contains(buscando.toLowerCase())) {
				libros.add(o);
			} 
		}
		
		return libros;
	}
	
	public static List<Object> buscarLibrosPorFechaPublicacion(List<Object> lista, String buscando) {
		List<Object> libros = new ArrayList<Object>();
		Object o;
		System.out.println("Buscando libro por fecha de publicacion '" + buscando + "'");
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Libro) o).getPublicacion().isAfter(LocalDate.parse(buscando))) {
				libros.add(o);
			}
		}
		
		return libros;
	}

	public static List<Object> buscarLibrosPorAutor(List<Object> lista, String buscando) {
		List<Object> libros = new ArrayList<Object>();
		Object o;
		System.out.println("Buscando libro por autor que contenga: '" + buscando + "'");
		for (Iterator<Object> i = lista.iterator(); i.hasNext();) {
			o = (Object) i.next();
			if (((Libro) o).getAutor().getNombreCompleto().toLowerCase().contains(buscando.toLowerCase())) {
				libros.add(o);
			} 
		}
		
		return libros;
	}
	
	public String escribirAFicheroDeTexto() {
		return 
				this.getTitulo() + "," 
				+ this.getEditorial() + "," 
				+ this.getPublicacion() + "," 
				+ this.getPaginas() + "," 
				+ this.getAutor().getNombreCompleto();	
	}

	public static Libro leerDesdeLineaDeTexto(String linea, List<Object> autores) throws Exception {
		String titulo = "", editorial = "", publicacion = "";
		int paginas = 0;
		List<Object> listadoAutores = null;
		
		int  pInicio = 0, pFinal = 0;
		int i = 0;
		
		pFinal = linea.indexOf(',');
		
		if (pFinal != -1) {
			while (pFinal != -1) {
				String palabra = linea.substring(pInicio, pFinal).trim();
				pInicio = pFinal + 1;
				pFinal = linea.indexOf(',', pInicio);
				
				if (i == 0) {
					titulo = palabra;
				} else if (i == 1) {
					editorial = palabra;
				} else if (i == 2) {
					publicacion = palabra;
				} else if (i == 3) {
					paginas = Integer.parseInt(palabra);
				}
				i++;
			}
			
			String palabra = linea.substring(pInicio).trim();
			
			listadoAutores = Autor.buscarAutoresPorNombre(autores, palabra);
			
			if (listadoAutores.size() != 1) {
				if (listadoAutores.size() == 0) {
					throw new Exception("El autor " + palabra + " no ha sido creado previamente");
				} else {
					throw new Exception("Hay " + listadoAutores.size() + " autores que coinciden con la busqueda '" + palabra + "'");
				}
			}
		}
		
		if (i != 4) {
			throw new Exception("La linea de fichero no está bien formada");
		}
		
		return new Libro(titulo, editorial, publicacion, paginas, (Autor) listadoAutores.get(0));
	}

}

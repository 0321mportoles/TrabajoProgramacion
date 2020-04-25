import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author MARTA
 *
 */
public class Inicio {

	public static Scanner reader = new Scanner(System.in);
	public static List<Object> libros = Libro.loadDefaultObjects();
	public static List<Object> autores = Autor.loadDefaultObjects();
	public static List<Object> eventos = Evento.loadDefaultObjects();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int opcion;
		
		// Create new object example
		// Libro l = new Libro("La historia interminable", "ALFAGUARA", "1979-09-01");
		// System.out.println(l);
		System.out.println();
		
		do {
			opcion = mostrarMenuPrincipal();
			ejecutarOpciones(opcion);
		} while (opcion != 0);
		
		System.out.println();
		System.out.println("Programa finalizado");
		reader.close();
	}

	private static int mostrarMenuPrincipal() {
		System.out.println();
		System.out.println("=======================================================================================");
		System.out.println("                          TRABAJO PROGRAMACIÓN CURSO 2019-2020                         ");
		System.out.println("=======================================================================================");
		System.out.println("1 .- Insertar elementos");
		System.out.println("2 .- Realizar búsqueda");
		System.out.println("3 .- Modificar elemento");
		System.out.println("4 .- Borrar elemento");
		System.out.println("5 .- Visualizar elementos");
		System.out.println("6 .- Importación desde'Fichero de texto'");
		System.out.println("7 .- Exportación a 'Fichero de texto'");
		System.out.println("8 .- Importación a archivo en formato binario");
		System.out.println("9 .- Exportación a archivo en formato binario");
		System.out.println("10 .- Importación utilizando acceso aleatorio");
		System.out.println("11 .- Exportación utilizando acceso aleatorio");
		System.out.println("0 .- Salir del programa");
		System.out.println("=======================================================================================");
		System.out.print("Elija una opción de las anteriores (0 para salir): ");

		return Integer.parseInt(reader.nextLine());
	}
	
	private static int menuElementos() {
		System.out.println();
		System.out.println("1 .- Libro");
		System.out.println("2 .- Autor");
		System.out.println("3 .- Evento de un libro");
		System.out.println("0 .- Volver atrás");
		
		System.out.print(" - Elige una opción de untre las disponibles (0-3): ");
		
		return Integer.parseInt(reader.nextLine());
	}

	private static void ejecutarOpciones(int opcion) {
		System.out.println();
		switch (opcion) {
			case 1:
				insertarElementos();
				break;
			case 2:
				buscarElementos();
				
				break;
			case 3:
				modificarElementos();
				break;
			case 4:
				eliminarElementos();
				break;
			case 5:
				visualizarElementos();
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			case 8:
				
				break;
			case 9:

				break;
			case 10:
				
				break;
			case 11:
				
				break;
		}
	}

	private static void buscarElementos() {
		int op;
		
		System.out.println("¿Que tipo de elemento quieres buscar?");
		System.out.println("===========================================================================");
		
		op = menuElementos();
		buscarElemento(op);
		
	}

	private static void buscarElemento(int op) {
		switch (op) {
			// Busqueda de libros
			case 1:
				buscarLibros();
				break;
			// Busqueda de autores
			case 2:
				Autor autor;
				//autor = buscarAutores();
			
				break;
			// Busqueda de eventos
			case 3:
				Evento evento;
				//evento = buscarEventos();
				
				break;
	
			default:
				break;
		}
		
		
	}
	
	private static void buscarLibros() {
		int op;
		List<Object> librosBuscados;
		
		op= menuCriterioDeBusqueda();
		switch (op) {
			// Buscando por titulo
			case 1:
				librosBuscados = Libro.buscarLibrosPorTitulo(libros, pedirString("Titulo a buscar"));
				imprimirObjectos(librosBuscados);
				break;
			// Buscando por autor
			case 2:
			//	librosBuscados = Libro.buscarLibrosPorAutor(libros, pedirString("Autor a buscar"));
			//	imprimirObjectos(librosBuscados);
				break;
			// Buscando por fecha de publicacion
			case 3:
				boolean error = true;
				while (error) {
					try {
						String fecha = pedirString("Fecha de publicacion a buscar (YYYY-mm-dd)");
						LocalDate.parse(fecha);
						librosBuscados = Libro.buscarLibrosPorFechaPublicacion(libros, fecha);
						imprimirObjectos(librosBuscados);
						error = false;
					} catch (DateTimeParseException e) {
						System.out.println("******** ERROR. La fecha debe seguir el formato especificado **********");
					}
				}
				
				break;
			// Buscando por editorial
			case 4:
				librosBuscados = Libro.buscarLibrosPorEditorial(libros, pedirString("Editorial a buscar"));
				imprimirObjectos(librosBuscados);	
				break;
			
		}
	}

	private static int menuCriterioDeBusqueda() {
		System.out.println("¿Qué criterio de busqueda quieres?");
		System.out.println("1 .- Por titulo");
		System.out.println("2 .- Por autor");
		System.out.println("3 .- Por fecha de publicacion");
		System.out.println("4 .- Por editorial");
		System.out.println("0 .- Volver atrás");
		
		System.out.print(" - Elige una opción de untre las disponibles (0-4): ");
		
		return Integer.parseInt(reader.nextLine());
	}

	private static void insertarElementos() {
		int op;
		
		System.out.println("¿Que elemento quieres insertar?");
		System.out.println("===========================================================================");
		
		op = menuElementos();
		insertarElemento(op);
	}
	
	private static void insertarElemento(int op) {
		switch (op) {
		case 1:
			Libro libro;
			libro = pedirDatosNuevoLibro();
			libros.add(libro);
			break;
		case 2:
			Autor autor;
			autor = pedirDatosNuevoAutor();
			autores.add(autores);
			break;
		case 3:
			Evento evento;
			evento = pedirDatosNuevoEvento();
			eventos.add(evento);
			break;

		default:
			break;
		}
		
	}

	private static Evento pedirDatosNuevoEvento() {
		System.out.println("Insertar nuevo Evento");
		System.out.println("===========================================================================");
		
		String nombre = pedirString("Nombre");
		String lugar = pedirString("Lugar");
		String fecha = pedirFecha("Fecha (YYYY-mm-dd)");
		
		return new Evento(nombre, lugar, fecha);
	}

	private static Autor pedirDatosNuevoAutor() {
		System.out.println("Insertar nuevo autor");
		System.out.println("===========================================================================");
		
		String nombreCompleto = pedirString("Nombre completo");
		String fechaNacimiento = pedirFecha("Fecha de nacimiento (YYY-mm-dd)");
		String pais = pedirString("Pais");
		
		return new Autor(nombreCompleto, fechaNacimiento, pais);
	}

	private static Libro pedirDatosNuevoLibro() {
		System.out.println("Insertar nuevo libro");
		System.out.println("===========================================================================");
		
		String titulo = pedirString("Titulo");
		String editorial = pedirString("Editorial");
		String fecha = pedirFecha("Fecha de publicación (YYYY-mm-dd)");
		
		return new Libro(titulo, editorial, fecha);
	}

	private static void modificarDatosLibro(Libro libroAModificar) {
		String titulo = pedirStringPermitirVacio("Titulo (enter si no quieres modificar)");
		String editorial = pedirStringPermitirVacio("Editorial (enter si no quieres modificar)");
		String fecha = pedirStringPermitirVacio("Fecha de publicación (YYYY-mm-dd) (enter si no quieres modificar)");
		
		if ((titulo != null) && (!titulo.equals(""))) {
			libroAModificar.setTitulo(titulo);
		}
		
		if ((editorial != null) && (!editorial.equals(""))) {
			libroAModificar.setEditorial(editorial);
		}
		
		if ((fecha != null) && (!fecha.equals(""))) {
			libroAModificar.setPublicacion(fecha);
		}
	}
	
	private static String pedirFecha(String string) {
		boolean fechaOk = false;
		
		do {
			System.out.println(string + ": ");
			string = reader.nextLine();
			try {
				LocalDate.parse(string);
			} catch (DateTimeParseException e) {
				// TODO: handle exception
			}
			
		} while (!fechaOk);
		
		return string;
	}

	private static String pedirString() {
		return pedirString("Introduce una cadena de texto: ");
	}

	private static String pedirString(String string) {
		String cadena;
		do {
			System.out.print(string + ": ");
			cadena = reader.nextLine();
		} while ((cadena == null) && (cadena.equals("")));
		
		return cadena;
	}
	
	private static String pedirStringPermitirVacio(String string) {
		String cadena;
		
		System.out.print(string + ": ");
		cadena = reader.nextLine();
		
		return cadena;
	}

	private static void modificarElementos() {
		int op;
		
		System.out.println("¿Que elemento quieres modificar?");
		System.out.println("===========================================================================");
		
		op = menuElementos();
		modificarElemento(op);
	}	

	private static void modificarElemento(int op) {
		int n;
		switch (op) {
		case 1:
			n = pedirNumeroDeElemento();
			Libro libroAModificar = (Libro) libros.get(n);
			modificarDatosLibro(libroAModificar);

			break;
		case 2:
			
			n = pedirNumeroDeElemento();
			Autor autorAModificar = (Autor) autores.get(n);
			modificarDatosAutor(autorAModificar);

			break;
		case 3:
			n = pedirNumeroDeElemento();
			Evento eventoAModificar = (Evento) eventos.get(n);
			modificarDatosEvento(eventoAModificar);
			

		break;

		default:
			break;
		}
		
	}

	private static void modificarDatosEvento(Evento eventoAModificar) {
		String nombre = pedirStringPermitirVacio("Nombre (enter si no quieres modificar)");
		String lugar = pedirStringPermitirVacio("Lugar (enter si no quieres modificar)");
		String fecha = pedirStringPermitirVacio("Fecha (YYYY-mm-dd) (enter si no quieres modificar)");
		
		if ((nombre != null) && (!nombre.equals(""))) {
			eventoAModificar.setNombre(nombre);
		}
		
		if ((lugar != null) && (!lugar.equals(""))) {
			eventoAModificar.setLugar(lugar);
		}
		
		if ((fecha != null) && (!fecha.equals(""))) {
			eventoAModificar.setFecha(fecha);
		}
		
	}

	private static void modificarDatosAutor(Autor autorAModificar) {
		String nombreCompleto = pedirStringPermitirVacio("Nombre completo (enter si no quieres modificar)");
		try {
			String fechaNacimiento = pedirStringPermitirVacio("Fecha de nacimiento (YYYY-mm-dd) (enter si no quieres modificar)");
			
			if ((fechaNacimiento != null) && (!fechaNacimiento.equals(""))) {
				autorAModificar.setFechaNacimiento(fechaNacimiento);
				
			}
		} catch (DateTimeParseException e) {
			System.out.println("******* ERROR: La fecha es invalida");
		}
		
		String pais = pedirStringPermitirVacio("Pais (enter si no quieres modificar)");
		
		if ((nombreCompleto != null) && (!nombreCompleto.equals(""))) {
			autorAModificar.setNombreCompleto(nombreCompleto);
		}
		

		if ((pais != null) && (!pais.equals(""))) {
			autorAModificar.setPais(pais);
		}
		
	}

	private static int pedirNumeroDeElemento() {
		System.out.print("¿Que numero de elemento quieres modificar? ");

		return Integer.parseInt(reader.nextLine());
	}

	private static void eliminarElementos() {
		int op;
		
		System.out.println();
		System.out.print("¿Que elemento quieres eliminar? ");
		
		op = menuElementos();
		eliminarElemento(op);
	}	

	private static void eliminarElemento(int op) {
		int n;
		switch (op) {
		case 1:
			n = pedirNumeroDeElemento();
			System.out.println("Borrando libro con titulo " + ((Libro) libros.get(n)).getTitulo());
			Libro libroAEliminar = (Libro) libros.remove(n);
			break;
		case 2:
			n = pedirNumeroDeElemento();
			System.out.println("Borrando Autor " + ((Autor) autores.get(n)).getNombreCompleto());
			Autor autorAEliminar = (Autor) autores.remove(n);
			break;
		case 3:
			n = pedirNumeroDeElemento();
			System.out.println("Borrando Evento " + ((Evento) eventos.get(n)).getNombre());
			Evento eventoAEliminar = (Evento) eventos.remove(n);
				
		break;

		default:
			break;
		}
		
	}
	
	
	private static void visualizarElementos() {
		int op;
		
		System.out.println("¿Que elementos quieres visualizar?");
		System.out.println("===========================================================================");
		
		op = menuElementos();
		visualizarElemento(op);
	}

	private static void visualizarElemento(int op) {
		switch (op) {
			case 1:
				System.out.println("Mostrando todos los libros (" + libros.size() + ") cargados en memoria");
				System.out.println("===========================================================================");
				imprimirObjectos(libros);
				
				break;
			case 2:
				System.out.println("Mostrando todos los autores (" + autores.size() + ") cargados en memoria");
				System.out.println("===========================================================================");
				imprimirObjectos(autores);
				
				break;
			case 3:
				System.out.println("Mostrando todos los eventos sobre libros (" + eventos.size() + ") cargados en memoria");
				System.out.println("===========================================================================");
				imprimirObjectos(eventos);
				break;

		default:
			break;
		}
		
	}
	
	private static void imprimirObjectos(List <Object> objetos)
	{
		Object o;
		int contador = 0;
		
		for (Iterator i = objetos.iterator(); i.hasNext();) {
			o = (Object) i.next();
			System.out.println(contador++ + " " + o);
		}
	}


}

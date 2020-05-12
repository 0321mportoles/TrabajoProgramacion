import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author MARTA
 *
 */
public class Inicio {

	public static Scanner reader = new Scanner(System.in);
	public static List<Object> libros  = new ArrayList<Object>();
	public static List<Object> autores = new ArrayList<Object>();
	public static List<Object> eventos = new ArrayList<Object>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int opcion;
		

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
		int numero = -1;
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
		
		try {
			numero = Integer.parseInt(reader.nextLine());
		} catch (Exception e) {
			System.out.print("La opcion introducida debe ser un numero");
		}

		return numero;
			
	}
	
	private static int menuElementos() {
		int numero = -1;
		
		System.out.println();
		System.out.println("1 .- Libro");
		System.out.println("2 .- Autor");
		System.out.println("3 .- Evento de un libro");
		System.out.println("0 .- Volver atrás");
		System.out.println("===================================================");
		System.out.print(" - Elige una opción de untre las disponibles (0-3): ");
		
		try {
			numero = Integer.parseInt(reader.nextLine());
		} catch (Exception e) {
			System.out.print("La opcion introducida debe ser un numero");
		}

		return numero;
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
				System.out.println("Importando a fichero de TEXTO");
				if (Importar.importarDeFicheroTexto(autores, libros, eventos)) {
					System.out.println("El fichero ha sido importado correctamente");
				} else {
					System.out.println("Ha habido un error en la importación");
				}
				break;
			case 7:
				System.out.println("Exportando a fichero de TEXTO");
				if (Exportar.exportarAFicheroTexto(autores, libros, eventos)) {
					System.out.println("El fichero ha sido exportado correctamente");
				} else {
					System.out.println("Ha habido un error en la exportación");
				}
				break;
			case 8:
				System.out.println("Importando a fichero BINARIO");
				if (Importar.importarDeFicheroBinario(autores, libros, eventos)) {
					System.out.println("El fichero ha sido importado correctamente");
				} else {
					System.out.println("Ha habido un error en la importación");
				}
				break;
			case 9:
				System.out.println("Exportando a fichero BINARIO");
				if (Exportar.exportarAFicheroBinario(autores, libros, eventos)) {
					System.out.println("El fichero ha sido exportado correctamente");
				} else {
					System.out.println("Ha habido un error en la exportación");
				}
				break;
//			case 10:
//				System.out.println("Importando a fichero Acceso Aleatorio");
//				if (Importar.importarDeFicheroDeAccesoAleatorio(autores, libros, eventos)) {
//					System.out.println("El fichero ha sido importado correctamente");
//				} else {
//					System.out.println("Ha habido un error en la importación");
//				}
//				break;
			
			case 11:
				System.out.println("Exportando a fichero Acceso Aleatorio");
			if (Exportar.exportarAFicheroDeAccesoAleatorio(autores, libros, eventos)) {
				System.out.println("El fichero ha sido exportado correctamente");
			} else {
				System.out.println("Ha habido un error en la exportación");
			}
				break;
			case 0:
				
				break;
			default:
				System.out.println("La opción insertada es incorrecta");
		}
	}

	private static void buscarElementos() {
		int op;
		
		System.out.println("¿Que tipo de elemento quieres buscar?");
		System.out.println("=====================================");
		
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
				buscarAutores();
			
				break;
			// Busqueda de eventos
			case 3:
				buscarEventos();
				break;
	
			default:
				System.out.println("La opción insertada es incorrecta");
				break;
		}
		
		
	}
	
	private static void buscarLibros() {
		int op;
		List<Object> librosBuscados;
		
		op= menuCriterioDeBusquedaLibro();
		switch (op) {
			// Buscando por titulo
			case 1:
				librosBuscados = Libro.buscarLibrosPorTitulo(libros, pedirString("Titulo a buscar"));
				imprimirObjectos(librosBuscados);
				break;
			// Buscando por autor
			case 2:
				librosBuscados = Libro.buscarLibrosPorAutor(libros, pedirString("Autor a buscar"));
				imprimirObjectos(librosBuscados);
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
				
			default:
				System.out.println("La opción insertada es incorrecta");
			
		}
	}
	
	private static void buscarAutores() {
		int op;
		List<Object> autoresBuscados;
		
		op= menuCriterioDeBusquedaAutor();
		switch (op) {
			// Buscando por nombre
			case 1:
				autoresBuscados = Autor.buscarAutoresPorNombre(autores, pedirString("Titulo a buscar"));
				imprimirObjectos(autoresBuscados);
				break;
			// Buscando por pais
			case 2:
				autoresBuscados = Autor.buscarAutorPorPais(autores, pedirString("Autor a buscar"));
				imprimirObjectos(autoresBuscados);
				break;
			// Buscando por fecha de nacimiento
			case 3:
				boolean error = true;
				while (error) {
					try {
						String fecha = pedirString("Fecha de nacimiento a buscar (YYYY-mm-dd)");
						LocalDate.parse(fecha);
						autoresBuscados = Autor.buscarAutorPorFecha(autores, fecha);
						imprimirObjectos(autoresBuscados);
						error = false;
					} catch (DateTimeParseException e) {
						System.out.println("******** ERROR. La fecha debe seguir el formato especificado **********");
					}
				}
				
				break;
			default:
				System.out.println("La opción insertada es incorrecta");
		}
	}
	private static void buscarEventos() {
		int op;
		List<Object> eventosBuscados;
		
		op= menuCriterioDeBusquedaEvento();
		switch (op) {
			// Buscando por nombre
			case 1:
				eventosBuscados = Evento.buscarEventoPorNombre(eventos, pedirString("Nombre a buscar"));
				imprimirObjectos(eventosBuscados);
				break;
			// Buscando por lugar
			case 2:
				eventosBuscados = Evento.buscarEventoPorLugar(eventos, pedirString("Evento a buscar"));
				imprimirObjectos(eventosBuscados);
				break;
			// Buscando por fecha
			case 3:
				boolean error = true;
				while (error) {
					try {
						String fecha = pedirString("Fecha de evento a buscar (YYYY-mm-dd)");
						LocalDate.parse(fecha);
						eventosBuscados = Evento.buscarEventoPorFecha(eventos, fecha);
						imprimirObjectos(eventosBuscados);
						error = false;
					} catch (DateTimeParseException e) {
						System.out.println("******** ERROR. La fecha debe seguir el formato especificado **********");
					}
				}
				
				break;
			// Buscando por Libro
			case 4:
				eventosBuscados = Evento.buscarEventosPorLibro(eventos, pedirString("Editorial a buscar"));
				imprimirObjectos(eventosBuscados);	
				break;
			default:
				System.out.println("La opción insertada es incorrecta");
		}
	}

	private static int menuCriterioDeBusquedaLibro() {
		int numero = -1;
		
		System.out.println("¿Qué criterio de busqueda quieres?");
		System.out.println("1 .- Por titulo");
		System.out.println("2 .- Por autor");
		System.out.println("3 .- Por fecha de publicacion");
		System.out.println("4 .- Por editorial");
		System.out.println("0 .- Volver atrás");
		System.out.println("=====================================");
		System.out.print(" - Elige una opción de untre las disponibles (0-4): ");
		
		try {
			numero = Integer.parseInt(reader.nextLine());
		} catch (Exception e) {
			System.out.print("La opcion introducida debe ser un numero");
		}

		return numero;
	}
	private static int menuCriterioDeBusquedaAutor() {
		int numero = -1;
		
		System.out.println("¿Qué criterio de busqueda quieres?");
		System.out.println("1 .- Por nombre completo");
		System.out.println("2 .- Por pais");
		System.out.println("3 .- Por fecha de nacimiento");
		System.out.println("0 .- Volver atrás");
		System.out.println("=====================================");
		System.out.print(" - Elige una opción de untre las disponibles (0-4): ");
				
		try {
			numero = Integer.parseInt(reader.nextLine());
		} catch (Exception e) {
			System.out.print("La opcion introducida debe ser un numero");
		}

		return numero;
	}
	private static int menuCriterioDeBusquedaEvento() {
		int numero = -1;
		
		System.out.println("¿Qué criterio de busqueda quieres?");
		System.out.println("1 .- Por nombre");
		System.out.println("2 .- Por lugar");
		System.out.println("3 .- Por fecha");
		System.out.println("4 .- Por libro");
		System.out.println("0 .- Volver atrás");
		System.out.println("=====================================");
		System.out.print(" - Elige una opción de untre las disponibles (0-4): ");
		
		try {
			numero = Integer.parseInt(reader.nextLine());
		} catch (Exception e) {
			System.out.print("La opcion introducida debe ser un numero");
		}

		return numero;
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
			autores.add(autor);
			break;
		case 3:
			Evento evento;
			evento = pedirDatosNuevoEvento();
			eventos.add(evento);
			break;

		default:
			System.out.println("La opción insertada es incorrecta");
			break;
		}
		
	}

	private static Evento pedirDatosNuevoEvento() {
		System.out.println("Insertar nuevo Evento");
		System.out.println("===========================================================================");
		
		String nombre = pedirString("Nombre");
		String lugar = pedirString("Lugar");
		String fecha = pedirFecha("Fecha (YYYY-mm-dd)");
		Libro libro = pedirDatosNuevoLibro();
		libros.add(libro);
		return new Evento(nombre, lugar, fecha, libro);
	}

	private static Autor pedirDatosNuevoAutor() {
		System.out.println("Insertar nuevo autor");
		System.out.println("===========================================================================");
		
		String nombreCompleto = pedirString("Nombre completo");
		String fechaNacimiento = pedirFecha("Fecha de nacimiento (YYYY-mm-dd)");
		String pais = pedirString("Pais");
		
		return new Autor(nombreCompleto, fechaNacimiento, pais);
	}

	private static Libro pedirDatosNuevoLibro() {
		System.out.println("Insertar nuevo libro");
		System.out.println("===========================================================================");
		
		String titulo = pedirString("Titulo");
		String editorial = pedirString("Editorial");
		String fecha = pedirFecha("Fecha de publicación (YYYY-mm-dd)");
		Autor autor = pedirDatosNuevoAutor();
		autores.add(autor);
		return new Libro(titulo, editorial, fecha, autor);
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
		String stringFecha = "";
		
		do {
			System.out.print(string + ": ");
			stringFecha = reader.nextLine();
			try {
				LocalDate.parse(stringFecha);
				fechaOk = true;
			} catch (DateTimeParseException e) {
				System.out.println("La fecha introducida es incorrecta, vuelve a intentarlo");
			}
			
		} while (!fechaOk);
		
		return stringFecha;
	}

	private static String pedirString() {
		return pedirString("Introduce una cadena de texto: ");
	}

	private static String pedirString(String string) {
		String cadena = "";
		do {
			System.out.print(string + ": ");
			cadena = reader.nextLine();
		} while ((cadena == null) || (cadena.equals("")));
		
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
			System.out.println("La opción insertada es incorrecta");
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
		int numero = -1;
			
		System.out.print("¿Que numero de elemento quieres modificar? ");

		try {
			numero = Integer.parseInt(reader.nextLine());
		} catch (Exception e) {
			System.out.print("La opcion introducida debe ser un numero");
		}

		return numero;
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
			System.out.println("La opción insertada es incorrecta");
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
		System.out.println();
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
				System.out.println("La opción insertada es incorrecta");
			break;
		}
		
	}
	
	private static void imprimirObjectos(List <Object> objetos)
	{
		Object o;
		int contador = 0;
		
		if (objetos.size() == 0) {
			System.out.println("No hay elementos para mostrar");
		}
		
		for (Iterator i = objetos.iterator(); i.hasNext();) {
			o = (Object) i.next();
			System.out.println(contador++ + " " + o);
		}
	}


}

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

		return reader.nextInt();
	}

	private static void ejecutarOpciones(int opcion) {
		System.out.println();
		switch (opcion) {
			case 1:
				insertarElementos();
				break;
			case 2:
				
				
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

		default:
			break;
		}
		
	}

	private static Libro pedirDatosNuevoLibro() {
		System.out.println("Insertar nuevo libro");
		System.out.println("===========================================================================");
		
		String titulo = pedirString("Titulo");
		String editorial = pedirString("Editorial");
		String fecha = pedirFecha("Fecha de publicación (YYYY-mm-dd): ");
		
		return new Libro(titulo, editorial, fecha);
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
			System.out.println(string + ": ");
			cadena = reader.nextLine();
		} while ((cadena != null) && (!cadena.equals("")));
		
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
	
		
	}

	private static void eliminarElementos() {
		int op;
		
		System.out.println("¿Que elemento quieres eliminar?");
		System.out.println("===========================================================================");
		
		op = menuElementos();
		eliminarElemento(op);
	}	

	private static void eliminarElemento(int op) {
		
		
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
				System.out.println("Mostrando todos los autores (" + autores.size() + ") cargados en memoria");
				System.out.println("===========================================================================");
				imprimirObjectos(autores);
				break;
			case 2:
				System.out.println("Mostrando todos los libros (" + eventos.size() + ") cargados en memoria");
				System.out.println("===========================================================================");
				imprimirObjectos(eventos);
				break;
			case 3:
				System.out.println("Mostrando todos los libros (" + libros.size() + ") cargados en memoria");
				System.out.println("===========================================================================");
				imprimirObjectos(libros);
			

		default:
			break;
		}
		
	}
	
	private static void imprimirObjectos(List <Object> objetos)
	{
		Object o;
		
		for (Iterator i = objetos.iterator(); i.hasNext();) {
			o = (Object) i.next();
			System.out.println(o);
		}
	}

	private static int menuElementos() {
		System.out.println("1 .- Autor");
		System.out.println("2 .- Presentacion de un libro");
		System.out.println("3 .- Libro");
		System.out.println("0 .- Volver atrás");
		
		System.out.print(" - Elige una opción de untre las disponibles (0-3): ");
		
		return reader.nextInt();
	}
}

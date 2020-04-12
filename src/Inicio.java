import java.util.Scanner;

/**
 * @author MARTA
 *
 */
public class Inicio {

	public static Scanner reader = new Scanner(System.in);
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
		System.out.println("                     TRABAJO FIN DE CURSO - PROGRAMACIÓN 2019-2020                     ");
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
		
		
	}

	private static void modificarElementos() {
		int op;
		
		System.out.println("¿Que elemento quieres modificar?");
		System.out.println("===========================================================================");
		
		op = menuElementos();
		modificarElemento(op);
	}	

	private static void modificarElemento(int op) {
		// TODO Auto-generated method stub
		
	}

	private static void eliminarElementos() {
		int op;
		
		System.out.println("¿Que elemento quieres eliminar?");
		System.out.println("===========================================================================");
		
		op = menuElementos();
		eliminarElemento(op);
	}	

	private static void eliminarElemento(int op) {
		// TODO Auto-generated method stub
		
	}
	
	private static void visualizarElementos() {
		int op;
		
		System.out.println("¿Que elementos quieres visualizar?");
		System.out.println("===========================================================================");
		
		op = menuElementos();
		visualizarElemento(op);
	}

	private static void visualizarElemento(int op) {
		// TODO Auto-generated method stub
		
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

import java.awt.font.NumericShaper;
import java.io.*;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Importar {
	public static boolean importarDeFicheroTexto(List<Object> autores, List<Object> libros, List<Object> eventos)
	{
	    autores.clear();
	    libros.clear();
	    eventos.clear();
	    
	    File fichero = new File("FicheroTexto.txt"); //declarar fichero
	    BufferedReader fic;
	    int numeroDeAutores = 0, numeroDeLibros = 0, numeroDeEventos = 0;
	    
		try {
			fic = new BufferedReader(new FileReader(fichero));
		    
			String linea = fic.readLine();
			
			if (linea != null) {
				numeroDeAutores = Integer.parseInt(linea);
			}
			
			/* Importando autores */
			while (numeroDeAutores > 0 && (linea = fic.readLine()) != null) { //se va leyendo un carácter
				Autor autor = Autor.leerDesdeLineaDeTexto(linea);
				autores.add(autor);
				numeroDeAutores--;
			}
			linea = fic.readLine();

			if (linea != null) {
				numeroDeLibros= Integer.parseInt(linea);
			}
			
			/* Importando libros */
			while (numeroDeLibros > 0 && (linea = fic.readLine()) != null) { 
				Libro libro = Libro.leerDesdeLineaDeTexto(linea, autores);
				libros.add(libro);
				numeroDeLibros--;
			}
			
			linea = fic.readLine();
			
			if (linea != null) {
				numeroDeEventos= Integer.parseInt(linea);
			}
			/* Importando eventos */
			while (numeroDeEventos > 0 && (linea = fic.readLine()) != null) {
				Evento evento = Evento.leerDesdeLineaDeTexto(linea, libros);
				eventos.add(evento);
				numeroDeEventos--;
			}
			
		    
			fic.close(); //cerrar fichero 
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			return false;
		} catch (IOException e) {
			System.out.println("IOException");
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	    
	          
	    
		
	    return true;
	}

	

	public static boolean importarDeFicheroBinario(List<Object> autores, List<Object> libros, List<Object> eventos) {
		File fichero = new File("FichData.dat"); 
	    int numeroDeAutores = 0, numeroDeLibros = 0, numeroDeEventos = 0;
	    
	    autores.clear();
	    libros.clear();
	    eventos.clear();
	    
		try {
			DataInputStream fic = new
	                  DataInputStream(new FileInputStream(fichero));
			
			numeroDeAutores = fic.readInt();
			/* Importando autores */
			while (numeroDeAutores > 0) { 
				Autor autor = new Autor(
						fic.readUTF(), 
						fic.readUTF(), 
						fic.readUTF());
		
				autores.add(autor);
				numeroDeAutores--;
			}
			
			numeroDeLibros = fic.readInt();
			/* Importando libros */
			while (numeroDeLibros > 0 ) { 
				String titulo = fic.readUTF(); 
				String editorial = fic.readUTF();
				String publicacion = fic.readUTF();
				int paginas = fic.readInt();
				String cadenaAutor = fic.readUTF();
				List<Object> listadoAutores = Autor.buscarAutoresPorNombre(autores, cadenaAutor);
				
				if (listadoAutores.size() != 1) {
					if (listadoAutores.size() == 0) {
						throw new Exception("El autor " + cadenaAutor + " no ha sido creado previamente");
					} else {
						throw new Exception("Hay " + listadoAutores.size() + " autores que coinciden con la busqueda '" + cadenaAutor + "'");
					}
				}
				
				Libro libro = new Libro(titulo, editorial, publicacion, paginas, (Autor) listadoAutores.get(0));
				libros.add(libro);
				numeroDeLibros--;
			}
			
			numeroDeEventos = fic.readInt();
			System.out.println("Eventos detectados: " + numeroDeEventos);
			/* Importando eventos */
			while (numeroDeEventos > 0) {
				String nombre 		= fic.readUTF(); 
				String lugar 		= fic.readUTF(); 
				String fecha 		= fic.readUTF(); 
				String cadenaLibro 	= fic.readUTF(); 
				List<Object> listadoLibros = Libro.buscarLibrosPorTitulo(libros, cadenaLibro);
				
				if (listadoLibros.size() != 1) {
					if (listadoLibros.size() == 0) {
						throw new Exception("El libro " + cadenaLibro + " no ha sido creado previamente");
					} else {
						throw new Exception("Hay " + listadoLibros.size() + " libros que coinciden con la busqueda '" + cadenaLibro + "'");
					}
				}
				
				Evento evento = new Evento(nombre, lugar, fecha, (Libro) listadoLibros.get(0));
				eventos.add(evento);
				System.out.println("Evento leido: " + evento);
				numeroDeEventos--;
			}
    
			fic.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			return false;
		} catch (EOFException e) {
			System.out.println("EOFException");
		} catch (IOException e) {
			System.out.println("IOException");
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	    	
	    return true;
	}

	public static boolean importarDeFicheroDeAccesoAleatorio (List<Object> autores, List<Object> libros, List<Object> eventos) {
	    autores.clear();
	    libros.clear();
	    eventos.clear();
	    
	    File fichero = new File("FicheroAccesoAleatorio.dat"); 

	    int posicion = 0, entero;
	    String fecha, cadena1, cadena2, cadena3 = null;
	    
		try {
			RandomAccessFile fic = new RandomAccessFile(fichero, "r");
			System.out.println("Colocando puntero en posicion: " + posicion);
			fic.seek(posicion);
			
			int numeroDeAutores = fic.readInt();
			posicion += 4;

			System.out.println("Importando (" + numeroDeAutores + ") autores");
			/* Importando autores */
			while (numeroDeAutores > 0) {
				cadena1 = leerCadenaDeFichero(fic, Autor.LONGITUD_MAX_CADENA);
				fecha = leerCadenaDeFichero(fic, Autor.LONGITUD_MAX_FECHA);
				cadena2 = leerCadenaDeFichero(fic, Autor.LONGITUD_MAX_CADENA);
				Autor a = new Autor(cadena1, fecha, cadena2);
				autores.add(a);
				numeroDeAutores--;
				posicion = posicion + Autor.LONGITUD_MAX_OBJ_BYTES;
				fic.seek(posicion);
			}
			
			int numeroDeLibros = fic.readInt();
			System.out.println("Importando (" + numeroDeLibros + ") libros");
			posicion += 4;
			/* Importando libros */
			while (numeroDeLibros > 0 ) { 
				/* Leo Titulo del libro */
				cadena1 = leerCadenaDeFichero(fic, Libro.LONGITUD_MAX_CADENA);
				/* Leo Editorial*/
				cadena2 = leerCadenaDeFichero(fic, Libro.LONGITUD_MAX_CADENA);
				/* Leo Fecha de publicación*/
				fecha = leerCadenaDeFichero(fic, Libro.LONGITUD_MAX_FECHA);
				/* Leo el número de paginas */
				entero = fic.readInt();
				/* Leo el NOMBRE del Autor relacionado con el libro */
				cadena3 = leerCadenaDeFichero(fic, Libro.LONGITUD_MAX_CADENA);				
				/* BUSCO el autor de mi listado de autores*/
				List<Object> listadoAutores = Autor.buscarAutoresPorNombre(autores, cadena3);
				
				/* Me aseguro de que solo encuentro un autor con ese nombre */
				if (listadoAutores.size() != 1) {
					if (listadoAutores.size() == 0) {
						throw new Exception("El autor " + cadena3 + " no ha sido creado previamente");
					} else {
						throw new Exception("Hay " + listadoAutores.size() + " autores que coinciden con la busqueda '" + cadena3 + "'");
					}
				}
				
				/* Creo mi libro */
				Libro libro = new Libro(cadena1, cadena2, fecha, entero, (Autor) listadoAutores.get(0));
				
				/* Añado el libro creado a mi lista de libros */
				libros.add(libro);
				/* Resto el número de libros que quedan por leer*/
				numeroDeLibros--;
				/* Actualizo la posición del puntero que debe apuntar en el próximo elemento*/
				posicion= posicion + Libro.LONGITUD_MAX_OBJ_BYTES;
				fic.seek(posicion);
			}
			
			int numeroDeEventos = fic.readInt();
			posicion += 4;
			System.out.println("Importando " + numeroDeEventos + " eventos");
			/* Importando eventos */
			while (numeroDeEventos > 0) {
				cadena1 = leerCadenaDeFichero(fic, Evento.LONGITUD_MAX_CADENA);
				cadena2 = leerCadenaDeFichero(fic, Evento.LONGITUD_MAX_CADENA);
				fecha = leerCadenaDeFichero(fic, Evento.LONGITUD_MAX_FECHA);
				cadena3 = leerCadenaDeFichero(fic, Evento.LONGITUD_MAX_CADENA);						
				
				List<Object> listadoLibros = Libro.buscarLibrosPorTitulo(libros, cadena3);
				
				if (listadoLibros.size() != 1) {
					if (listadoLibros.size() == 0) {
						throw new Exception("El libro " + cadena3 + " no ha sido creado previamente");
					} else {
						throw new Exception("Hay " + listadoLibros.size() + " libros que coinciden con la busqueda '" + cadena3 + "'");
					}
				}
				
				Evento evento = new Evento(cadena1, cadena2, fecha, (Libro) listadoLibros.get(0));
				eventos.add(evento);
				numeroDeEventos--;
				posicion= posicion + Evento.LONGITUD_MAX_OBJ_BYTES;
				fic.seek(posicion);
			}
    
			System.out.println();
			fic.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			return false;
		} catch (EOFException e) {
		} catch (IOException e) {
			System.out.println("IOException");
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	    	
	    return true;
	}



	private static String leerCadenaDeFichero(RandomAccessFile fic, Integer longitudMaxCadena) throws IOException {
		String nombre = "";
		char aux;
		
		for (int i = 0; i < longitudMaxCadena; i++) {
			aux = fic.readChar(); 
			nombre = nombre.concat("" + aux);
		}
		
		return nombre.trim();
	}
}
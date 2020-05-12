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
						throw new Exception("Hay " + listadoLibros.size() + " libros que coinciden con la busqueda '" + listadoLibros + "'");
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
		
	    File fichero = new File("FicheroAccesoAleatorio.dat"); 
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
						throw new Exception("Hay " + listadoLibros.size() + " libros que coinciden con la busqueda '" + listadoLibros + "'");
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
}
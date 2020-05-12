import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.List;

public class Exportar {
	
		
	public static boolean exportarAFicheroTexto(
			List<Object> autores, 
			List<Object> libros, 
			List<Object> eventos)
	{
		File fichero = new File("FicheroTexto.txt");	//declara fichero          
		FileWriter fic;
		
		try {
			fic = new FileWriter(fichero);
			fic.write(String.valueOf(autores.size()));
				
			for (int i = 0; i < autores.size(); i++) {
				fic.write("\n");
				fic.write(((Autor) autores.get(i)).escribirAFicheroDeTexto());
			}
				
			fic.write("\n");
			fic.write(String.valueOf(libros.size()));
			for (int i = 0; i < libros.size(); i++) {
				fic.write("\n");
				fic.write(((Libro) libros.get(i)).escribirAFicheroDeTexto());
			}
			
			fic.write("\n");
			fic.write(String.valueOf(eventos.size()));
			for (int i = 0; i < eventos.size(); i++) {
				fic.write("\n");
				fic.write(((Evento) eventos.get(i)).escribirAFicheroDeTexto());
			}
				
			fic.close();

		} catch (IOException e) {
			return false;
		}
		
		return true;			

	}

	public static boolean exportarAFicheroBinario(
			List<Object> autores, 
			List<Object> libros, 
			List<Object> eventos)
	{
		File fichero = new File("FichData.dat");	//declara fichero          
		DataOutputStream fic;
		
		try {
			fic = new DataOutputStream(new FileOutputStream(fichero));
			fic.writeInt(autores.size());
			for (Object autor : autores) {
				fic.writeUTF(((Autor) autor).getNombreCompleto());
				fic.writeUTF(((Autor) autor).getFechaNacimiento().toString());
				fic.writeUTF(((Autor) autor).getPais());
			}
				
			fic.writeInt(libros.size());
			for (int i = 0; i < libros.size(); i++) {
				fic.writeUTF(((Libro) libros.get(i)).getTitulo());
				fic.writeUTF(((Libro) libros.get(i)).getEditorial());
				fic.writeUTF(((Libro) libros.get(i)).getPublicacion().toString());
				fic.writeInt(((Libro) libros.get(i)).getPaginas());
				fic.writeUTF(((Libro) libros.get(i)).getAutor().getNombreCompleto());
			}
			
			fic.writeInt(eventos.size());
			for (int i = 0; i < eventos.size(); i++) {
				fic.writeUTF(((Evento) eventos.get(i)).getNombre());
				fic.writeUTF(((Evento) eventos.get(i)).getLugar());
				fic.writeUTF(((Evento) eventos.get(i)).getFecha().toString());
				fic.writeUTF(((Evento) eventos.get(i)).getLibro().getTitulo());
			}
				
			fic.close();

		} catch (FileNotFoundException e1) {
			return false;
		} catch (IOException e) {
			return false;
		} 
		
		return true;			

	}

	public static boolean exportarAFicheroDeAccesoAleatorio(
			List<Object> autores, 
			List<Object> libros, 
			List<Object> eventos)
	{
		File fichero = new File("FicheroAccesoAleatorio.dat");	         
		RandomAccessFile fic;
		
			
		try {
			fic = new RandomAccessFile(fichero, "rw");	
			fic.writeInt(autores.size());
			for (Object autor : autores) {
				fic.writeChars((((Autor) autor).getNombreCompleto()));
				fic.writeChars(((Autor) autor).getFechaNacimiento().toString());
				fic.writeChars(((Autor) autor).getPais());
			}
				
				
			fic.writeInt(libros.size());
			for (Object libro : libros) {
				fic.writeChars(((Libro) libro).getTitulo());
				fic.writeChars(((Libro) libro).getEditorial());
				fic.writeChars(((Libro) libro).getPublicacion().toString());
				fic.writeInt(((Libro) libro).getPaginas());
				fic.writeChars(((Libro) libro).getAutor().getNombreCompleto());
			} 
			
			fic.writeInt(eventos.size());
			for (Object evento : eventos)  {
				fic.writeChars(((Evento) evento).getNombre());
				fic.writeChars(((Evento) evento).getLugar());
				fic.writeChars(((Evento) evento).getFecha().toString());
				fic.writeChars(((Evento) evento).getLibro().getTitulo());
			}
				
			fic.close();

		} catch (FileNotFoundException e1) {
			return false;
		} catch (IOException e) {
			return false;
		} 
		
		return true;			

	

	}


}
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
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
		
		StringBuffer buffer = null;
		try {
			fic = new RandomAccessFile(fichero, "rw");
			fic.writeInt(autores.size());
			for (Object autor : autores) {
				buffer = new StringBuffer((((Autor) autor).getNombreCompleto()));      
				buffer.setLength(Autor.LONGITUD_MAX_CADENA);
				fic.writeChars(buffer.toString());
				buffer = new StringBuffer(((Autor) autor).getFechaNacimiento().toString());
				buffer.setLength(Autor.LONGITUD_MAX_FECHA);
				fic.writeChars(buffer.toString());
				buffer = new StringBuffer(((Autor) autor).getPais());      
				buffer.setLength(Autor.LONGITUD_MAX_CADENA);
				fic.writeChars(buffer.toString());
			}
				
			fic.writeInt(libros.size());
			for (Object libro : libros) {
				buffer = new StringBuffer( ((Libro) libro).getTitulo() );      
				buffer.setLength(Libro.LONGITUD_MAX_CADENA); 
			    fic.writeChars(buffer.toString());
		    	buffer = new StringBuffer( ((Libro) libro).getEditorial() );      
		    	buffer.setLength(Libro.LONGITUD_MAX_CADENA); 
			    fic.writeChars(buffer.toString());
		    	buffer = new StringBuffer( ((Libro) libro).getPublicacion().toString() );      
		    	buffer.setLength(Libro.LONGITUD_MAX_FECHA);
			    fic.writeChars(buffer.toString());
			    fic.writeInt(((Libro) libro).getPaginas());
		    	buffer = new StringBuffer( ((Libro) libro).getAutor().getNombreCompleto() );      
		    	buffer.setLength(Libro.LONGITUD_MAX_CADENA); 
			    fic.writeChars(buffer.toString());
			} 
			
			fic.writeInt(eventos.size());
			for (Object evento : eventos)  {
				buffer = new StringBuffer(((Evento) evento).getNombre());      
				buffer.setLength(Evento.LONGITUD_MAX_CADENA); 
				fic.writeChars(buffer.toString());
				buffer = new StringBuffer(((Evento) evento).getLugar());      
				buffer.setLength(Evento.LONGITUD_MAX_CADENA); 
				fic.writeChars(buffer.toString());
				buffer = new StringBuffer(((Evento) evento).getFecha().toString());      
				buffer.setLength(Evento.LONGITUD_MAX_FECHA);
				fic.writeChars(buffer.toString());
				buffer = new StringBuffer(((Evento) evento).getLibro().getTitulo());      
				buffer.setLength(Evento.LONGITUD_MAX_CADENA); 
				fic.writeChars(buffer.toString());
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
package com.grupoatrium.persistencia;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.grupoatrium.modelo.Libro;
import com.grupoatrium.modelo.LibroNoEncontradoException;


/*****************************************************************************************
 * Nombre de Clase : LibrosDAO															 *
 * Autor: Miguel Rosa																	 *
 * Fecha: 09 - Noviembre - 2016															 *
 * Versi�n: 1.0																			 *
 * Descripci�n: Esta clase se encarga de la capa de conexi�n con la base de datos.		 *
 * Los datos de conexi�n se definen directamente en la secci�n ATRIBUTOS. 				 *
 * Esta clase est� definida �nicamente para la conexi�n a una base de datos MySQL. 		 *
 *****************************************************************************************/

public class LibrosDAO implements ItfzLibrosDao{
	
	/*************************************************************************************
	 * ATRIBUTOS																		 *
	 *************************************************************************************/
		
	// Creamos la SessionFactory
	private SessionFactory sf = new Configuration().configure().buildSessionFactory();
			
	// De la SessionFactory obtenemos la Session 
	private Session session = sf.openSession();
	
	/*************************************************************************************
	 * CONSTRUCTORES																	 *
	 *************************************************************************************/
	
	public LibrosDAO() {
				
	}

	/*************************************************************************************
	 * GETTERS Y SETTERS															
	 *************************************************************************************/
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	public Session getSesion() {
		return session;
	}

	public void setSesion(Session sesion) {
		this.session = sesion;
	}

	/*************************************************************************************
	 * METODOS 																			 *
	 *************************************************************************************/
	
	/* Nombre: mensajeError()
	 * Argumentos: String
	 * Devuelve: void
	 * Descripci�n: Se detecta durante la realizaci�n del programa que las
	 * instrucciones de este m�todo se repiten considerablemente.
	 * Se crea este m�todo para simplificar y clarificar el c�digo. El String que
	 * se pasa como par�metro se muestra al usuario para informarle de que se
	 * ha producido un error.
	 */
	
	public String mensajeError(String str, Exception e) {
		
		// Se declara un String que almacenar� los datos introducidos por el
		// usuario
		String cadena;
		
		// Imprimimos el registro de error
		e.printStackTrace();
		
		// Se informa al usuario o se le solicita un dato.
		System.out.println();
		System.out.println(str);
		
		// Se devuelven los datos escritos por el usuario.
		Scanner sc = new Scanner(System.in);
		cadena = sc.nextLine();
		
		// Devolvemos el dato introducido por el usuario
		return cadena;
		
	}
	
	/*************************************************************************************
	 * METODOS SOBREESCRITOS INTERFAZ ItfzLibrosDao										 *
	 *************************************************************************************/
	
	/* Nombre: altaLibro()
	 * Argumentos: Libro
	 * Devuelve: Boolean
	 * Descripci�n: Este m�todo se encarga de gestionar todos los procesos necesarios
	 * para dar de alta un registro en la base de datos.
	 */

	@Override
	public boolean altaLibro(Libro libro) {
		
		//Direccion direccion = libro.getEditorial().getDireccion();
		//Editorial editorial = libro.getEditorial();
		
		// Variable de retorno
		boolean salida = true;
		
		try {
			
			// Persistimos el objeto Libro
			session.save(libro);

			
		// Si ocurre un error en el proceso capturamos la Excepci�n 
		} catch (Exception e) {
			
			// Informamos al usuario de que se ha producido un error durante el registro
			mensajeError("\nSE HA PRODUCIDO UN ERROR AL INTENTAR INTRODUCIR LOS DATOS EN LA BASE DE DATOS.\nPulse INTRO para CONTINUAR.", e);
			
			// Imprimos la pila de error.
			e.printStackTrace();
			
			// Como no se ha realizado el registro del libro en la base de datos devolvemos
			// false.
			salida = false;
			
		}
		
		return salida;
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: eliminarLibro()
	 * Argumentos: Integer
	 * Devuelve: Boolean
	 * Descripci�n: Este m�todo se encarga de gestionar los procesos necesarios
	 * para eliminar de la base de datos un registro, con el id que coincida con el 
	 * Integer que se le pasa como par�metro al m�todo.
	 * En caso de que el Integer que se pasa por par�metro no coincida con el ID de ning�n
	 * registro en la base de datos se lanzar� la excepci�n LibroNoEncontradoException.
	 */

	@Override
	public boolean eliminarLibro(int id) throws LibroNoEncontradoException {
		
		// Variable de retorno
		boolean salida = true;

		try {
			
			// Comprobamos que el id que pasamos como argumento existe en la BDD
			Query miQuery = session.createQuery("SELECT l FROM Libro l WHERE l.IDLibro = " + id);
			List<Libro> milista = miQuery.list();
			
			// Si el tama�o del List devuelto es 0 lanzamos la excepcion 
			// LibroNoEncontradoException
			if (milista.size() == 0) {
				throw new LibroNoEncontradoException("ID");
			} 
			// si no, borramos el libro
			else {
				Query otraQuery = session.createQuery("DELETE Libro l WHERE l.IDLibro = " + id);
				otraQuery.executeUpdate();
			}
			
		// Si ocurre un error en el proceso capturamos la Excepci�n
		} catch (Exception e) {
			
			// Informamos al usuario de que se ha producido un error durante el proceso
			mensajeError("\nSE HA PRODUCIDO UN ERROR AL INTENTAR ELIMINAR EL REGISTRO DE LA BASE DE DATOS.\nPulse INTRO para CONTINUAR.", e);
			
			// Imprimos la pila de error.
			e.printStackTrace();
			
			// Como no se ha eliminado el registro del libro en la base de datos devolvemos
			// false.
			salida = false;
		}
		
		return salida;
		
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: consultarTodos()
	 * Argumentos: Ninguno
	 * Devuelve: List
	 * Descripci�n: Este m�todo se encarga de gestionar los procesos necesarios
	 * para recoger de la base de datos, todos los datos, de todos los registros.
	 */

	@Override
	public List<Libro> consultarTodos() {
		
		// Creamos una colecci�n ArrayList
		List<Libro> misLibros = new ArrayList<Libro>();
		
		try {
			
			// Creamos un Query con la consulta
			Query miQuery = session.createQuery("SELECT l FROM Libro l");
			
			// Creamos una lista a partir del Query anterior
			misLibros = miQuery.list();
		
		// Si ocurre un error en el proceso capturamos la Excepci�n
		} catch (Exception e) {
			
			// Informamos al usuario de que se ha producido un error durante el proceso
			mensajeError("\nSE HA PRODUCIDO UN ERROR AL INTENTAR LEER LOS REGISTROS DE LA BASE DE DATOS.\nPulse INTRO para CONTINUAR.", e);
			
			// Imprimimos la cola de error.
			e.printStackTrace();
			
			// Como no se ha podido leer la base de datos y el ArrayList estar� vac�o
			// devolvemos null
			misLibros = null;

		}	
		
		return misLibros;
		
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: consultarISBN()
	 * Argumentos: String
	 * Devuelve: Libro
	 * Descripci�n: Este m�todo se encarga de gestionar los procesos necesarios
	 * para filtar de la base de datos, un registro con el isbn que coincida con el String 
	 * que se le pasa como par�metro al m�todo, y recoger sus datos.
	 * En caso de que el String no coincida con el ISBN de ning�n registro en la base de 
	 * datos se lanzar� la excepci�n LibroNoEncontradoException.
	 */

	@Override
	public Libro consultarISBN(String isbn) throws LibroNoEncontradoException {
		
		// Se declara la variable libro que devuelve la funci�n
		Libro libro;
		
		try {
			
			// Comprobamos que el isbn que pasamos como argumento existe en la BDD
			Query miQuery = session.createQuery("SELECT l FROM Libro l WHERE l.isbn = '" + isbn + "'");
			List<Libro> miLista = miQuery.list();
			
			// Si el tama�o del List devuelto es 0 lanzamos la excepcion 
			// LibroNoEncontradoException
			if (miLista.size() == 0) {
				throw new LibroNoEncontradoException("ISBN");
			} 
			// si no, devolvemos el objetio Libro devuelto
			else {
				libro = miLista.get(0);
			}
		
		// Si ocurre un error en el proceso capturamos la Excepci�n
		} catch (Exception e) {
			
			// Informamos al usuario de que se ha producido un error durante el proceso
			mensajeError("\nSE HA PRODUCIDO UN ERROR AL INTENTAR LEER LOS REGISTROS DE LA BASE DE DATOS.\nPulse INTRO para CONTINUAR.", e);
			
			// Imprimimos la cola de error.
			e.printStackTrace();
			
			// Como no se ha podido leer la base de datos no se ha podido crear la
			// instancia de la clase Libro, devolvemos null
			libro = null;

		}	

		return libro;
		
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: consultarTitulo()
	 * Argumentos: String
	 * Devuelve: List
	 * Descripci�n: Este m�todo se encarga de gestionar los procesos necesarios
	 * para filtar de la base de datos los registros que contengan en su t�tulo el String
	 * que se le pasa como par�metro al m�todo y recoger todos sus datos.
	 * En caso de que el String no forme parte del TITULO de ninguno de los registros 
	 * se lanzar� la excepci�n LibroNoEncontradoException.
	 */

	@Override
	public List<Libro> consultarTitulo(String titulo) throws LibroNoEncontradoException {

		// Creamos la colecci�n ArrayList que devuelve la funci�n
		List<Libro> misLibros = new ArrayList<Libro>();
		
		try {
			
			// Creamos un Query con la consulta
			Query miQuery = session.createQuery("FROM Libro l WHERE l.titulo LIKE '%" + titulo + "%'");
			
			// Creamos una lista a partir del Query anterior
			misLibros = miQuery.list();
			
			// Si el tama�o del List devuelto es 0 lanzamos la excepcion 
			// LibroNoEncontradoException
			if (misLibros.size() == 0) {
				throw new LibroNoEncontradoException("T�TULO");
			} 
			
		// Si ocurre un error en el proceso capturamos la Excepci�n
		} catch (Exception e) {
			
			// Informamos al usuario de que se ha producido un error durante el proceso
			mensajeError("\nSE HA PRODUCIDO UN ERROR AL INTENTAR LEER LOS REGISTROS DE LA BASE DE DATOS.\nPulse INTRO para CONTINUAR.", e);
			
			// Imprimimos la cola de error.
			e.printStackTrace();
			
			// Como no se ha podido leer la base de datos no se ha podido crear el
			// List de objetos Libro, devolvemos null
			misLibros = null;			

		}	
		
		return misLibros;
		
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: modificarPrecio()
	 * Argumentos: String, Double
	 * Devuelve: Boolean
	 * Descripci�n: Este m�todo se encarga de gestionar los procesos necesarios
	 * para filtar de la base de datos el registro cuyo ISBN coincida con el String que
	 * se pasa como par�metro y modificar el precio que aparece en el registro
	 * por el Double que se pasa como par�metro.
	 * En caso de que el String no coincida con el ISBN de ning�n registro en la base de 
	 * datos se lanzar� la excepci�n LibroNoEncontradoException.
	 */

	@Override
	public boolean modificarPrecio(String isbn, double precio) throws LibroNoEncontradoException {

		// Variable de retorno
		boolean salida = true;
		
		try {
			
			// Definimos la variable Libro
			Libro libro;
			
			// Comprobamos que el isbn se encuentra en la BDD
			Query miQuery = session.createQuery("FROM Libro l WHERE l.isbn = '" + isbn + "'");
			List<Libro> miLibro = miQuery.list();
			
			// Si el tama�o del List devuelto es 0 lanzamos la excepcion
			// LibroNoEncontradoException
			if (miLibro.size() == 0) {
				throw new LibroNoEncontradoException("ISBN");
			}
			// Si no, recuperamos el libro devuelto
			else {
				libro = miLibro.get(0);
			}
			
			// Cambiamos el valor del atributo precio al nuevo precio
			libro.setPrecio(precio);
			
			// Actualizamos los atributos de libro en la BDD
			session.update(libro);
		
		// Si ocurre un error en el proceso capturamos la Excepci�n
		} catch (Exception e) {
			
			// Informamos al usuario de que se ha producido un error durante el proceso
			mensajeError("\nSE HA PRODUCIDO UN ERROR AL INTENTAR MODIFICAR LOS DATOS EN LA BASE DE DATOS.\nPulse INTRO para CONTINUAR.", e);
			
			//Imprimos la pila de error.
			e.printStackTrace();
			
			// Como no se ha realizado el registro del libro en la base de datos devolvemos
			// false.
			salida = false;
			
		}
		
		return salida;
	}
}

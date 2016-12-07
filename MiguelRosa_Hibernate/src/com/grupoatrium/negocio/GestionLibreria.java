package com.grupoatrium.negocio;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Transaction;

import com.grupoatrium.cliente.OpcionIncorrectaException;
import com.grupoatrium.cliente.SeleccionModo;
import com.grupoatrium.modelo.Autor;
import com.grupoatrium.modelo.Direccion;
import com.grupoatrium.modelo.Editorial;
import com.grupoatrium.modelo.Libro;
import com.grupoatrium.modelo.LibroNoEncontradoException;
import com.grupoatrium.persistencia.LibrosDAO;

/*****************************************************************************************
 * Nombre de Clase : GestionLibreria													 *
 * Autor: Miguel Rosa																	 *
 * Fecha: 07 - Noviembre - 2016															 *
 * Versi�n: 1.0																			 *
 * Descripci�n: Se escriben unas breves instrucciones por consola para que el			 *
 * usuario seleccione:																	 *
 * 																						 *
 * 		1 Dar de ALTA un libro en la Base de Datos.										 *
 *		2 ELIMINAR un libro de la Base de Datos.										 *
 *		3 Consultar TODOS los libros de la Base de Datos (por defecto).					 *
 *		4 Consultar libro por ISBN.														 *
 *		5 Consultar libro por TITULO.													 *
 *		6 Modificar PRECIO de un libro.													 *
 *		7 SALIR																			 *
 * 																						 *
 * Si la opci�n introducida por el usuario no corresponde a ninguna de las				 *
 * anteriores se repetir� el mismo mensaje de selecci�n hasta que la opci�n 			 *
 * introducida coincida.																 *
 * 																						 *
 * En funci�n de la selecci�n del usuario, se lanzar� el m�todo correspondiente			 *
 * as� como nuevas instrucciones por consola solicitando los datos que fueran			 *
 * necesarios.																			 *
 *****************************************************************************************/

public class GestionLibreria implements ItfzGestionLibreria{
	
	/*************************************************************************************
	 * ATRIBUTOS																		 *
	 *************************************************************************************/
	private String seleccion;	// Atributo que se encarga de almacenar la opci�n del
								// usuario
	
	private boolean salir;		// Atributo que se encarga de la salida del programa
	
	/*************************************************************************************
	 * CONSTRUCTORES																	 *
	 *************************************************************************************/
	public GestionLibreria() {
		
		salir = false;
		
		while (!salir) {
			// Impresi�n en consola de unas breves instrucciones.
			System.out.println();
			System.out.println("Seleccione una opci�n:\n");
			System.out.println("\t1 Dar de ALTA un libro en la Base de Datos.");
			System.out.println("\t2 ELIMINAR un libro de la Base de Datos.");
			System.out.println("\t3 Consultar TODOS los libros de la Base de Datos (por defecto).");
			System.out.println("\t4 Consultar libro por ISBN.");
			System.out.println("\t5 Consultar libro por TITULO.");
			System.out.println("\t6 Modificar PRECIO de un libro.");
			System.out.println();
			System.out.println("\t7 ATRAS");
			System.out.println();
			System.out.println("\t8 SALIR");
			System.out.println();
			System.out.println("Puede seleccionar el n�mero o escribir la palabra clave.");
			
			// Se crea una instancia de la Scanner para recoger la opci�n del usuario
			Scanner sc = new Scanner(System.in);
			
			// Se asigna la opci�n del usuario al atributo "seleccion". El String devuelto
			// por el usuario se convierte a min�sculas antes de almacenarlo para que la
			// comprobaci�n sea independiente de las may�sculas o min�sculas.
			seleccion = sc.nextLine().toLowerCase();
			// Se invoca al m�todo comprobar.
			try {
				comprobar();
			} catch (Exception e) {
				e.printStackTrace();
				new GestionLibreria();
			}
		}
			
	}
	
	/*************************************************************************************
	 * METODOS																			 *
	 *************************************************************************************/
	
	/* Nombre: comprobar()
	 * Argumentos: Ninguno
	 * Devuelve: Nada
	 * Descripci�n: 
	 * Se compara el valor del atributo "seleccion" introducido por el usuario con 
	 * los siguientes valores:
	 * 
	 * 		� "1" � "alta"  ->  Se lanza el m�todo altaLibro(Libro libro)
	 * 		� "2" � "eliminar" -> Se lanza el m�todo eliminarLibro(int id)
	 * 		� "3" � "todo" � "todos" � "" -> Se lanza el m�todo consultarTodos()
	 * 		� "4" � "isbn" -> Se lanza el m�todo consultarISBN(String isbn)
	 * 		� "5" � "titulo" � "t�tulo" -> Se lanza el m�todo 
	 * 									   consultarTitulo(String titulo)
	 * 		� "6" � "precio" -> Se lanza el m�todo 
	 * 							modificarPrecio(String isbn, double precio)
	 * 		� "7" � "atr�s" � "atras" -> Se vuelve al men� anterior de 
	 * 									 Selecci�n de Modo
	 * 		
	 * Si la opci�n introducida por el usuario no corresponde a ninguna de las
	 * anteriores se repetir� el mismo mensaje de selecci�n hasta que la
	 * opci�n introducida coincida.
	 */
	
	public void comprobar() throws OpcionIncorrectaException{
		
		// Opci�n 1. Dar de Alta un Libro
		
		if (seleccion.equals("1") || (seleccion.equals("alta"))) {
			
			// Se informa al usuario de la opci�n escogida.
			informacionYEspera("SE VA A DAR DE ALTA UN LIBRO. Pulse INTRO para continuar.");
			
			// Se crea una instancia de la clase Libro. El constructor de esta clase
			// proporciona instrucciones al usuario para introducir los datos.
			Libro nuevoLibro = crearLibroUsuario();
			
			// Se lanza el m�todo altaLibro(Libro libro). El argumento de este m�todo
			// es la instancia de la Clase Libro que creamos en el paso anterior.
			if (altaLibro(nuevoLibro)) {
				informacionYEspera("EL LIBRO SE HA DADO DE ALTA EN LA BASE DE DATOS CON EL ID: " +
									nuevoLibro.getIDLibro() + ".\nPulse INTRO para continuar.");
			
			// Si altaLibro() devuelve false quiere decir que algo ha fallado y el libro no
			// se ha dado de alta.
			} else {
				informacionYEspera("SE LE REDIRECCIONAR� AL MEN� PRINCIPAL. Pulse INTRO para continuar.");
			}
		}
		
		// Opci�n 2. Eliminar un Libro
		
		else if (seleccion.equals("2") || (seleccion.equals("eliminar"))) {
			
			// Se informa al usuario de la opci�n escogida.
			informacionYEspera("SE VA A ELIMINAR UN LIBRO. Pulse INTRO para continuar.");
			
			// Se informa al usuario para que introduca el ID del libro que desea 
			// eliminar de la base de datos.
			System.out.println();
			System.out.println("POR FAVOR, INTRODUZCA EL ID DEL LIBRO QUE DESEA ELIMINAR y pulse INTRO.");
			
			// Se espera a que el usuario introduzca el ID del libro. El ID se almacena
			// en una variable String temporal que luego es convertida a entero y 
			// almacenada en la variable int id. Debido a que el usuario puede 
			//introducir un valor no  convertible a entero se crea un bucle do/while 
			// que capture la posible excepci�n NumberFormatException y se solicita un 
			// nuevo valor en caso de que esto ocurra.
			
			boolean error; //Esta es la variable que controla la salida del 
						   //bucle do/while

			do {
				error = false;
				try {
					Scanner sc = new Scanner(System.in);
					String temporal = sc.nextLine();
					int id = Integer.parseInt(temporal);
					
					// Se lanza el m�todo eliminarLibro(int id), el ID que se pasa 
					// por argumento es el id introducido por el usuario.
					if (eliminarLibro(id)) {
						informacionYEspera("EL LIBRO CON ID " + id + ", SE HA ELIMINADO DE LA BASE DE DATOS.\nPulse INTRO para continuar.");
					
					// Si altaLibro() devuelve false quiere decir que algo ha fallado y el libro no
					// se ha eliminado.
					} else {
						informacionYEspera("SE LE REDIRECCIONAR� AL MEN� PRINCIPAL. Pulse INTRO para continuar.");
					}
					
					
				} catch (NumberFormatException e) {
					System.out.println("EL DATO INTRODUCIDO NO SE RECONOCE. Introduzca un ID correcto.");
					error = true;
				}
			} while (error);
		}
		
		// Opci�n 3. Mostrar los libros de la Base de Datos
		
		else if (seleccion.equals("3") || seleccion.equals("todo") || 
				seleccion.equals("todos") || seleccion.equals("")) {
			
			// Se informa al usuario de la opci�n escogida.
			informacionYEspera("SE VAN A MOSTRAR TODOS LOS LIBROS DE LA BASE DE DATOS. Pulse INTRO para continuar.");
			
			// Se lanza el m�todo consultarTodos() y se almacena en un List;
			List<Libro> librosBD = consultarTodos();
			
			
			// Recorremos el List e imprimimos los atributos de todos los objetos que lo componen.

			for (Libro i: librosBD) {
				System.out.println("ID: " + i.getIDLibro());
				System.out.println("T�TULO: " + i.getTitulo());
				System.out.println("AUTORES: ");
				Set<Autor> autores = i.getAutores();
				for (Autor a: autores) {
					System.out.println("\tNOMBRE: " + a.getNombre());
					System.out.println("\tNACIONALIDAD: " + a.getNombre());
					System.out.println("\tCOMENTARIOS: " + a.getComentarios());
					System.out.println();
				}
				System.out.println("EDITORIAL: " + i.getEditorial().getNombre());
				System.out.println("ISBN: " + i.getIsbn());
				System.out.println("PUBLICACION: " + i.getPublicacion());
				System.out.println("PRECIO: " + i.getPrecio());
				System.out.println("DESCRIPCI�N: " + i.getDescripcion());
				System.out.println();
				System.out.println("--------------------------------------------------");
				System.out.println();
			}
			
		} 
		
		// Opci�n 4. Mostrar el libro con el ISBN indicado por el usuario
		
		else if (seleccion.equals("4") || seleccion.equals("isbn")) {
			
			// Se informa al usuario de la opci�n escogida.
			informacionYEspera("SE VA A REALIZAR UNA B�SQUEDA POR ISBN EN LA BASE DE DATOS. Pulse INTRO para continuar.");
			
			// Se informa al usuario para que introduca el ISBN del libro que desea
			// localizar de la base de datos.
			System.out.println();
			System.out.println("POR FAVOR, INTRODUZCA EL ISBN DEL LIBRO QUE DESEA LOCALIZAR y pulse INTRO.");
			
			// Se crea una instancia de la clase Scanner para recoger el dato
			// introducido por el usuario y se almacena en el String isbn. 
			Scanner sc = new Scanner(System.in);
			String isbn = sc.nextLine();
			
			// Se lanza el m�todo consultarISBN(String isbn) que nos debe devolver el
			// libro con ese isbn
			Libro miLibro = consultarISBN(isbn);
			
			// Imprimimos todos los atributos del objeto Libro devuelto en el paso anterior
			System.out.println("ID: " + miLibro.getIDLibro());
			System.out.println("T�TULO: " + miLibro.getTitulo());
			System.out.println("AUTORES: ");
			Set<Autor> autores = miLibro.getAutores();
			for (Autor a: autores) {
				System.out.println("\tNOMBRE: " + a.getNombre());
				System.out.println("\tNACIONALIDAD: " + a.getNombre());
				System.out.println("\tCOMENTARIOS: " + a.getComentarios());
				System.out.println();
			}
			System.out.println("EDITORIAL: " + miLibro.getEditorial().getNombre());
			System.out.println("ISBN: " + miLibro.getIsbn());
			System.out.println("PUBLICACION: " + miLibro.getPublicacion());
			System.out.println("PRECIO: " + miLibro.getPrecio());
			System.out.println("DESCRIPCI�N: " + miLibro.getDescripcion());
			System.out.println();
			System.out.println("--------------------------------------------------");
			System.out.println();			
		} 
		
		// Opci�n 5. Mostrar el/los libro/s con el t�tulo similar al introducido por el usuario
		
		else if (seleccion.equals("5") || seleccion.equals("titulo") ||
				   seleccion.equals("t�tulo")) {
			
			// Se informa al usuario de la opci�n escogida.
			informacionYEspera("SE VA A REALIZAR UNA B�SQUEDA POR TITULO EN LA BASE DE DATOS. Pulse INTRO para continuar.");
			
			// Se informa al usuario para que introduca el t�tulo del libro que desea
			// localizar de la base de datos.
			System.out.println();
			System.out.println("POR FAVOR, INTRODUZCA EL T�TULO DEL LIBRO QUE DESEA LOCALIZAR y pulse INTRO.");
			
			// Se crea una instancia de la clase Scanner para recoger el dato
			// introducido por el usuario y se almacena en el String titulo. 
			Scanner sc = new Scanner(System.in);
			String titulo = sc.nextLine();

			
			// Se lanza el m�todo consultarTitulo() y se almacena en un List;
			List<Libro> librosBD = consultarTitulo(titulo);
			
			// Recorremos el List e imprimimos los atributos de todos los objetos que lo componen.
			for (Libro i: librosBD) {
				System.out.println("ID: " + i.getIDLibro());
				System.out.println("T�TULO: " + i.getTitulo());
				System.out.println("AUTORES: ");
				Set<Autor> autores = i.getAutores();
				for (Autor a: autores) {
					System.out.println("\tNOMBRE: " + a.getNombre());
					System.out.println("\tNACIONALIDAD: " + a.getNombre());
					System.out.println("\tCOMENTARIOS: " + a.getComentarios());
					System.out.println();
				}
				System.out.println("EDITORIAL: " + i.getEditorial().getNombre());
				System.out.println("ISBN: " + i.getIsbn());
				System.out.println("PUBLICACION: " + i.getPublicacion());
				System.out.println("PRECIO: " + i.getPrecio());
				System.out.println("DESCRIPCI�N: " + i.getDescripcion());
				System.out.println();
				System.out.println("--------------------------------------------------");
				System.out.println();
			}
			
			
		} 
		
		// Opci�n 6. Cambiar el precio de un libro
		
		else if (seleccion.equals("6") || seleccion.equals("precio")) {
			
			// Se informa al usuario de la opci�n escogida.
			informacionYEspera("SE VA A CAMBIAR EL PRECIO DE UN LIBRO EN LA BASE DE DATOS. Pulse INTRO para continuar.");
			
			// Se informa al usuario para que introduca el ISBN del libro que desea
			// localizar de la base de datos.
			System.out.println();
			System.out.println("POR FAVOR, INTRODUZCA EL ISBN DEL LIBRO AL QUE DESEA CAMBIAR EL PRECIO y pulse INTRO.");
			
			// Se crea una instancia de la clase Scanner para recoger el dato
			// introducido por el usuario y se almacena en el String isbn. 
			Scanner sc = new Scanner(System.in);
			String isbn = sc.nextLine();
			
			// Se informa al usuario para que introduca el nuevo precio del libro
			// al que acaba de introducir el ISBN.
			System.out.println();
			System.out.println("POR FAVOR, INTRODUZCA EL NUEVO PRECIO PARA EL LIBRO CON ISBN " + isbn + "\ny pulse INTRO.");
			
			// Se espera a que el usuario introduzca el PRECIO del libro, �ste se almacena
			// en una variable String temporal que luego es convertida a Double y 
			// almacenada en la variable double precio. Debido a que el usuario puede 
			//introducir un valor no  convertible a double se crea un bucle do/while 
			// que capture la posible excepci�n NumberFormatException y se solicita un 
			// nuevo valor en caso de que esto ocurra.
			
			boolean error; 	//Esta es la variable que controla la salida del 
			 				//bucle do/while
			do {
				error = false;
				try {
					sc = new Scanner(System.in);
					String temporal = sc.nextLine();
					double precio = Double.parseDouble(temporal);
					
					// Se lanza el m�todo modificarPrecio(String isbn, double precio),
					// los argumentos isbn y precio son datos introducidos anteriormente
					// por el usuario.
					if (modificarPrecio(isbn, precio)) {
						informacionYEspera("SE HA MODIFICADO EL PRECIO DEL LIBRO CON ISBN " +
											isbn + " A " + precio + ".\nPulse INTRO para continuar.");
						
					// Si modificarPrecio() devuelve false quiere decir que algo ha fallado y el precio del
					// libro no se ha modificado.
					} else {
						informacionYEspera("SE LE REDIRECCIONAR� AL MEN� PRINCIPAL. Pulse INTRO para continuar.");
					}
					
				} catch (NumberFormatException e) {
					System.out.println("EL DATO INTRODUCIDO NO SE RECONOCE COMO PRECIO. Introduzca un PRECIO correcto.");
					error = true;
				}
			} while (error);

		} 
		
		// Opci�n 7. Volver al men� de selecci�n de modo gr�fico.
		
		else if (seleccion.equals("7") || seleccion.equals("atr�s") ||
				   seleccion.equals("atras")) {
			
			// Se informa al usuario de la opci�n escogida.
			informacionYEspera("SE LE VA A REDIRIGIR AL MEN� ANTERIOR. Pulse INTRO para continuar.");
			
			// Se vuelve al men� inicial creando una nueva instancia de la Clase SeleccionModo.
			new SeleccionModo();
			
		}
		
		// Opci�n 8. Salir de la aplicaci�n.
		
		else if (seleccion.equals("8") || seleccion.equals("salir")) {
			
			// Se informa al usuario de la opci�n escogida.
			informacionYEspera("SE VA A SALIR DE LA APLICACI�N. MUCHAS GRACIAS POR SU USO. Pulse INTRO para continuar.");
			
			// Se sale del bucle del constructor poniendo la variable salir a true 
			salir = true;
			
			
		}
		
		// Se lanza la excepci�n OpcionIncorrecta cuando la opci�n introducida por el
		// usuario no corresponde con ninguna de las anteriores
		
		else throw new OpcionIncorrectaException();
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: informacionYEspera()
	 * Argumentos: String
	 * Devuelve: void
	 * Descripci�n: Se detecta durante la realizaci�n del programa que las
	 * instrucciones de este m�todo se repiten considerablemente.
	 * Se crea este m�todo para simplificar y clarificar el c�digo. El String que
	 * se pasa como par�metro se muestra al usuario para informarle de alg�n evento 
	 * o solicitarle informaci�n.
	 */
	
	public String informacionYEspera(String str) {
		
		// Declaramos una variable String que almacenar� el dato del usuario
		String cadena;
		
		// Se informa al usuario o se le solicita un dato.
		System.out.println();
		System.out.println(str);
		
		// Se devuelven los datos escritos por el usuario.
		Scanner sc = new Scanner(System.in);
		cadena = sc.nextLine();
		
		// Devolvemos el dato del usuario
		return cadena;
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: crearLibroUsuario()
	 * Argumentos: Ninguno
	 * Devuelve: Libro
	 * Descripci�n: Este m�todo se encarga de la solicitud al usuario de los datos
	 * necesarios para crear un objeto de la clase Libro.
	 */
	
	public Libro crearLibroUsuario() {
		
		// Se declaran las variables que almacenar�n los datos asignando valores por defecto.
		
		String temporal = "";			// Usamos esta variable para almacenar temporalmente los
										// datos introducidos por el usuario que requieran ser
										// convertidos a otro tipo, como en el caso de las
										// variables publicacion y precio.
		
		boolean error = false; 			// Esta es la variable que controla la salida de los 
										// bucles do/while para la conversi�n de datos.
		
		Set<Autor> autores = null; 		// Nombre del autor
		String descripcion = ""; 		// Sinopsis del Libro
		Editorial editorial = null;		// Editorial que lo ha publicado
		String isbn = "";				// C�digo ISBN
		double precio = 0.0;			// Precio en euros
		int publicacion = 0;			// A�o de publicaci�n
		String titulo = "";				// T�tulo del libro
		
		// Se solicitan los datos al usuario.
		
		// titulo
		titulo = informacionYEspera("INTRODUZCA EL T�TULO DEL LIBRO");
		
		// autores
		autores = crearAutores();
		
		// editorial
		editorial = crearEditorial();
		
		// isbn
		isbn = informacionYEspera("INTRODUZCA EL ISBN DEL LIBRO");
		
		// publicacion. Al tratarse publicacion de una variable de tipo int hay que convertir
		// el dato del usuario de String a Integer, como este proceso puede lanzar una
		// excepci�n, la capturamos y solicitamos al usuario que repita el proceso hasta que
		// que el dato introducido sea correcto.
			
		do {
			error = false;
			try {
				temporal = informacionYEspera("INTRODUZCA EL A�O DE PUBLICACION DEL LIBRO");
				publicacion = Integer.parseInt(temporal);
			} catch (NumberFormatException e) {
				System.out.println("EL DATO INTRODUCIDO NO SE RECONOCE COMO A�O. Introduzca un A�O correcto.");
				error = true;
			}
		} while (error);
		
		// precio. Al tratarse precio de una variable de tipo double hay que convertir
		// el dato del usuario de String a Double, como este proceso puede lanzar una
		// excepci�n, la capturamos y solicitamos al usuario que repita el proceso hasta que
		// que el dato introducido sea correcto.
		
		do {
			error = false;
			try {
				temporal = informacionYEspera("INTRODUZCA EL PRECIO DEL LIBRO");
				precio = Double.parseDouble(temporal);
			} catch (NumberFormatException e) {
				System.out.println("EL DATO INTRODUCIDO NO SE RECONOCE COMO PRECIO. Introduzca un PRECIO correcto.");
				error = true;
			}
		} while (error);
	
		// descripcion		
		descripcion = informacionYEspera("INTRODUZCA UNA DESCRIPCION DEL LIBRO");
		
		Libro miLibro = new Libro(titulo, autores, editorial, isbn, publicacion, precio, descripcion);
		
		return miLibro;
		
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: crearAutores()
	 * Argumentos: Ninguno
	 * Devuelve: Set<Autor>
	 * Descripci�n: Este m�todo se encarga de la solicitud al usuario de los datos
	 * necesarios para crear un conjunto (Set) de la clase Autor para el libro que
	 * se est� creando.
	 */
	
	public Set<Autor> crearAutores() {
		boolean error;
		String temporal;
		Set<Autor> autores = new HashSet<Autor>();
		
		
		int numeroAutores = 0;
		do {
			error = false;
			try {
				temporal = informacionYEspera("�CUANTOS AUTORES HAN INTERVENIDO EN EL LIBRO?\n");
				numeroAutores = Integer.parseInt(temporal);
			} catch (NumberFormatException e) {
				System.out.println("EL DATO INTRODUCIDO NO SE RECONOCE COMO N�MERO ENTERO. Introduzca un N�MERO correcto.");
				error = true;
			}
		} while (error);
		
		for (int n = 0; n < numeroAutores; n++) {
			String nombre = informacionYEspera("�Nombre del AUTOR " + (n + 1) + "?\n");
			String nacionalidad = informacionYEspera("�Nacionalidad del AUTOR " + (n + 1) + "?\n");
			String comentarios = informacionYEspera("�Alg�n comentario relevante del AUTOR " + (n + 1) + "?\n");
			Autor autor = new Autor(nombre, nacionalidad, comentarios);
			autores.add(autor);
		}
		return autores;
		
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: crearEditorial()
	 * Argumentos: Ninguno
	 * Devuelve: Editorial
	 * Descripci�n: Este m�todo se encarga de la solicitud al usuario de los datos
	 * necesarios para crear un objeto de la clase Editorial para el libro que
	 * se est� creando.
	 */
	
	public Editorial crearEditorial() {
		boolean error;
		String temporal;
		
		String nombre = informacionYEspera("�NOMBRE DE LA EDITORIAL?\n");
		
		String nif = informacionYEspera("�NIF DE LA EDITORIAL?\n");
		
		String nombreCalle = informacionYEspera("�NOMBRE DE LA CALLE?\n");

		int numeroCalle = 0;

		
		do {
			error = false;
			try {
				temporal = informacionYEspera("�N�MERO?\n");
				numeroCalle = Integer.parseInt(temporal);
			} catch (NumberFormatException e) {
				System.out.println("EL DATO INTRODUCIDO NO SE RECONOCE COMO N�MERO ENTERO. Introduzca un N�MERO correcto.");
				error = true;
			}
		} while (error);
		
		String poblacion = informacionYEspera("�POBLACI�N?\n");
		
		int cp = 0;
		
		do {
			error = false;
			try {
				temporal = informacionYEspera("�C�DIGO POSTAL?\n");
				cp = Integer.parseInt(temporal);
			} catch (NumberFormatException e) {
				System.out.println("EL DATO INTRODUCIDO NO SE RECONOCE COMO N�MERO ENTERO. Introduzca un N�MERO correcto.");
				error = true;
			}
		} while (error);
		
		String provincia = informacionYEspera("�PROVINCIA?\n");
		
		Direccion direccion = new Direccion(nombreCalle, numeroCalle, poblacion, cp, provincia);
		
		return new Editorial(nombre, nif, direccion);
	}
	
	
	
	/*************************************************************************************
	 * METODOS SOBREESCRITOS INTERFAZ ItfzGestionLibreria								 *
	 *************************************************************************************/
	
	/* Nombre: altaLibro()
	 * Argumentos: Libro
	 * Devuelve: Boolean
	 * Descripci�n: Crea una instancia de la clase LibrosDAO y lanza su m�todo
	 * altaLibro() devolviendo su resultado e independizando la clase GestionLibreria de
	 * la conexi�n a la base de datos
	 * El par�metro que recibe este m�todo es el mismo que se pasa al m�todo hom�nimo de
	 * la clase LibrosDAO.
	 */
	
	@Override
	public boolean altaLibro(Libro libro) {
		boolean salida;
		LibrosDAO conexionBD = new LibrosDAO();
		
		Transaction tx = conexionBD.getSesion().getTransaction();
		tx.begin();
		salida = conexionBD.altaLibro(libro);
		tx.commit();
		
		conexionBD.getSesion().close();
		conexionBD.getSf().close();
		
		return salida;
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: eliminarLibro()
	 * Argumentos: Integer
	 * Devuelve: Boolean
	 * Descripci�n: Crea una instancia de la clase LibrosDAO y lanza su m�todo
	 * eliminarLibro() devolviendo su resultado e independizando la clase GestionLibreria de
	 * la conexi�n a la base de datos.
	 * El par�metro que recibe este m�todo es el mismo que se pasa al m�todo hom�nimo de
	 * la clase LibrosDAO. 
	 */

	@Override
	public boolean eliminarLibro(int id) {
		boolean salida;
		LibrosDAO conexionBD = new LibrosDAO();
		
		try {
			Transaction tx = conexionBD.getSesion().getTransaction();
			tx.begin();
			salida = conexionBD.eliminarLibro(id);
			tx.commit();	
		} catch (LibroNoEncontradoException e) {
			e.mensajeConsola("ID");
			salida = false;
		}
		
		conexionBD.getSesion().close();
		conexionBD.getSf().close();
		
		return salida;
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: consultarTodos()
	 * Argumentos: Ninguno
	 * Devuelve: List
	 * Descripci�n: Crea una instancia de la clase LibrosDAO y lanza su m�todo
	 * consultarTodos() devolviendo su resultado e independizando la clase GestionLibreria 
	 * de la conexi�n a la base de datos.
	 * El par�metro que recibe este m�todo es el mismo que se pasa al m�todo hom�nimo de
	 * la clase LibrosDAO. 
	 */

	@Override
	public List<Libro> consultarTodos() {
		
		List<Libro> libros;
		LibrosDAO conexionBD = new LibrosDAO();
		
		libros = conexionBD.consultarTodos();
		
		conexionBD.getSesion().close();
		conexionBD.getSf().close();
		
		return libros;
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: consultarISBN()
	 * Argumentos: String
	 * Devuelve: Libro
	 * Descripci�n: Crea una instancia de la clase LibrosDAO y lanza su m�todo
	 * consultarISBN() devolviendo su resultado e independizando la clase GestionLibreria 
	 * de la conexi�n a la base de datos.
	 * El par�metro que recibe este m�todo es el mismo que se pasa al m�todo hom�nimo de
	 * la clase LibrosDAO. 
	 */

	@Override
	public Libro consultarISBN(String isbn) {
		
		Libro libro;
		LibrosDAO conexionBD = new LibrosDAO();
		
		try {
			libro = conexionBD.consultarISBN(isbn);
		} catch (LibroNoEncontradoException e) {
			e.mensajeConsola("ISBN");
			libro = null;
		}
		
		conexionBD.getSesion().close();
		conexionBD.getSf().close();
		
		return libro;
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: consultarTitulo()
	 * Argumentos: String
	 * Devuelve: List
	 * Descripci�n: Crea una instancia de la clase LibrosDAO y lanza su m�todo
	 * consultarTitulo() devolviendo su resultado e independizando la clase GestionLibreria 
	 * de la conexi�n a la base de datos.
	 * El par�metro que recibe este m�todo es el mismo que se pasa al m�todo hom�nimo de
	 * la clase LibrosDAO. 
	 */

	@Override
	public List<Libro> consultarTitulo(String titulo) {
		
		List<Libro> libros;
		LibrosDAO conexionBD = new LibrosDAO();
		
		try {
			libros = conexionBD.consultarTitulo(titulo);
		} catch (LibroNoEncontradoException e) {
			e.mensajeConsola("T�TULO");
			libros = null;
		}
		
		conexionBD.getSesion().close();
		conexionBD.getSf().close();
		
		return libros;
	}
	
	/*-----------------------------------------------------------------------------------*/
	
	/* Nombre: modificarPrecio()
	 * Argumentos: String, Double
	 * Devuelve: Boolean
	 * Descripci�n: Crea una instancia de la clase LibrosDAO y lanza su m�todo
	 * modificarPrecio() devolviendo su resultado e independizando la clase GestionLibreria 
	 * de la conexi�n a la base de datos.
	 * Los par�metros que recibe este m�todo son los mismos que se pasan al m�todo hom�nimo
	 * de la clase LibrosDAO. 
	 */

	@Override
	public boolean modificarPrecio(String isbn, double precio) {
		
		boolean salida;
		LibrosDAO conexionBD = new LibrosDAO();
		
		try {
			Transaction tx = conexionBD.getSesion().getTransaction();
			tx.begin();
			salida = conexionBD.modificarPrecio(isbn, precio);
			tx.commit();
		} catch (LibroNoEncontradoException e) {
			e.mensajeConsola("ISBN");
			salida = false;
		}
		
		conexionBD.getSesion().close();
		conexionBD.getSf().close();
		
		return salida;
	}
	
}

/*************************************************************************************
 * METODOS INNECESARIOS																 *
 *************************************************************************************/

/* Nombre: formateadoISBN()
 * Argumentos: String
 * Devuelve: String
 * Descripci�n: Ya que el ISBN es un n�mero complejo que en ocasiones viene divido
 * por diferentes bloques num�ricos separados por guiones o espacios, este m�todo
 * elimina los guines y espacios qued�ndose con un �nico bloque num�rico.
 * Este m�todo est� pensado �nicamente para prop�sitos de b�squeda y comparaci�n,
 * por lo que los datos introducidos en la Base de Datos deben intentar pasarse 
 * con el formato original de guiones o espacios.
 * Adem�s, todos aquellos caracteres que no sean num�ricos ser�n eliminados. 
 */

 /*

public String formateadoISBN(String str) {
	// Todos los caracteres num�ricos
	char[] comparacion = {'0','1', '2','3','4','5','6','7','8','9'};
	// La cadena pasada como argumento se pasa a un array de Character
	char[] array = str.toCharArray();
	// Se crea un ArrayList vac�o para ir almacenando caracteres
	List<Character> lista = new ArrayList<Character>();
	
	// Se recorre la cadena de ISBN car�cter a car�cter, en caso de que sea
	// un n�mero, �ste se a�ade al Arraylist y se contin�a con el siguiente
	// car�cter.
	for (int i = 0; i < array.length; i++) {
		for(int j = 0; j < comparacion.length; j++) {
			if (array[i] == comparacion[j]) {
				lista.add(array[i]);
				break;
			}
		}
	}
	// Se devuelve el contenido de la lista de Character como un String
	return lista.toString(); 
}

 */

package com.grupoatrium.negociografico;

import javax.swing.JFrame;

/*****************************************************************************
 * Nombre de Clase : Main 
 * Autor: Miguel Rosa 
 * Fecha: 12 - Noviembre - 2016 
 * Versi�n: 1.0 
 * Descripci�n: 
 * Esta clase se encarga de la creaci�n de la ventana (JFrame) que contendr�
 * todos los elementos gr�ficos
 *****************************************************************************/

public class EntornoGrafico {

	/*************************************************************************
	 * CONSTRUCTORES
	 *************************************************************************/

	public EntornoGrafico() {
		// Creamos la ventana con el t�tulo "Gesti�n Librer�a"
		JFrame miVentana = new JFrame("Gesti�n Librer�a");

		// Cuando se cierre la ventana el programa terminar�.
		miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// A�adimos una instancia del JPanel LaminaPrincipal a nuestro JFrame
		miVentana.add(new LaminaPrincipal());

		// Hacemos que los bordes de nuestra ventana se ajusten a los del JFrame
		// que
		// a�adimos en el paso anterior
		miVentana.pack();

		// Hacemos que nuestra ventana aparezca en el centro de la pantalla
		miVentana.setLocationRelativeTo(null);

		// Impedimos que los bordes de la ventana se puedan redimensionar
		miVentana.setResizable(false);

		// Hacemos la ventana visible
		miVentana.setVisible(true);

	}

}

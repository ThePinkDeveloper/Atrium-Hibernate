package com.grupoatrium.negociografico;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.grupoatrium.modelo.Libro;
import com.grupoatrium.modelo.LibroNoEncontradoException;
import com.grupoatrium.persistencia.LibrosDAO;

/******************************************************************************
 * Nombre de Clase : LaminaConsultarISBN
 * Autor: Miguel Rosa 
 * Fecha: 15 - Noviembre - 2016 
 * Versi�n: 1.0 
 * Hereda: JPanel 
 * Implementa: ActionListener
 * Descripcion: 
 * LaminaConsultarISBN es la clase que crea el JPanel que tiene todos los
 * componentes para poder mostrar los datos de un libro de la base de datos 
 * seg�n el �c�digo ISBN introducido por el usuario. No se le asigna ning�n 
 * layout para poder posicionar los elementos en sus coordenadas X e Y. 
 * Cuando se presiona el bot�n buscar, se recoge el isbn introducido en el 
 * JTextField y se lanza el m�todo consultarISBN() de la clase LibrosDAO
 * mostrando los datos recibidos en el JTextArea habilitado para ello.
 ******************************************************************************/

public class LaminaConsultarISBN extends JPanel implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
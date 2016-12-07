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
 * Nombre de Clase : LaminaConsultarTitulo
 * Autor: Miguel Rosa 
 * Fecha: 15 - Noviembre - 2016 
 * Versión: 1.0 
 * Hereda: JPanel 
 * Implementa: ActionListener
 * Descripcion: 
 * LaminaConsultarTitulo es la clase que crea el JPanel que tiene todos los
 * componentes para poder mostrar los datos de un libro de la base de datos 
 * según el título introducido por el usuario. No se le asigna ningún layout
 * para poder posicionar los elementos en sus coordenadas X e Y. 
 * Cuando se presiona el botón buscar, se recoge el String introducido en el 
 * JTextField y se lanza el método consultarTitulo() de la clase LibrosDAO
 * mostrando los datos recibidos en el JTextArea habilitado para ello.
 ******************************************************************************/

public class LaminaConsultarTitulo extends JPanel implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
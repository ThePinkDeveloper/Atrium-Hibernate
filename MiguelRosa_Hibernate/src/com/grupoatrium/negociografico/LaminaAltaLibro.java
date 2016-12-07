package com.grupoatrium.negociografico;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.grupoatrium.modelo.Libro;
import com.grupoatrium.persistencia.LibrosDAO;

/******************************************************************************
 * Nombre de Clase : LaminaAltaLibro
 * Autor: Miguel Rosa 
 * Fecha: 15 - Noviembre - 2016 
 * Versión: 1.0 
 * Hereda: JPanel 
 * Implementa: ActionListener
 * Descripcion: 
 * LaminaAltaLibro es la clase que crea el JPanel que tiene todos los
 * componentes para poder dar de alta un libro en la base de datos. No se le
 * asigna ningún layout para poder posicionar los elementos en sus coordenadas
 * X e Y. Cuando se presiona el botón darDeAlta, se recogen todos los datos
 * introducidos en los JTextFields y JTextArea para crear una instancia de la
 * clase Libro y se lanza el método altaLibro() de la clase LibrosDAO dando
 * así el libro en la base de datos.
 ******************************************************************************/

public class LaminaAltaLibro extends JPanel implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

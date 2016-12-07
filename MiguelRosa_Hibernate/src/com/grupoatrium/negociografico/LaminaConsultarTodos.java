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

import com.grupoatrium.modelo.Libro;
import com.grupoatrium.persistencia.LibrosDAO;

/******************************************************************************
 * Nombre de Clase : LaminaConsultarTodos
 * Autor: Miguel Rosa 
 * Fecha: 15 - Noviembre - 2016 
 * Versión: 1.0 
 * Hereda: JPanel 
 * Implementa: ActionListener
 * Descripcion: 
 * LaminaConsultarTodos es la clase que crea el JPanel que tiene todos los
 * componentes para poder mostrar el contenido íntegro de los registros de 
 * la base de datos. No se le asigna ningún layout para poder posicionar los 
 * elementos en sus coordenadas X e Y. Cuando se presiona el botón mostrar, 
 * se lanza el método mostrarTodo() de la clase LibrosDAO y se recogen los
 * datos para mostrarlos en el JTextArea habilitado dentro de un JScrollPane
 * para que aparezca la barra de scroll.
 ******************************************************************************/

public class LaminaConsultarTodos extends JPanel implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}
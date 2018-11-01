package uniandes.isis2304.superAndes.interfazApp;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.datanucleus.enhancer.asm.Label;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRippler;

import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JList;

public class PanelCarrito extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelCarrito() {
		setLayout(null);

		setBorder ( new TitledBorder("Carrito"));
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(31, 33, 56, 16);
		add(lblCliente);
		
		JLabel lblNombreCliente = new JLabel("Nombre Cliente");
		lblNombreCliente.setBounds(99, 33, 118, 16);
		add(lblNombreCliente);
		
		JLabel lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setBounds(31, 62, 56, 16);
		add(lblSucursal);
		
		JLabel lblNombresucursal = new JLabel("NombreSucursal");
		lblNombresucursal.setBounds(99, 62, 128, 16);
		add(lblNombresucursal);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 102, 524, 185);
		panel.setBorder( new TitledBorder("Productos"));
		add(panel);
		panel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(12, 32, 500, 140);
		panel.add(list);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(439, 318, 97, 25);
		add(btnFinalizar);
		
		JButton btnAbandonar = new JButton("Abandonar");
		btnAbandonar.setBounds(330, 318, 97, 25);
		add(btnAbandonar);
		
		JLabel lblTotal = new JLabel("Total: $");
		lblTotal.setBounds(335, 289, 56, 16);
		add(lblTotal);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(480, 289, 56, 16);
		add(lblPrecio);
		
		
		

	}
}

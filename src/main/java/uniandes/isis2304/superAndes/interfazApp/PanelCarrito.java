package uniandes.isis2304.superAndes.interfazApp;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.datanucleus.enhancer.asm.Label;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRippler;

import uniandes.isis2304.superAndes.negocio.VOCarrito;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;

public class PanelCarrito extends JPanel implements ActionListener{

	private InterfazSuperAndesApp interfaz;
	
	private JLabel lblNombreCliente;
	
	private JLabel lblNombresucursal;
	
	private JLabel lblPrecio;
	
	private JButton btnAgregar;
	
	private JButton btnEliminar;
	/**
	 * Create the panel.
	 */
	public PanelCarrito(InterfazSuperAndesApp interfaz) {
		setLayout(null);
		
		this.interfaz = interfaz;

		setBorder ( new TitledBorder("Carrito"));
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(31, 33, 56, 16);
		add(lblCliente);
		
		lblNombreCliente = new JLabel("Nombre Cliente");
		lblNombreCliente.setBounds(99, 33, 118, 16);
		add(lblNombreCliente);
		
		JLabel lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setBounds(31, 62, 56, 16);
		add(lblSucursal);
		
		lblNombresucursal = new JLabel("NombreSucursal");
		lblNombresucursal.setBounds(99, 62, 128, 16);
		add(lblNombresucursal);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 102, 524, 185);
		panel.setBorder( new TitledBorder("Productos"));
		add(panel);
		panel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(12, 61, 500, 111);
		panel.add(list);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setActionCommand("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(12, 23, 79, 25);
		panel.add(btnAgregar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(103, 23, 89, 25);
		panel.add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setActionCommand("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(204, 23, 79, 25);
		panel.add(btnEliminar);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(439, 318, 97, 25);
		add(btnFinalizar);
		
		JButton btnAbandonar = new JButton("Abandonar");
		btnAbandonar.setBounds(330, 318, 97, 25);
		add(btnAbandonar);
		
		JLabel lblTotal = new JLabel("Total: $");
		lblTotal.setBounds(335, 289, 56, 16);
		add(lblTotal);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(480, 289, 56, 16);
		add(lblPrecio);
	}
	
	public void actualizarCarrito(VOCarrito carrito)
	{
		lblNombreCliente.setText(carrito.getEmail());
		lblNombresucursal.setText(carrito.getIdSucursal()+"");
		lblPrecio.setText(carrito.getPrecioTotal()+"");
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String a = e.getActionCommand();
		if(a.equals("Agregar"))
		{
			this.interfaz.agregarProductoCarrito();
		}
		else if(a.equals("Eliminar"))
		{
			this.interfaz.eliminarProductoCarrito();
		}
	}
}
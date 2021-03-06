package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.Carrito;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOAlmacenamiento;
import uniandes.isis2304.superAndes.negocio.VOCarrito;
import uniandes.isis2304.superAndes.negocio.VOClientes;
import uniandes.isis2304.superAndes.negocio.VOInfoProdCarrito;
import uniandes.isis2304.superAndes.negocio.VOInfoProdProveedor;
import uniandes.isis2304.superAndes.negocio.VOInfoProdSucursal;
import uniandes.isis2304.superAndes.negocio.VOProducto;
import uniandes.isis2304.superAndes.negocio.VOPromocion;
import uniandes.isis2304.superAndes.negocio.VOProveedor;
import uniandes.isis2304.superAndes.negocio.VORequ12;
import uniandes.isis2304.superAndes.negocio.VOSucursal;
import uniandes.isis2304.superAndes.negocio.VOVentas;

@SuppressWarnings("serial")
public class InterfazSuperAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuraci�n de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json";

	/**
	 * Ruta al archivo de configuraci�n de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociaci�n a la clase principal del negocio.
	 */
	private SuperAndes superAndes;
	
	/**
	 * Objeto carrito
	 */
	private VOCarrito carrito;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/

	/**
	 * Objeto JSON con la configuraci�n de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacci�n para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Men� de la aplicaci�n
	 */
	private JMenuBar menuBar;

	/**
	 * Panel de carrito de compras
	 */
	private PanelCarrito panelCarrito;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Construye la ventana principal de la aplicaci�n. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesApp()
	{
		guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ);
		configurarFrame();
		if(guiConfig != null)
		{
			crearMenu(guiConfig.getAsJsonArray("menuBar"));
		}

		tableConfig = openConfig("Tablas BD", CONFIG_TABLAS);
		superAndes = new SuperAndes(tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos(this);

		setLayout ( new GridLayout(2, 1));
		add(new JLabel( new ImageIcon (path)));
		JPanel panel = new JPanel();
		panel.setLayout( new GridLayout(1, 2));
		panel.add(panelDatos);

		panelCarrito = new PanelCarrito(this);
		panel.add(panelCarrito);
		add(panel);
	}

	/* ****************************************************************
	 * 			M�todos de configuraci�n de la interfaz
	 *****************************************************************/

	/**
	 * Lee datos de configuraci�n para la aplicaci�, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuraci�n deseada
	 * @param archConfig - Archivo Json que contiene la configuraci�n
	 * @return Un objeto JSON con la configuraci�n del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig(String tipo, String archConfig)
	{
		JsonObject config = null;
		try
		{
			Gson gson = new Gson();
			FileReader file = new FileReader(archConfig);
			JsonReader reader = new JsonReader(file);
			config = gson.fromJson(reader, JsonObject.class);
			log.info("Se encontr� un archivo de configuraci�n v�lido: " + tipo);
		}
		catch(Exception e)
		{
			log.info("No se encontr� un archivo de configuraci�n v�lido");
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de interfaz v�lido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}
		return config;
	}

	/**
	 * M�todo para configurar el frame principal de la aplicaci�n
	 */
	private void configurarFrame()
	{
		int alto = 0;
		int ancho = 0;
		String titulo ="";
		if (guiConfig == null)
		{
			log.info("Se aplica configuracion por defecto");
			titulo = "SuperAndes APP Default";
			alto = 3000;
			ancho = 500;
		}
		else
		{
			log.info("Se aplica configuraci�n indicada en el archivo de configuraci�n");
			titulo = guiConfig.get("title").getAsString();
			alto = guiConfig.get("frameH").getAsInt();
			ancho =guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(50, 50);
		setResizable(true);
		setBackground(Color.WHITE);

		setTitle(titulo);
		setSize(ancho, alto);
	}

	/**
	 * M�todo para crear el men� de la aplicaci�n con base em el objeto JSON le�do
	 * Genera una barra de men� y los men�s con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los men�s deseados
	 */
	private void crearMenu(JsonArray jsonMenu)
	{
		try
		{
			menuBar = new JMenuBar();
			for(JsonElement men : jsonMenu)
			{
				JsonObject jom = men.getAsJsonObject();

				String menuTitle = jom.get("menuTitle").getAsString();
				JsonArray opciones = jom.getAsJsonArray("options");

				JMenu menu = new JMenu(menuTitle);

				for(JsonElement op : opciones)
				{
					JsonObject jo = op.getAsJsonObject();
					String lb = jo.get("label").getAsString();
					if(lb.equals("separador"))
					{
						menu.addSeparator();
					}
					else
					{
						String event = jo.get("event").getAsString();

						JMenuItem mItem = new JMenuItem(lb);
						mItem.addActionListener(this);
						mItem.setActionCommand(event);

						menu.add(mItem);
					}
				}
				menuBar.add(menu);
			}
			setJMenuBar(menuBar);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			CRUD de Clientes
	 *****************************************************************/

	public void agregarCliente()
	{
		try
		{
			String emailCliente = JOptionPane.showInputDialog(this, "Email del cliente?", "Agregar Cliente", JOptionPane.QUESTION_MESSAGE);
			String nombreCliente = JOptionPane.showInputDialog(this, "Nombre del Cliente?", "Agregar Cliente", JOptionPane.QUESTION_MESSAGE);
			String documento = JOptionPane.showInputDialog(this, "Documento del cliente?", "Agregar Cliente", JOptionPane.QUESTION_MESSAGE);
			String direccion = JOptionPane.showInputDialog(this, "Direccion del Cliente?", "Agregar Cliente", JOptionPane.QUESTION_MESSAGE);
				if(emailCliente != null)
				{
					VOClientes tb = superAndes.agregarCliente(emailCliente, nombreCliente, documento, direccion);
					if(tb == null)
					{
						throw new Exception("No se pudo crear el cliente con el email: " + emailCliente);
					}
					String resultado = "En agregarCliente\n\n";
					resultado += "Cliente adicionado exitosamente:    " + emailCliente + "\n";
					resultado += "                    	   Nombre:    " + nombreCliente + "\n";
					resultado += "                    	   Documento: " + documento + "\n";
					resultado += "                    	   Direcci�n: " + direccion + "\n";
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
				}
						
						
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarClientes()
	{
		try
		{
			List<VOClientes> lista = superAndes.darVOClientes();
			String resultado = "En listarClientes";
			resultado += "\n" + listarClientes(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operacion terminada";
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	public void rfc12() throws Exception
	{
		int id = Integer.parseInt(JOptionPane.showInputDialog(this, "sucursal de las busquedas?", "Mostrar carrito", JOptionPane.QUESTION_MESSAGE));
		if(id <=0 || id >150)
		{
			throw new Exception ("No es un valor v�lido de sucursal");		
		}
		try
		{
			List<VORequ12> lista = superAndes.darVORequ12(id);
			String resultado = "En el requerimiento 12";
			resultado += "\n" + listarRequ12(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operacion terminada";
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	public void rfc10() throws Exception
	{
		int id = Integer.parseInt(JOptionPane.showInputDialog(this, "sucursal de las busquedas?", "Sucursal?", JOptionPane.QUESTION_MESSAGE));
		String restrincciones = JOptionPane.showInputDialog(this, "Que restrincciones desea agregar?", "Restrincciones", JOptionPane.QUESTION_MESSAGE);
		try
		{
			List<VOClientes> lista = superAndes.darVORequC10(id, restrincciones);
			String resultado = "En Requerimiento";
			resultado += "\n" + listarClientes(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operacion terminada";
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}	
	}
	
	public void rfc11() throws Exception
	{
		int id = Integer.parseInt(JOptionPane.showInputDialog(this, "sucursal de las busquedas?", "Sucursal?", JOptionPane.QUESTION_MESSAGE));
		String restrincciones = JOptionPane.showInputDialog(this, "Que restrincciones desea agregar?", "Restrincciones", JOptionPane.QUESTION_MESSAGE);
		try
		{
			List<VOClientes> lista = superAndes.darVORequ11(id, restrincciones);
			String resultado = "En listarClientes";
			resultado += "\n" + listarClientes(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operacion terminada";
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	public void rfc13() throws Exception
	{
		int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Cual tipo de cliente desea buscar? 1, 2 o 3?", "Sucursal?", JOptionPane.QUESTION_MESSAGE));
		if(id <=0 || id >150)
		{
			throw new Exception ("No es un valor v�lido de respuesta");		
		}
		try
		{
			List<VOClientes> lista = superAndes.darVORequ13(id);
			String resultado = "En listarClientes";
			resultado += "\n" + listarClientes(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operacion terminada";
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}	
	}
	
	private String listarRequ12(List<VORequ12> lista) 
	{
		String resp = "Los resultados del requerimiento son";
		int i = 1;
		for (VORequ12 tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}
	
	

	public void buscarCliente()
	{
		try
		{
			String emailCliente = JOptionPane.showInputDialog(this, "Email del cliente?", "Buscar cliente por email", JOptionPane.QUESTION_MESSAGE);
			if(emailCliente != null)
			{
				VOClientes cliente = superAndes.darCliente(emailCliente);
				String resultado = "En buscar Cliente por cliente\n\n";
				if(resultado != null)
				{
					resultado += "El cliente es: " + cliente;
				}
				else
				{
					resultado += "Un cliente con email: " + emailCliente + " NO EXISTE\n";
				}
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancela por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una l�nea por cada tipo de bebida
	 * @param lista - La lista con los tipos de bebida
	 * @return La cadena con una l�ea para cada tipo de bebida recibido
	 */
	private String listarClientes(List<VOClientes> lista) 
	{
		String resp = "Los tipos de bebida existentes son:\n";
		int i = 1;
		for (VOClientes tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	/* ****************************************************************
	 * 			CRUD de Almacenamiento
	 *****************************************************************/

	public void agregarAlmacenamiento()
	{
		try
		{
			String idSucursal = JOptionPane.showInputDialog(this, "Id de la sucursal?", "Agregar almacenamiento", JOptionPane.QUESTION_MESSAGE);
			String codigoProd = JOptionPane.showInputDialog(this, "Cordigo barras que almacenar�?", "Agregar almacenamiento", JOptionPane.QUESTION_MESSAGE);
			String idCat = JOptionPane.showInputDialog(this, "Id categoria de producto?", "Agregar almacenamiento", JOptionPane.QUESTION_MESSAGE);
			String idTipo = JOptionPane.showInputDialog(this, "Id tipo producto?", "Agregar almacenamiento", JOptionPane.QUESTION_MESSAGE);
			String capaVol = JOptionPane.showInputDialog(this, "Capacidad en volumen del almacenamiento?", "Agregar almacenamiento", JOptionPane.QUESTION_MESSAGE);
			String capaPeso = JOptionPane.showInputDialog(this, "Capacidad en peso del almacenamiento?", "Agregar almacenamiento", JOptionPane.QUESTION_MESSAGE);
			String nivelRevast = JOptionPane.showInputDialog(this, "Nivel de reavastecimiento?", "Agregar almacenamiento", JOptionPane.QUESTION_MESSAGE);
			int tipo = JOptionPane.showConfirmDialog(this, "Es bodega?", "Agregar almacenamiento", JOptionPane.YES_NO_OPTION);
			if( idSucursal != null && codigoProd != null && capaVol != null && capaPeso != null && nivelRevast != null)
			{
				VOAlmacenamiento al = superAndes.agregarAlmacenamiento(Integer.parseInt(idSucursal), codigoProd, Integer.parseInt(idCat), Integer.parseInt(idTipo), Integer.parseInt(capaVol), Integer.parseInt(capaPeso), tipo + 1, Integer.parseInt(nivelRevast));
				if(al == null)
				{
					throw new Exception ("No se pudo crear un almacenamiento a la sucursal: " + idSucursal);	
				}
				String resultado = "En adicinarAlmacenamiento\n\n";
				resultado += "Almacenamiento adicionado exitosamente: " + al;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void eliminarAlmacenamiento()
	{
		try
		{
			String idSucursal = JOptionPane.showInputDialog(this, "Id de la sucursal?", "Eliminar almacenamiento", JOptionPane.QUESTION_MESSAGE);
			String codBarras = JOptionPane.showInputDialog(this, "Codigo de barras?", "Eliminar almacenamiento", JOptionPane.QUESTION_MESSAGE);
			int tipo = JOptionPane.showConfirmDialog(this, "Desea eliminar la bodega?", "Agregar almacenamiento", JOptionPane.YES_NO_OPTION);
			if(idSucursal != null && codBarras != null)
			{
				long alEliminados = superAndes.eliminarAlmacenamientoSucursal(Integer.parseInt(idSucursal), codBarras, tipo + 1);

				String resultado = "En eliminar Almacenamiento\n\n";
				resultado += alEliminados + " Almacenamientos eliminados\n";
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelaa por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarAlmacenamientoSucursal()
	{
		try
		{
			String idSucursal = JOptionPane.showInputDialog(this, "Id de la sucursal?", "Listar almacenamientos", JOptionPane.QUESTION_MESSAGE);
			if(idSucursal != null)
			{
				List <VOAlmacenamiento> lista = superAndes.darVOAlmacenamiento(Integer.parseInt(idSucursal));

				System.out.println(lista.size());
				String resultado = "En listarAlmacenamiento";
				resultado += "\n" + listarAlmacenamientos(lista);
				panelDatos.actualizarInterfaz(resultado);
				resultado += "\n Operaci�n Terminada";
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarAlmacenamientos(List<VOAlmacenamiento> lista)
	{
		String resp = "Los almacenamientos son: \n";
		int i =1;
		for(VOAlmacenamiento al : lista)
		{
			System.out.println(i);
			resp += i++ + ". " + al.toString() + "\n";
		}
		return resp;
	}

	/* ****************************************************************
	 * 			CRUD de Almacenamiento
	 *****************************************************************/

	public void nuevaVenta()
	{
		try
		{
			String idSucursal = JOptionPane.showInputDialog (this, "Id Sucursal?", "Venta Nueva", JOptionPane.QUESTION_MESSAGE);
			String email = JOptionPane.showInputDialog (this, "Email del cliente?", "Venta Nueva", JOptionPane.QUESTION_MESSAGE);
			ArrayList<String> codigos = new ArrayList();
			ArrayList<Integer> cantidad = new ArrayList<>();
			int resp = 0;
			while(resp == 0)
			{
				codigos.add(JOptionPane.showInputDialog (this, "C�digo de barras?", "Venta Nueva", JOptionPane.QUESTION_MESSAGE));
				cantidad.add(Integer.parseInt(JOptionPane.showInputDialog (this, "Cantidad del producto", "Venta Nueva", JOptionPane.QUESTION_MESSAGE)));
				resp = JOptionPane.showConfirmDialog(this, "desea agregar otro producto?", "Agregar almacenamiento", JOptionPane.YES_NO_OPTION);
			}
			if(idSucursal != null && email != null && !codigos.isEmpty() && !cantidad.isEmpty())
			{
				VOVentas su = superAndes.nuevaVenta(Integer.parseInt(idSucursal), email, 0
						
						);

				ArrayList<VOInfoProdSucursal> arreglo = new ArrayList<>();
				for(int i = 0; i < codigos.size(); i++)
				{
					VOInfoProdSucursal a = superAndes.agregarProductoVenta(su.getId(), su.getIdSucursal(), codigos.get(i), cantidad.get(i), 0 , 0);
					arreglo.add(a);
				}
				if(su == null || arreglo.isEmpty())
				{
					throw new Exception("No se pudo crear la venta nueva");
				}
				String resultado = "En adicionar venta\n\n";
				resultado += "Venta agregada exitosamente: " + su;
				for(int i = 0; i < codigos.size(); i++)
				{
					resultado += codigos.get(i) + cantidad.get(i);
				}
				resultado += "\nOperaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Productos
	 *****************************************************************/   

	public void agregarProducto()
	{
		try
		{
			String codBarras = JOptionPane.showInputDialog(this, "Codigo de barras", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
			String idCategoriaStr = JOptionPane.showInputDialog(this, "ID Categoria", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
			String idTipoProdStr = JOptionPane.showInputDialog(this, "ID Tipo Producto", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
			String nombre = JOptionPane.showInputDialog(this, "Nombre", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
			String marca = JOptionPane.showInputDialog(this, "Marca", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
			String presentacion = JOptionPane.showInputDialog(this, "Presentacion", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
			String cantidadPresentStr = JOptionPane.showInputDialog(this, "Cantidad Presentacion", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
			String uniMedida = JOptionPane.showInputDialog(this, "Unidad de Medida", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
			String volumenStr = JOptionPane.showInputDialog(this, "Volumen", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);
			String pesoStr = JOptionPane.showInputDialog(this, "Peso", "Agregar Producto", JOptionPane.QUESTION_MESSAGE);

			long idCategoria = Long.parseLong(idCategoriaStr);
			long idTipoProd = Long.parseLong(idTipoProdStr);
			int cantidadPresent = Integer.parseInt(cantidadPresentStr);
			int volumen = Integer.parseInt(volumenStr);
			int peso = Integer.parseInt(pesoStr);

			if(codBarras != null)
			{
				VOProducto tb = superAndes.agregarProducto(codBarras, idCategoria, idTipoProd, nombre, marca, presentacion, cantidadPresent, uniMedida, volumen, peso);
				if(tb == null)
				{
					throw new Exception("No se pudo crear el producto con el codigo de barras: " + codBarras);
				}
				String resultado = "En agregarProducto\n\n";
				resultado += "Producto adicionado exitosamente: " + codBarras;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarProductos()
	{
		try
		{
			List<VOProducto> lista = superAndes.darVOProductos();
			String resultado = "En listarProductos";
			resultado += "\n" + listarProductos(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operacion terminada";
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una l�nea por cada tipo de bebida
	 * @param lista - La lista con los tipos de bebida
	 * @return La cadena con una l�ea para cada tipo de bebida recibido
	 */
	private String listarProductos(List<VOProducto> lista) 
	{
		String resp = "Los productos existentes son:\n";
		int i = 1;
		for (VOProducto tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}
	/* ****************************************************************
	 * 			CRUD de Sucursales
	 *****************************************************************/   

	public void agregarSucursal()
	{
		try
		{

			String nombre = JOptionPane.showInputDialog(this, "Nombre", "Agregar Sucursal", JOptionPane.QUESTION_MESSAGE);
			String ciudad = JOptionPane.showInputDialog(this, "Ciudad", "Agregar Sucursal", JOptionPane.QUESTION_MESSAGE);
			String direccion = JOptionPane.showInputDialog(this, "Direccion", "Agregar Sucursal", JOptionPane.QUESTION_MESSAGE);

			if(nombre != null && ciudad != null && direccion != null)
			{
				VOSucursal tb = superAndes.agregarSucursal(nombre, ciudad, direccion);
				if(tb == null)
				{
					throw new Exception("No se pudo crear la sucursal con el nombre: " + nombre);
				}
				String resultado = "En agregarSucursal\n\n";
				resultado += "Sucursal adicionada exitosamente: " + nombre;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarSucursales()
	{
		try
		{
			List<VOSucursal> lista = superAndes.darVOSucursal();
			String resultado = "En listarSucursales";
			resultado += "\n" + listarSucursales(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operacion terminada";
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una l�nea por cada tipo de bebida
	 * @param lista - La lista con los tipos de bebida
	 * @return La cadena con una l�ea para cada tipo de bebida recibido
	 */
	private String listarSucursales(List<VOSucursal> lista) 
	{
		String resp = "Las sucursales existentes son:\n";
		int i = 1;
		for (VOSucursal tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	/* ****************************************************************
	 * 			CRUD de Proveedores
	 *****************************************************************/   

	public void agregarProveedor()
	{
		try
		{

			String nit = JOptionPane.showInputDialog(this, "NIT", "Agregar Proveedor", JOptionPane.QUESTION_MESSAGE);
			String nombre = JOptionPane.showInputDialog(this, "Ciudad", "Agregar Proveedor", JOptionPane.QUESTION_MESSAGE);
			String calificacionStr = JOptionPane.showInputDialog(this, "Direccion", "Agregar Proveedor", JOptionPane.QUESTION_MESSAGE);

			double calificacion = Double.parseDouble(calificacionStr);

			if(nit != null && nombre != null && calificacionStr != null)
			{
				VOProveedor tb = superAndes.agregarProveedor(nit, nombre, calificacion);
				if(tb == null)
				{
					throw new Exception("No se pudo crear el proveedor con el nit: " + nit);
				}
				String resultado = "En agregarProveedor\n\n";
				resultado += "Proveedor adicionada exitosamente: " + nit;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarProveedores()
	{
		try
		{
			List<VOProveedor> lista = superAndes.darVOProveedor();
			String resultado = "En listarProveedores";
			resultado += "\n" + listarProveedores(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operacion terminada";
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una l�nea por cada tipo de bebida
	 * @param lista - La lista con los tipos de bebida
	 * @return La cadena con una l�ea para cada tipo de bebida recibido
	 */
	private String listarProveedores(List<VOProveedor> lista) 
	{
		String resp = "Los proveedores existentes son:\n";
		int i = 1;
		for (VOProveedor tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	/* ****************************************************************
	 * 			CRUD de Promociones
	 * ****************************************************************/   

	public void agregarPromocion()
	{
		try
		{
			String idSucursalStr = JOptionPane.showInputDialog(this, "ID Sucursal", "Agregar Promocion", JOptionPane.QUESTION_MESSAGE);
			String codigoBarras = JOptionPane.showInputDialog(this, "Codigo de barras producto", "Agregar Promocion", JOptionPane.QUESTION_MESSAGE);
			String nombre = JOptionPane.showInputDialog(this, "Nombre", "Agregar Promocion", JOptionPane.QUESTION_MESSAGE);
			String fechaInicioStr = JOptionPane.showInputDialog(this, "Fecha Inicio (DD/MM/YYYY)", "Agregar Promocion", JOptionPane.QUESTION_MESSAGE);
			String fechaFinStr = JOptionPane.showInputDialog(this, "Fecha Fin (DD/MM/YYYY)", "Agregar Promocion", JOptionPane.QUESTION_MESSAGE);
			String tipoPromoStr = JOptionPane.showInputDialog(this, "Tipo promocion (numero)", "Agregar Promocion", JOptionPane.QUESTION_MESSAGE);
			String valorOriginalStr = JOptionPane.showInputDialog(this, "Valor Original", "Agregar Promocion", JOptionPane.QUESTION_MESSAGE);
			String valorPromoStr = JOptionPane.showInputDialog(this, "Valor Promocion", "Agregar Promocion", JOptionPane.QUESTION_MESSAGE);

			long idSucursal = Long.parseLong(idSucursalStr);
			int tipoPromo = Integer.parseInt(tipoPromoStr);
			int valorOriginal = Integer.parseInt(valorOriginalStr);
			int valorPromo = Integer.parseInt(valorPromoStr);
			SimpleDateFormat format = new SimpleDateFormat("DD/MM/YYYY");
			Date fechaInicio = format.parse(fechaInicioStr);
			Date fechaFin = format.parse(fechaFinStr);



			if(idSucursalStr != null)
			{
				VOPromocion tb = superAndes.agregarPromocion(idSucursal, codigoBarras,nombre, fechaInicio, fechaFin, tipoPromo, valorOriginal, valorPromo);
				if(tb == null)
				{
					throw new Exception("No se pudo crear la promocion con el nombre: " + nombre + ", en la sucursal: " + idSucursal);
				}
				String resultado = "En agregarPromocion\n\n";
				resultado += "Promocion adicionada exitosamente: " + nombre + " " + idSucursal;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarPromociones()
	{
		try
		{
			List<VOPromocion> lista = superAndes.darVOPromocion();
			String resultado = "En listarProductos";
			resultado += "\n" + listarPromociones(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operacion terminada";
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	

	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una l�nea por cada tipo de bebida
	 * @param lista - La lista con los tipos de bebida
	 * @return La cadena con una l�ea para cada tipo de bebida recibido
	 */
	private String listarPromociones(List<VOPromocion> lista) 
	{
		String resp = "Los productos existentes son:\n";
		int i = 1;
		for (VOPromocion tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}  

	/* ****************************************************************
	 * 			CRUD de Carritos
	 * ****************************************************************/   
	/**
	 * Un cliente solicita un carrito.
	 */
	public void solicitarCarrito()
	{
		try
		{
			String idSucursalStr = JOptionPane.showInputDialog(this, "ID Sucursal", "Solicitar Carrito", JOptionPane.QUESTION_MESSAGE);
			String email = JOptionPane.showInputDialog(this, "Email", "Solicitar Carrito", JOptionPane.QUESTION_MESSAGE);
			
			long idSucursal = Long.parseLong(idSucursalStr);

			if(idSucursalStr != null && email != null)
			{
				VOCarrito tb = superAndes.agregarCarrito(email, idSucursal, new Long(0), "Activo");
				carrito = tb;
				if(tb == null)
				{
					throw new Exception("No se pudo solicitar el carrito para: " + email + ", en la sucursal: " + idSucursal);
				}
				String resultado = "En agregarPromocion\n\n";
				resultado += "Promocion adicionada exitosamente: " + email + " " + idSucursal;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Un cliente abandona un carrito
	 */
	public void abandonarCarrito()
	{
		try
		{
			superAndes.abandonarCarrito(carrito.getDocumentoCliente(), carrito.getIdSucursal());
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Elimina los carritos y devuelve los productos a sus estantes.
	 */
	public void eliminarCarritos()
	{
		try
		{
			superAndes.eliminarCarritos();
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	public void finalizarCompra()
	{
		try
		{
			VOVentas venta = superAndes.finalizarCompra(carrito.getId(), carrito.getDocumentoCliente(), carrito.getIdSucursal(), carrito.getPrecioTotal());
			if(venta == null)
			{
				throw new Exception("No se puedo finalizar la venta del carrito: " + carrito.getId());
			}
			String resultado = "En finalizar compra\n\n";
			resultado += "Venta agregada " + venta;
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
			
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/* ****************************************************************
	 * 			CRUD de Carritos
	 * ****************************************************************/   
	
	public void agregarProductoCarrito()
	{
		try
		{
			String codigoBarras = JOptionPane.showInputDialog (this, "Codigo de barras del producto?", "Agregar producto", JOptionPane.QUESTION_MESSAGE);
			int cantidad = Integer.parseInt(JOptionPane.showInputDialog (this, "Cuantos desea agregar?", "Adicionar producto", JOptionPane.QUESTION_MESSAGE));
			if(codigoBarras != null && cantidad != 0)
			{
				VOInfoProdCarrito ipc = superAndes.agregarProductoCarrito(carrito.getId(), carrito.getDocumentoCliente(), carrito.getIdSucursal(), codigoBarras, cantidad, 0 , 0);
				if( ipc == null)
				{
					throw new Exception("No se pudo agregar un producto al carrito: " + codigoBarras);
				}
				String resultado = "En agregarProductoCarrito \n\n";
				resultado += "Producto al carrito agregado exitosamente: " + ipc;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	public void eliminarProductoCarrito()
	{
		try
		{
			String codigoBarras = JOptionPane.showInputDialog (this, "Codigo de barras del producto a eliminar?", "Agregar producto", JOptionPane.QUESTION_MESSAGE);
			if(codigoBarras != null)
			{
				long ipc = superAndes.eliminarProductoCarrito(carrito.getId(), carrito.getDocumentoCliente(), carrito.getIdSucursal(), codigoBarras);
				if(ipc == -1)
				{
					throw new Exception("No se pudo eliminar el producto : " + codigoBarras);
				}
				String resultado = "En eliminar producto carrito\n\n";
				resultado += ipc + " Productos eliminados\n";
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/* ****************************************************************
	 * 			CRUD de interfaz de carrito
	 *****************************************************************/
	
	public void mostrarCarrito()
	{
		try
		{
			String email = JOptionPane.showInputDialog (this, "Email del cliente", "Mostrar carrito", JOptionPane.QUESTION_MESSAGE);
			long idSucursal = Integer.parseInt(JOptionPane.showInputDialog (this, "Id de la sucursal", "Mostrar carrito", JOptionPane.QUESTION_MESSAGE));
			if(email != null && idSucursal != 0)
			{
				VOCarrito car = superAndes.darCarrito(email, idSucursal);
				System.out.println(car);
				String resultado = "En buscar carrito " + email + ", en la sucursal: " + idSucursal;
				if(car != null)
				{
					resultado += "El carrito encontrado: " + car;
					carrito = car;
				}
				else
				{
					resultado += "El carrito del cliente: " + email + " y con id de sucursal: " + idSucursal + " NO EXISTE";
				}
				resultado += "\n Operaci�n terminada";
				//panelCarrito.actualizarCarrito(carrito);
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		}
		catch (Exception e) 
    	{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
		
	}

	/* ****************************************************************
	 * 			CRUD de mensajes de error
	 *****************************************************************/

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicaci�n
	 * @param e - La excepci�n generada
	 * @return La cadena con la informaci�n de la excepci�n y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecuci�n\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y superandes.log para m�s detalles";
		return resultado;
	}

	/**
	 * Genera una cadena de caracteres con la descripci�n de la excepcion e, haciendo �nfasis en las excepcionsde JDO
	 * @param e - La excepci�n recibida
	 * @return La descripci�n de la excepci�n, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}



	/* ****************************************************************
	 * 			M�todos administrativos
	 *****************************************************************/

	/**
	 * Muestra el log de SuperAndes
	 */
	public void mostrarLogSuperAndes()
	{
		mostrarArchivo("superandes.log");
	}

	/**
	 * Abre el archivo dado como par�metro con la aplicaci�n por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus()
	{
		mostrarArchivo("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de SuperAndes
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogSuperAndes()
	{
		boolean resp = limpiarArchivo("superandes.log");

		String resultado = "\n\n************ Limpiando el log de superAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogDatanucleus()
	{
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo)
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			return false;
		}
	}

	/**
	 * Muestra la informaci�n acerca del desarrollo de esta apicaci�n
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogot�	- Colombia)\n";
		resultado += " * Departamento	de	Ingenier�a	de	Sistemas	y	Computaci�n\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes - Iteraci�n 2\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Mario Santiago Andrade \n";
		resultado += " * @author Andr�s Felipe Rodriguez \n";
		resultado += " * Octubre de 2018\n";
		resultado += " * \n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}
	
	

	/**
	 * M�todo para la ejecuci�n de los eventos que enlazan el men� con los m�todos de negocio
	 * Invoca al m�todo correspondiente seg�n el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String evento = e.getActionCommand();
		try
		{
			Method req = InterfazSuperAndesApp.class.getMethod(evento);
			req.invoke(this);
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/

	/**
	 * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por l�nea de comandos
	 */
	public static void main(String[] args) 
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp();
			interfaz.setVisible(true);
		}
		catch(Exception e)
		{
			e.printStackTrace( );
		}
	}

}

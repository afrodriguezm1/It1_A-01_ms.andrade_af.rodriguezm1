package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
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
import javax.swing.UIManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOAlmacenamiento;
import uniandes.isis2304.superAndes.negocio.VOClientes;
import uniandes.isis2304.superAndes.negocio.VOEmpresas;
import uniandes.isis2304.superAndes.negocio.VOInfoProdProveedor;
import uniandes.isis2304.superAndes.negocio.VOInfoProdSucursal;
import uniandes.isis2304.superAndes.negocio.VOPersona;
import uniandes.isis2304.superAndes.negocio.VOSucursal;
import uniandes.isis2304.superAndes.negocio.VOVentas;

@SuppressWarnings("serial")
public class InterfazSuperAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json";
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
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
     * Asociación a la clase principal del negocio.
     */
	private SuperAndes superAndes;
	
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	
	/**
     * Objeto JSON con la configuración de interfaz de la app.
     */
	private JsonObject guiConfig;
	
	/**
     * Panel de despliegue de interacción para los requerimientos
     */
	private PanelDatos panelDatos;

	 /**
     * Menú de la aplicación
     */
	private JMenuBar menuBar;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
     * Construye la ventana principal de la aplicación. <br>
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
		panelDatos = new PanelDatos();
		
		setLayout ( new BorderLayout());
		add(new JLabel( new ImageIcon (path)), BorderLayout.NORTH);
		add(panelDatos, BorderLayout.CENTER);
	}
	
	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	
	/**
     * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
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
			log.info("Se encontró un archivo de configuración válido: " + tipo);
		}
		catch(Exception e)
		{
			log.info("No se encontró un archivo de configuración válido");
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}
		return config;
	}

	/**
     * Método para configurar el frame principal de la aplicación
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
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info("Se aplica configuraciín indicada en el archivo de configuración");
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
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
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
			int tipo = JOptionPane.showConfirmDialog(this, "El cliente es una empresa?", "Agregar Cliente", JOptionPane.YES_NO_OPTION);
			System.out.println(tipo);
			String dato1 = JOptionPane.showInputDialog(this, ((tipo == 0)? "Nit de la empresa" : "Documento del cliente"), "Agregar Cliente", JOptionPane.QUESTION_MESSAGE);
			if(tipo == 0)
			{
				String dato2 = JOptionPane.showInputDialog(this, "Dirección del cliente?", "Agregar Cliente", JOptionPane.QUESTION_MESSAGE);
				if(emailCliente != null)
				{
					VOClientes tb = superAndes.agregarCliente(emailCliente, nombreCliente);
					VOEmpresas tb2 = superAndes.agregarEmpresa(emailCliente, dato1, dato2);
					if(tb == null || tb2 == null)
					{
						throw new Exception("No se pudo crear el cliente con el email: " + emailCliente);
					}
					String resultado = "En agregarCliente\n\n";
					resultado += "Cliente adicionado exitosamente:    " + emailCliente + "\n";
					resultado += "                    	   Nombre:    " + nombreCliente + "\n";
					resultado += "                    	   Nit:       " + dato1 + "\n";
					resultado += "                    	   Dirección: " + dato2 + "\n";
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
			else if(tipo == 1)
			{
				if(emailCliente != null)
				{
					VOClientes tb = superAndes.agregarCliente(emailCliente, nombreCliente);
					VOPersona tb2 = superAndes.agregarPersona(emailCliente, Integer.parseInt(dato1));
					if(tb == null || tb2 == null)
					{
						throw new Exception("No se pudo crear el cliente con el email: " + emailCliente);
					}
					String resultado = "En agregarCliente\n\n";
					resultado += "Cliente adicionado exitosamente: " + emailCliente + "\n";
					resultado += "                    	   Nombre:    " + nombreCliente + "\n";
					resultado += "                    	   Documento: " + dato1 + "\n";
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
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
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancela por el usuario");
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
     * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los tipos de bebida
     * @return La cadena con una líea para cada tipo de bebida recibido
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
    		String codigoProd = JOptionPane.showInputDialog(this, "Cordigo barras que almacenará?", "Agregar almacenamiento", JOptionPane.QUESTION_MESSAGE);
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
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
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
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelaa por el usuario");
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
    			resultado += "\n Operación Terminada";
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
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
    			codigos.add(JOptionPane.showInputDialog (this, "Código de barras?", "Venta Nueva", JOptionPane.QUESTION_MESSAGE));
    			cantidad.add(Integer.parseInt(JOptionPane.showInputDialog (this, "Cantidad del producto", "Venta Nueva", JOptionPane.QUESTION_MESSAGE)));
    			resp = JOptionPane.showConfirmDialog(this, "desea agregar otro producto?", "Agregar almacenamiento", JOptionPane.YES_NO_OPTION);
    		}
    		if(idSucursal != null && email != null && !codigos.isEmpty() && !cantidad.isEmpty())
    		{
    			VOVentas su = superAndes.nuevaVenta(Integer.parseInt(idSucursal), email);

				ArrayList<VOInfoProdSucursal> arreglo = new ArrayList<>();
    			for(int i = 0; i < codigos.size(); i++)
    			{
    				VOInfoProdSucursal a = superAndes.agregarProductoVenta(su.getId(), codigos.get(i), cantidad.get(i));
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
    			resultado += "\nOperación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    	}
    	catch(Exception e)
    	{
    		String resultado = generarMensajeError(e);
    		panelDatos.actualizarInterfaz(resultado);
    	}
    }
    
    
    /* ****************************************************************
	 * 			CRUD de mensajes de error
	 *****************************************************************/
	
	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y superandes.log para más detalles";
		return resultado;
	}
	
	/**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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
	 * 			Métodos administrativos
	 *****************************************************************/
	
	/**
	 * Muestra el log de SuperAndes
	 */
	public void mostrarLogSuperAndes()
	{
		mostrarArchivo("superandes.log");
	}
	
	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
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
	 * Muestra en el panel de datos la traza de la ejecución
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
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus()
	{
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
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
     * Muestra la información acerca del desarrollo de esta apicación
     */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes - Iteración 2\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Mario Santiago Andrade \n";
		resultado += " * @author Andrés Felipe Rodriguez \n";
		resultado += " * Octubre de 2018\n";
		resultado += " * \n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}

	/**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
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
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
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

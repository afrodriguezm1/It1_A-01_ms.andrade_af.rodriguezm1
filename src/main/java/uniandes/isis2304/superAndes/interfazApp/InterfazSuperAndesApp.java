package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.lang.reflect.Method;
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
import uniandes.isis2304.superAndes.negocio.VOClientes;

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
			if(emailCliente != null)
			{
				VOClientes tb = superAndes.agregarCliente(emailCliente, nombreCliente);
				if(tb == null)
				{
					throw new Exception("No se pudo crear el cliente con el email: " + emailCliente);
				}
				String resultado = "En agregarCliente\n\n";
				resultado += "Cliente adicionado exitosamente: " + emailCliente;
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
	
	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
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

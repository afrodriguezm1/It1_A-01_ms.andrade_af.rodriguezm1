package uniandes.isis2304.superAndes.InterfazDemo;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOCarrito;
import uniandes.isis2304.superAndes.negocio.VOCategoria;
import uniandes.isis2304.superAndes.negocio.VOClientes;
import uniandes.isis2304.superAndes.negocio.VOEmpresas;
import uniandes.isis2304.superAndes.negocio.VOInfoProdCarrito;
import uniandes.isis2304.superAndes.negocio.VOPersona;
import uniandes.isis2304.superAndes.negocio.VOProducto;
import uniandes.isis2304.superAndes.negocio.VOPromocion;
import uniandes.isis2304.superAndes.negocio.VOProveedor;
import uniandes.isis2304.superAndes.negocio.VOSucursal;
import uniandes.isis2304.superAndes.negocio.VOTipoProducto;
import uniandes.isis2304.superAndes.negocio.VOVentas;
import uniandes.isis2304.superAndes.negocio.Ventas;

@SuppressWarnings("serial")
public class InterfazSuperAndesDemo extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesDemo.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigDemo.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json";
	
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
    public InterfazSuperAndesDemo()
    {
    	// Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        superAndes = new SuperAndes(tableConfig);
    	//String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );
        setLayout (new BorderLayout());
        //add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );   
        add( panelDatos, BorderLayout.CENTER );  

    }
    
	/* ****************************************************************
	 * 			Métodos para la configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");	
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Super Andes App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "Super Andes APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }
    
    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
	/* ****************************************************************
	 * 			Demos de Sucursal
	 *****************************************************************/
    /**
    * Demostración de creación, consulta y borrado de Sucursal
    * Muestra la traza de la ejecución en el panelDatos
    * 
    * Pre: La base de datos está vacía
    * Post: La base de datos está vacía
    */
    public void demoSucursal()
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			
			String nombre = "Mercado";
			String direccion ="Calle 4 59";
			String ciudad = "Medellín";
			boolean errorSucursal = false;
			
			VOSucursal sucursal = superAndes.agregarSucursal(nombre, direccion, ciudad);
			
			if(sucursal == null)
			{
				sucursal = superAndes.darSucursal(nombre);
				errorSucursal = true;
			}
			
			List<VOSucursal> lista = superAndes.darVOSucursal();
			long sucEliminados = superAndes.eliminarSucursal(nombre, direccion);
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Sucursal\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorSucursal)
			{
				resultado += "*** Exception creando sucursal !!\n";
				resultado += "*** Es probable que esa sucursal ya existiera y hay restricción de UNICIDAD sobre el nombre de la sucursal\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado la sucursal con nombre: " + nombre + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarSucursal (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += sucEliminados + " sucursal eliminada\n";
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
	/* ****************************************************************
	 * 			Demos de Proveedor
	 *****************************************************************/
    public void demoProveedor()
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			
    		String nit = "68648516";
			String nombre = "Mercado";
			double calificacion = 3;
			boolean errorProveedor = false;
			
			VOProveedor proveedor = superAndes.agregarProveedor(nit, nombre, calificacion);
			
			if(proveedor == null)
			{
				proveedor = superAndes.darProveedor(nit);
				errorProveedor = true;
			}
			//Prueba si la calificacion esta en el rango permitido ( 0 >= calificacion <= 5)
			boolean errorCalificacion = false;
			if(calificacion < 0 || calificacion > 5)
			{
				errorCalificacion = true;
			}
			
			List<VOProveedor> lista = superAndes.darVOProveedor();
			long provEliminados = superAndes.eliminarProveedor(nit);
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de proveedor\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorProveedor)
			{
				resultado += "*** Exception creando proveedor !!\n";
				resultado += "*** Es probable que ese proveedor ya existiera y hay restricción de UNICIDAD sobre el nombre de la sucursal\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorCalificacion)
			{
				resultado += "*** Exception creando proveedor !!\n";
				resultado += "*** La calificacion es menor a 0 o mayor a 5\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado la sucursal con nombre: " + nombre + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarProveedor (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += provEliminados + " proveedor eliminada\n";
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
	/* ****************************************************************
	 * 			Demos de Promocion
	 *****************************************************************/
    public void demoPromocion()
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			long idSucursal = 1;
    		String codBarras = "68648516";
			String nombre = "2x1";
			SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
			Date fechaFin = formatter.parse("20-08-2018");
			Date fechaInicio = formatter.parse("29-08-2018");
			int tipoPromo = 2;
			int valorOriginal = 8500;
			int valorPromo  = 6200;
			boolean errorPromocion = false;
			
			VOPromocion promocion = superAndes.agregarPromocion(idSucursal, codBarras, nombre, fechaInicio, fechaFin, tipoPromo, valorOriginal, valorPromo );
			
			if(promocion == null)
			{
				promocion = superAndes.darPromocion(codBarras, idSucursal, nombre);
				errorPromocion = true;
			}
			
			boolean errorFecha = false;
			//Revisa si la fecha de inicio es menor a la fecha final de la promocion
			if(fechaInicio.after(fechaInicio))
			{
				errorFecha = true;
			}
			
			boolean errorIdSucursal = false;
			//Busca si existe la sucursal con ese id. Si no existe no se puede agregar
			String existeIdSucursal = Long.toString(superAndes.darIdSucursal(idSucursal));
			if(existeIdSucursal.equals("") )
			{
				errorIdSucursal = true;
			}
						
			List<VOPromocion> lista = superAndes.darVOPromocion();
			long promoEliminados = superAndes.eliminarPromocion(codBarras, idSucursal, nombre);
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de promocion\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorPromocion)
			{
				resultado += "*** Exception creando promocion !!\n";
				resultado += "*** Es probable que esa promocion ya existiera y hay restricción de UNICIDAD sobre el nombre de la sucursal\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorFecha)
			{
				resultado += "*** Exception creando promocion !!\n";
				resultado += "*** La fecha inicial es mayor a la fecha final \n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorIdSucursal)
			{
				resultado += "*** Exception creando promocion !!\n";
				resultado += "*** La sucursal no existe! \n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			
			resultado += "Adicionado la promocion con nombre: " + nombre + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarPromocion (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += promoEliminados + " promocion eliminada\n";
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
	/* ****************************************************************
	 * 			Demos de Producto
	 *****************************************************************/
    public void demoProducto()
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
    		

			String codBarras = "68648516";
			long idCategoria = 1;
			long idTipoProducto = 1;
			String nombre = "Leche Entera";
			String marca = "Alqueria";
			String presentacion = "Bolsa";
			int cantidadPresen = 15;
			String uniMedida = "gr";
			int volumen = 15;
			int peso = 550;
			boolean errorProducto = false;
			
			VOProducto producto = superAndes.agregarProducto(codBarras, idCategoria, idTipoProducto, nombre, marca, presentacion, cantidadPresen, uniMedida, volumen, peso);  
			
			if(producto == null)
			{
				producto = superAndes.darProducto(codBarras);
				errorProducto = true;
			}
			
			boolean errorUnidadMedida = true;
			//Revisa si la unidad de medida es gr o ml
			if(uniMedida.equals("gr") || uniMedida.equals("ml"))
			{
				errorUnidadMedida = false;
			}
						
			List<VOProducto> lista = superAndes.darVOProductos();
			long prodEliminados = superAndes.eliminarProducto(codBarras);
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de producto\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorProducto)
			{
				resultado += "*** Exception creando producto !!\n";
				resultado += "*** Es probable que ese producto ya existiera y hay restricción de UNICIDAD sobre el codigo de barras del producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorUnidadMedida)
			{
				resultado += "*** Exception creando producto !!\n";
				resultado += "*** La unidad de medida debe ser 'gr' o 'ml' \n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el producto con codigo de barras: " + codBarras + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarProducto(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += prodEliminados + " producto eliminado\n";
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
	/* ****************************************************************
	 * 			Demos de Cliente
	 *****************************************************************/
    public void demoCliente()
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
    		

			String emailPersona = "shlohmo@global.com";
			String nombrePersona = "Shlohmo";
			String emailEmpresa = "Nosaj@thing.com";
			String nombreEmpresa = "Nosaj Thing";
			long documento = 16513165;
			String nit = "165146-15";
			String direccion = "Carrera 5 23-12";
			boolean errorCliente = false;
			
			VOClientes clientePersona = superAndes.agregarCliente(emailPersona, nombrePersona);
			VOPersona persona = superAndes.agregarPersona(emailPersona, documento);
			VOClientes clienteEmpresa = superAndes.agregarCliente(emailEmpresa, nombreEmpresa);
			VOEmpresas empresa = superAndes.agregarEmpresa(emailEmpresa, nit, direccion);
			
			if(clientePersona == null)
			{
				clientePersona = superAndes.darCliente(emailPersona);
				errorCliente = true;
			}
			
			if(clienteEmpresa == null)
			{
				clienteEmpresa = superAndes.darCliente(emailEmpresa);
				errorCliente = true;
			}
			
			boolean errorPersona = false;
			//Revisa si el  email del cliente ya existe para asociarlo a la persona
			if(!clienteEmpresa.getEmail().equals(persona.getEmail()))
			{
				errorPersona = true;
			}
			
			boolean errorEmpresa = false;
			//Revisa si el email del cliente ya existe para asociarlo a la empresa
			if(!clienteEmpresa.getEmail().equals(empresa.getEmail()))
			{
				errorEmpresa = true;
			}
			
			List<VOClientes> lista = superAndes.darVOClientes();
			long clientePerEliminados = superAndes.eliminarCliente(emailPersona);
			long personaEliminados = superAndes.eliminarPersona(emailPersona);
			long clienteEmpEliminados = superAndes.eliminarCliente(emailEmpresa);
			long EmpresaEliminados = superAndes.eliminarEmpresa(emailEmpresa);
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de clientes\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorCliente)
			{
				resultado += "*** Exception creando Cliente !!\n";
				resultado += "*** Es probable que ese cliente ya existiera y hay restricción de UNICIDAD sobre el email del cliente\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorPersona)
			{
				resultado += "*** Exception creando Persona !!\n";
				resultado += "*** No hay un cliente asociado con ese email\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorEmpresa)
			{
				resultado += "*** Exception creando Empresa !!\n";
				resultado += "*** No hay un cliente asociado con ese email\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el cliente persona con email: " + emailPersona + "\n";
			resultado += "Adicionado el cliente empresa con email: " + emailEmpresa + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarClientes(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += clientePerEliminados + " cliente persona eliminado\n";
			resultado += clienteEmpEliminados + " cliente empresa eliminado\n";
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
	/* ****************************************************************
	 * 			Demos de Ventas
	 *****************************************************************/
    public void demoVentas()
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
    		
    		long idSucursal = 1;
    		String emailCliente = "shlohmo@global.com";
			
			VOVentas venta = superAndes.nuevaVenta(idSucursal, emailCliente);

			
			boolean errorCliente = false;
			VOClientes buscado = superAndes.darCliente(emailCliente);
			//Revisa si el email del cliente existe en la BD
			if(buscado == null)
			{
				errorCliente = true;
			}

			boolean errorIdSucursal = false;
			//Busca si existe la sucursal con ese id. Si no existe no se puede agregar
			String existeIdSucursal = Long.toString(superAndes.darIdSucursal(idSucursal));
			if(existeIdSucursal.equals("") )
			{
				errorIdSucursal = true;
			}
						
			List<Ventas> lista = superAndes.darVenta(emailCliente);
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de venta\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if(errorCliente)
			{
				resultado += "*** Exception creando la venta !!\n";
				resultado += "*** No existe un cliente con ese email \n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			
			if(errorIdSucursal)
			{
				resultado += "*** Exception creando la venta !!\n";
				resultado += "*** No existe la sucursal con ese id\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado la venta: \n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarVentas(lista);

			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
	/* ****************************************************************
	 * 			Demos de Categoria
	 *****************************************************************/
    public void demoCategoria()
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
    		long id = 1;
			String nombre = "Leche Entera";
			boolean errorCategoria = false;
			VOCategoria categoria = superAndes.agregarCategoria(nombre);
			
			if(categoria == null)
			{
				categoria = superAndes.darCategoria(id);
				errorCategoria = true;
			}
			
			boolean errorTipoProd = true;
			VOTipoProducto existeTipoProd = superAndes.darTipoProd(id);
			
			if(existeTipoProd == null)
			{
				
			}
						
			List<VOCategoria> lista = superAndes.darVOCategoria();
			long categoriaEliminados = superAndes.eliminarCategoria(nombre);
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de categoria\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorCategoria)
			{
				resultado += "*** Exception creando categoria !!\n";
				resultado += "*** Es probable que esa categoria ya existiera\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorTipoProd)
			{
				resultado += "*** Exception creando categoria !!\n";
				resultado += "*** El tipo de producto no existe en la BD\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado la categoria con nombre: " + nombre + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarCategorias(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += categoriaEliminados + " categoria eliminada\n";
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
	/* ****************************************************************
	 			Demos de Carrito
	 *****************************************************************/
    
    /**
     * Demo de operaciones basicas de carrito, incluyendo recolectar productos
     */
    public void demoCarrito()
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
    		String emailCliente = "djones@future.com";
    		long idSucursal = 1;
    		String estado = "Activo";
    		
			VOCarrito carrito = superAndes.agregarCarrito(emailCliente, idSucursal, 0, estado);
			
			boolean errorCarrito = false;
			if(carrito == null)
			{
				carrito = superAndes.darCarrito(emailCliente, idSucursal);
				errorCarrito = true;
			}
			
			boolean errorIdSucursal = false;
			//Busca si existe la sucursal con ese id. Si no existe no se puede agregar
			String existeIdSucursal = Long.toString(superAndes.darIdSucursal(idSucursal));
			if(existeIdSucursal.equals("") )
			{
				errorIdSucursal = true;
			}
			
			boolean errorEstado = true;
			//Revisa si el estado del carrito es activo o abandonado
			if(estado.equals("Activo") || estado.equals("Abandonado"))
			{
				errorEstado = false;
			}
			
			superAndes.abandonarCarrito(emailCliente, idSucursal);
			boolean errorAbandonar = false;
			//Revisa que al abandonar el carrito el estado pase a ser abandonado
			if(!carrito.getEstado().equals("Abandonado")) 
			{
				errorAbandonar = true;
			}
						
			List<VOCarrito> lista = superAndes.darVOCarrito();
			//Retorna los productos del carrito y posteriormente lo elimina
			superAndes.eliminarCarritos(); 
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de carrito\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorCarrito)
			{
				resultado += "*** Exception creando carrito !!\n";
				resultado += "*** Es probable que el cliente ya tuviera un carrito\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorIdSucursal)
			{
				resultado += "*** Exception creando carrito!!\n";
				resultado += "*** La sucursal no se encuentra en la BD\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorEstado)
			{
				resultado += "*** Exception creando carrito!!\n";
				resultado += "*** El estado del carrito debe ser 'Activo' o 'Abandonado'\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorAbandonar)
			{
				resultado += "*** Exception abandonando carrito!!\n";
				resultado += "*** El carrito no cambia de estado a 'Abandonado'\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el carrito de: " + emailCliente + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarCarritos(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += " carrito eliminado\n";
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    /**
     * Demo de operaciones realizadas con productos en un carrito.
     */
    public void demoInfoProductoCarrito()
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
    		String emailCliente = "djones@future.com";
    		long idSucursal = 1;
    		String estado = "Activo";
    		
			VOCarrito carrito = superAndes.agregarCarrito(emailCliente, idSucursal, 0, estado);
    		
    		
    		long idCarrito = 1;
    		String codigoBarras = "9308907443851";
    		int cantidad = 1;

    		VOInfoProdCarrito infoProdCarrito = superAndes.agregarProductoCarrito(idCarrito, emailCliente, idSucursal, codigoBarras, cantidad);
    		
    		
    		VOProducto existeProd = superAndes.darProducto(codigoBarras);
    		boolean errorInfoProd = false;
    		//Verifica que el producto exista
    		if(existeProd == null)
    		{
    			errorInfoProd = true;
    		}
    	
			int hayCantidad = existeProd.getCantidadPresent();
			boolean errorCantidad = false;
			// Revisa que haya la cantidad suficiente del producto
			if(hayCantidad < cantidad )
			{
				errorCantidad = true;
			}
			
			boolean errorCarrito = false;
			//Revisa que el carrito exista en la sucursal y sea del cliente que agrega productos
			if(carrito == null)
			{
				carrito = superAndes.darCarrito(emailCliente, idSucursal);
				errorCarrito = true;
			}
			
			boolean errorIdSucursal = false;
			//Busca si existe la sucursal con ese id. Si no existe no se puede agregar
			String existeIdSucursal = Long.toString(superAndes.darIdSucursal(idSucursal));
			if(existeIdSucursal.equals("") )
			{
				errorIdSucursal = true;
			}
			
			superAndes.eliminarProductoCarrito(idCarrito, emailCliente, idSucursal, codigoBarras);
			
			superAndes.eliminarCarritos(); 
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de carrito\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorCarrito)
			{
				resultado += "*** Exception creando carrito !!\n";
				resultado += "*** Es probable que el cliente ya tuviera un carrito\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorIdSucursal)
			{
				resultado += "*** Exception creando carrito!!\n";
				resultado += "*** La sucursal no se encuentra en la BD\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorInfoProd)
			{
				resultado += "*** Exception agregando producto!!\n";
				resultado += "*** El producto no existe en la sucursal\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			if(errorCantidad)
			{
				resultado += "*** Exception agregando producto!!\n";
				resultado += "*** No hay la suficiente cantidad del producto\n";
				resultado += "*** Revise el log de superAndes para más detalles\n";
			}
			resultado += "Adicionado el producto: " + codigoBarras + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += " producto eliminado\n";
			resultado += " carrito eliminado\n";
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
    /**
     * Genera una cadena de caracteres con la lista de sucursales recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los sucursales
     * @return La cadena con una líea para cada sucursal recibido
     */
    private String listarSucursal(List<VOSucursal> lista) 
    {
    	String resp = "Las sucursales existentes son:\n";
    	int i = 1;
        for (VOSucursal tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    /**
     * Genera una cadena de caracteres con la lista de sucursales recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los sucursales
     * @return La cadena con una líea para cada sucursal recibido
     */
    private String listarProveedor(List<VOProveedor> lista) 
    {
    	String resp = "Los proveedores existentes son:\n";
    	int i = 1;
        for (VOProveedor tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    /**
     * Genera una cadena de caracteres con la lista de sucursales recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los sucursales
     * @return La cadena con una líea para cada sucursal recibido
     */
    private String listarPromocion(List<VOPromocion> lista) 
    {
    	String resp = "Las promociones existentes son:\n";
    	int i = 1;
        for (VOPromocion tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    /**
     * Genera una cadena de caracteres con la lista de sucursales recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los sucursales
     * @return La cadena con una líea para cada sucursal recibido
     */
    private String listarProducto(List<VOProducto> lista) 
    {
    	String resp = "Los productos existentes son:\n";
    	int i = 1;
        for (VOProducto tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    /**
     * Genera una cadena de caracteres con la lista de sucursales recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los sucursales
     * @return La cadena con una líea para cada sucursal recibido
     */
    private String listarClientes(List<VOClientes> lista) 
    {
    	String resp = "Los clientes existentes son:\n";
    	int i = 1;
        for (VOClientes tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    /**
     * Genera una cadena de caracteres con la lista de sucursales recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los sucursales
     * @return La cadena con una líea para cada sucursal recibido
     */
    private String listarVentas(List<Ventas> lista) 
    {
    	String resp = "Las ventas existentes son:\n";
    	int i = 1;
        for (Ventas tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    /**
     * Genera una cadena de caracteres con la lista de sucursales recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los sucursales
     * @return La cadena con una líea para cada sucursal recibido
     */
    private String listarCategorias(List<VOCategoria> lista) 
    {
    	String resp = "Las categorias existentes son:\n";
    	int i = 1;
        for (VOCategoria tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    
    /**
     * Genera una cadena de caracteres con la lista de sucursales recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los sucursales
     * @return La cadena con una líea para cada sucursal recibido
     */
    private String listarCarritos(List<VOCarrito> lista) 
    {
    	String resp = "Los carritos existentes son:\n";
    	int i = 1;
        for (VOCarrito tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
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
	
	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogSuperAndes ()
	{
		mostrarArchivo ("superAndes.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogSuperAndes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("superAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de superAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
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
//			e.printStackTrace();
			return false;
		}
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
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazSuperAndesDemo.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazSuperAndesDemo interfaz = new InterfazSuperAndesDemo( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}

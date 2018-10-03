package uniandes.isis2304.superAndes.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDOHelper;
import org.apache.log4j.Logger;
import javax.jdo.PersistenceManagerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PersistenciaSuperAndes 
{
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());
	
	public final static String SQL = "javax.jdo.query.SQL";
	
	private static PersistenciaSuperAndes instance;
	
	private PersistenceManagerFactory pmf;
	
	private List<String> tablas;
	
//	private SQLSucursal sqlSucursal;
	
//	private SQLProveedor sqlProveedor;
	
//	private SQLCategoria sqlCategoria;
	
//	private SQLTipoProducto sqlTipoProducto;
	
//	private SQLProducto sqlProducto;
	
//	private SQLProductoProveedor sqlProductoProveedor;
	
//	private SQLProductoSucursal sqlProductoSucursal;
	
//	private SQLProductoRedimible sqlProductoRedimible;
	
//	private SQLPromocion sqlPromocion;
	
//	private SQLOrdenPedido sqlOrdenPedido;
	
	private SQLInfoProductoProveedor sqlInfProProveedor;
	
//	private SQLAlmacenamienti sqlAlmacenamiento;
	
//	private SQLClientes sqlClientes;
	
//	private SQLPersonas sqlPersonas;
	
//	private SQLEmpresas sqlEmpresas;
	
//	private SQLVentas sqlVentas;
	
//	private SQLInfoProductoSucursal sqlInfProSucursal;
	
//	private SQLResoluciones sqlResoluciones;
	
//	private SQLInformacion sqlInformacion;
	
	private PersistenciaSuperAndes()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");
		crearClasesSQL();
		
		tablas = new LinkedList<String>();
		tablas.add("SuperAndes_sequence");
		tablas.add("SUCURSAL");
		tablas.add("PROVEEDOR");
		tablas.add("CATEGORIA");
		tablas.add("TIPO_PRODUCTO");
		tablas.add("PRODUCTO");
		tablas.add("PRODUCTO_PROVEEDOR");
		tablas.add("PRODUCTO_SUCURSAL");
		tablas.add("PRODUCTO_REDIMIBLE");
		tablas.add("PROMOCION");
		tablas.add("ORDEN_PEDIDO");
		tablas.add("INFO_PRODUCTO_PROVEEDOR");
		tablas.add("ALMACENAMIENTO");
		tablas.add("CLIENTES");
		tablas.add("PERSONAS");
		tablas.add("EMPRESAS");
		tablas.add("VENTAS");
		tablas.add("INFO_PRODUCTO_SUCURSAL");
		tablas.add("RESOLUCIONES");
		tablas.add("INFORMACION");		
	}
	
	private PersistenciaSuperAndes(JsonObject tableConfig)
	{
		crearClasesSQL();
		tablas = leerNombresTablas(tableConfig);
		
		System.out.println(tableConfig.get("unidadPersistencia").getAsString());
		String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();
		log.trace("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
	}
	
	public static PersistenciaSuperAndes getInstance()
	{
		if(instance == null)
		{
			instance = new PersistenciaSuperAndes();
		}
		return instance;
	}
	
	public static PersistenciaSuperAndes getInstance(JsonObject tableConfig)
	{
		if(instance == null)
		{
			instance = new PersistenciaSuperAndes(tableConfig);
		}
		return instance;
	}
	
	public void cerrarUnidadPersistencia()
	{
		pmf.close();
		instance = null;
	}
	
	private List <String> leerNombresTablas(JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas");
		
		List<String> resp = new LinkedList <String> ();
		for(JsonElement nom : nombres)
		{
			resp.add(nom.getAsString());
		}
		return resp;
	}
	
	private void crearClasesSQL()
	{
//		sqlSucursal = new SQLSucursla(this);
//		sqlProveedor = new SQLProveedor(this);
//		sqlCategoria = new SQLCategoria(this);
//		sqlTipoProducto = new SQLTipoProducto(this);
//		sqlProducto = new SQLProducto(this);
//		sqlProductoProveedor = new SQLProductoProveedor(this);
//		sqlProductoSucursal = new SQLProductoSucursal(this);
//		sqlProductoRedimible = new SQLProductoRedimible(this);
//		sqlPromocion = new SQLPromocion(this);
//		sqlOrdenPedido = new SQLOrdenPedido(this);
		sqlInfProProveedor= new SQLInfoProductoProveedor(this);
		
		
		
		
		
		
		
		
	}
	
	public String darSeqSuperAndes()
	{
		return tablas.get(0);
	}
}

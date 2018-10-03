package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;

import org.apache.log4j.Logger;
import org.datanucleus.store.rdbms.sql.method.SQLBooleanMethod;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.Almacenamiento;
import uniandes.isis2304.superAndes.negocio.Clientes;
import uniandes.isis2304.superAndes.negocio.Empresas;
import uniandes.isis2304.superAndes.negocio.InfoProdProveedor;
import uniandes.isis2304.superAndes.negocio.InfoProdSucursal;
import uniandes.isis2304.superAndes.negocio.Informacion;
import uniandes.isis2304.superAndes.negocio.Personas;
import uniandes.isis2304.superAndes.negocio.Resoluciones;
import uniandes.isis2304.superAndes.negocio.Ventas;

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

	private SQLAlmacenamiento sqlAlmacenamiento;

	private SQLClientes sqlClientes;

	private SQLPersonas sqlPersonas;

	private SQLEmpresas sqlEmpresas;

	private SQLVentas sqlVentas;

	private SQLInfoProductoSucursal sqlInfProSucursal;

	private SQLResoluciones sqlResoluciones;

	private SQLInformacion sqlInformacion;

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
		sqlAlmacenamiento = new SQLAlmacenamiento(this);
		sqlClientes = new SQLClientes(this);
		sqlPersonas = new SQLPersonas(this);
		sqlEmpresas = new SQLEmpresas(this);		
		sqlVentas = new SQLVentas(this);
		sqlInfProSucursal = new SQLInfoProductoSucursal(this);
		sqlResoluciones = new SQLResoluciones(this);
		sqlInformacion = new SQLInformacion(this);		

	}

	public String darSeqSuperAndes()
	{
		return tablas.get(0);
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
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

	//------------------------------------------------------------------------
	// Info Producto Proveedor
	//------------------------------------------------------------------------

	public InfoProdProveedor agregarInfoProdProveedor(long idOrden,String codigoBarras)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long idOr = idOrden;
			String cod = codigoBarras;
			long tuplasInsertadas = sqlInfProProveedor.agregarInfoProductoProveedor(pm, idOr, cod);
			tx.commit();

			log.trace("Inserción de Información de producto proveedor: " + idOr + ": " + tuplasInsertadas + " tuplas insertad");
			return new InfoProdProveedor(idOr, cod, 1);
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarInfoProdProveedor(long idOrden, String codigo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlInfProProveedor.eliminarInfoProductoProveedor(pm, idOrden, codigo);
			tx.commit();
			return resp;
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<InfoProdProveedor> darInfoProdProveedorOrden(long idOrden)
	{
		return sqlInfProProveedor.darInfoProdProveedorPorOrden(pmf.getPersistenceManager(), idOrden);
	}

	public InfoProdProveedor darInfoProdProveedorEspecifico(long idOrden, String codigo)
	{
		return sqlInfProProveedor.darInfoProdProveedorEspecifico(pmf.getPersistenceManager(), idOrden, codigo);
	}

	public long aumentarNumeroProductos(long idOrden, String codigoBarras)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlInfProProveedor.aumentarNumeroProductos(pm, idOrden, codigoBarras);
			tx.commit();
			return resp;
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	//------------------------------------------------------------------------
	// Almacenamiento
	//------------------------------------------------------------------------

	public Almacenamiento agregarBodegaSucursal(long idSucursal, String codigoBarras, long idCategoria, long idTipoProducto, double capacidadVol, double capacidadPeso, int cantidad, int nivelReavastecimiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = sqlAlmacenamiento.siguienteId(pm);
			long tuplaInsertadas = sqlAlmacenamiento.agregarBodegaSucursal(pm, idSucursal, codigoBarras, idCategoria, idTipoProducto, capacidadVol, capacidadPeso, cantidad, nivelReavastecimiento);
			tx.commit();

			log.trace("Inserción Almacenamiento a sucursal: " + idSucursal + " : " + tuplaInsertadas +" tuplas Insertadas");
			return new Almacenamiento(id, idSucursal, codigoBarras, idCategoria, idTipoProducto, capacidadVol, capacidadPeso, cantidad, 2, nivelReavastecimiento);
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Almacenamiento agregarEstanteSucursal(long idSucursal, String codigoBarras, long idCategoria, long idTipoProducto, double capacidadVol, double capacidadPeso, int cantidad, int nivelReavastecimiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = sqlAlmacenamiento.siguienteId(pm);
			long tuplaInsertadas = sqlAlmacenamiento.agregarEstanteSucursal(pm, idSucursal, codigoBarras, idCategoria, idTipoProducto, capacidadVol, capacidadPeso, cantidad, nivelReavastecimiento);
			tx.commit();

			log.trace("Inserción Almacenamiento a sucursal: " + idSucursal + " : " + tuplaInsertadas +" tuplas Insertadas");
			return new Almacenamiento(id, idSucursal, codigoBarras, idCategoria, idTipoProducto, capacidadVol, capacidadPeso, cantidad, 1, nivelReavastecimiento);
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarAlmacenDeSucursal(long idSucursal, String codigoBarras, int tipoAlmacenamiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlAlmacenamiento.eliminarAlmacenamientoSucursal(pm, idSucursal, codigoBarras, tipoAlmacenamiento);
			tx.commit();

			return resp;
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() +"\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long acutalizarCantidadAlmacenamiento(long idSucursal, String codigobarras, int cantidad, int tipoAlmacenamiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlAlmacenamiento.actualizarCantidadesAlmacenamiento(pm, idSucursal, codigobarras, cantidad, tipoAlmacenamiento);
			tx.commit();

			return resp;
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Almacenamiento darAlmacenamientoEnEspecifico(long idSucursal, String codigoBarras, int tipoAlma)
	{
		return sqlAlmacenamiento.buscarAlmacenamientoEnEspecifico(pmf.getPersistenceManager(), idSucursal, codigoBarras, tipoAlma);
	}

	public List<Almacenamiento> darAlmacenesPorSucursal(long idSucursal)
	{
		return sqlAlmacenamiento.buscarAlmacenesPorSucursal(pmf.getPersistenceManager(), idSucursal);
	}

	public List<Almacenamiento> darAlmacenesSuperAndes()
	{
		return sqlAlmacenamiento.buscarAlmacenes(pmf.getPersistenceManager());
	}

	//------------------------------------------------------------------------
	// Clientes
	//------------------------------------------------------------------------

	public Clientes agregarCliente(String email, String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlClientes.agregarCliente(pm, email, nombre);
			tx.commit();

			log.trace("Inserción de cliente: " + nombre + ": " + tuplasInsertadas + " tuplasInsertadas");
			return new Clientes(email, nombre);
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarCliente(String email)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlClientes.eliminarCliente(pm, email);
			tx.commit();
			return resp;
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Clientes darClientePorEmail(String email)
	{
		return sqlClientes.darClientePorEmail(pmf.getPersistenceManager(), email);
	}

	public List<Clientes> darClientesporNombre(String nombre)
	{
		return sqlClientes.darClientesConNombreParecido(pmf.getPersistenceManager(), nombre);
	}
	
	public List<Clientes> darClientes()
	{
		return sqlClientes.darClientes(pmf.getPersistenceManager());
	}

	//------------------------------------------------------------------------
	// Personas
	//------------------------------------------------------------------------

	public Personas agregarClientePersona(String email, long documento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplaInsertadas = sqlPersonas.agregarClientePersona(pm, email, documento, 0);
			tx.commit();
			log.trace("Insercion de cliente Persona : " + documento  + " : "+ tuplaInsertadas + " tuplas insertadas");
			return new Personas(email, documento, 0);
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarClientePersona(String email)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlClientes.eliminarCliente(pm, email);
			tx.commit();
			return resp;
		}
		catch(Exception e)
		{
			log.error(("Exception : " + e.getMessage() + "\n" + darDetalleException(e)));
			return - 1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Personas darClientePersonaPorEmail(String email)
	{
		return sqlPersonas.buscarClientePersonaPorEmail(pmf.getPersistenceManager(), email);
	}

	public Personas darClientePersonaPorDocumento(long documento)
	{
		return sqlPersonas.buscarClientePersonaPorDocumento(pmf.getPersistenceManager(), documento);
	}

	public List<Personas> darClientesPersonas()
	{
		return sqlPersonas.buscarClientesPersonas(pmf.getPersistenceManager());
	}

	//------------------------------------------------------------------------
	// Empresas
	//------------------------------------------------------------------------

	public Empresas agregarEmpresa(String email, String nit, String direccion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlEmpresas.agregarClienteEmpresa(pm, email, nit, direccion);
			tx.commit();

			log.trace("Insercion de Empresa  :" + email +" : " + tuplasInsertadas +"tuplas insertadas");

			return new Empresas(email, nit, direccion);
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarClienteEmpresa(String email)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlEmpresas.eliminarClienteEmpresa(pm, email);
			tx.commit();
			return resp;
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Empresas darEmpresaPorEmail(String email)
	{
		return sqlEmpresas.buscarClienteEmpresasPorEmail(pmf.getPersistenceManager(), email);
	}

	public Empresas darEmpresaPorNit(String nit)
	{
		return sqlEmpresas.buscarClienteEmpresasPorNit(pmf.getPersistenceManager(), nit);
	}

	public List<Empresas> darEmpresas()
	{
		return sqlEmpresas.buscarClientesEmpresas(pmf.getPersistenceManager());
	}

	//------------------------------------------------------------------------
	// Ventas
	//------------------------------------------------------------------------

	public Ventas agregarVenta(long idSucursal, String email, Date fechaVenta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = sqlVentas.darSiguienteId(pm);
			long factE = 0;
			if(sqlInformacion.facturaElectronica(pm) == true)
			{
				factE = sqlResoluciones.darConsecutivoActual(pm);
				sqlResoluciones.incrementarConsecutivoActual(pm);
			}
			long tuplaInsertadas = sqlVentas.agregarVenta(pm, idSucursal, email, factE, "", fechaVenta);
			tx.commit();
			log.trace("Insercion de venta en sucursal : " + idSucursal + " : " + tuplaInsertadas + " tuplas insertadas");
			return new Ventas(id, idSucursal, email, factE, "", fechaVenta);
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() +"\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Ventas> darVentasDeUnCliente(String email)
	{
		return sqlVentas.buscarVentasDeCliente(pmf.getPersistenceManager(), email);				
	}

	public List<Ventas> darVentasEnTiempoCliente(String email, Date fechInicio, Date fechaFin)
	{
		return sqlVentas.buscarVentasEntreTiempo(pmf.getPersistenceManager(), fechInicio, fechaFin, email);
	}

	public List<Ventas> darVentasPorSucursal(long idSucursal)
	{
		return sqlVentas.buscarVentasPorSucursal(pmf.getPersistenceManager(), idSucursal);
	}

	//------------------------------------------------------------------------
	// Info Producto Sucursal
	//------------------------------------------------------------------------

	public InfoProdSucursal agregarInfProdSucursal(long idVenta, String codigoBarras)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlInfProSucursal.agregarInfoProductoSucursal(pm, idVenta, codigoBarras);
			tx.commit();

			log.trace("Inserción de InfoProdSucursal de venta : " + idVenta +" : "  + tuplasInsertadas +" tuplas insertadas");
			return new InfoProdSucursal(idVenta, codigoBarras, 1);
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() +"\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarInfoProdSucursal(long idVenta, String codigoBarras)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlInfProSucursal.eliminarInfoProductoSucursal(pm, idVenta, codigoBarras);
			tx.commit();
			return resp;
		}
		catch(Exception e)
		{
			log.error("Exception :" + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public InfoProdSucursal darInfoProdSucursalEspecifico(long idVenta, String codigoBarras)
	{
		return sqlInfProSucursal.darInfoProdSucursalEspecifico(pmf.getPersistenceManager(), idVenta, codigoBarras);
	}

	public List<InfoProdSucursal> darInfoProdSucursalPorVenta(long idVenta)
	{
		return sqlInfProSucursal.darInfoProdSucursalPorVenta(pmf.getPersistenceManager(), idVenta);
	}

	public long aumentarNumeroProductoInfo(long idVenta, String codigoBarras)
	{
		return sqlInfProSucursal.aumentarNumeroProductos(pmf.getPersistenceManager(), idVenta, codigoBarras);
	}

	//------------------------------------------------------------------------
	// Resoluciones
	//------------------------------------------------------------------------

	public Resoluciones agregarResolucion(long numeroResol,Date fechHabili, Date fechaVenc, long inicioCon, long finCon, long numActual)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlResoluciones.agregarResolucion(pm, numeroResol, fechHabili, fechaVenc, inicioCon, finCon, numActual);
			tx.commit();
			log.trace("Insercion de Resolucion:  " + numeroResol + " : " + tuplasInsertadas + " tuplas insertadas");
			return new Resoluciones(numeroResol, fechHabili, fechaVenc, inicioCon, finCon, numActual);
		}
		catch(Exception e)
		{
			log.error("Exception : " +e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	//------------------------------------------------------------------------
	// Información
	//------------------------------------------------------------------------
	public Informacion agregarInformacion(String nit, String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlInformacion.agregarInformacion(pm, nit, nombre);
			tx.commit();
			log.trace("Insercion de Informacion: " + nombre  + " : " + tuplasInsertadas + "tuplas inserradas");
			return new Informacion(nit, nombre, false);
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage() +"\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public Informacion darInformacion()
	{
		return sqlInformacion.darInformacion(pmf.getPersistenceManager());
	}
}

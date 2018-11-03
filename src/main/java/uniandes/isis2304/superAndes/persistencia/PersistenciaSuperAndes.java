package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
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
import uniandes.isis2304.superAndes.negocio.Carrito;
import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Clientes;
import uniandes.isis2304.superAndes.negocio.Empresas;
import uniandes.isis2304.superAndes.negocio.InfoProdCarrito;
import uniandes.isis2304.superAndes.negocio.InfoProdProveedor;
import uniandes.isis2304.superAndes.negocio.InfoProdSucursal;
import uniandes.isis2304.superAndes.negocio.Informacion;
import uniandes.isis2304.superAndes.negocio.OrdenPedido;
import uniandes.isis2304.superAndes.negocio.Personas;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.ProductoProveedor;
import uniandes.isis2304.superAndes.negocio.ProductoRedimible;
import uniandes.isis2304.superAndes.negocio.ProductoSucursal;
import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.Resoluciones;
import uniandes.isis2304.superAndes.negocio.Sucursal;
import uniandes.isis2304.superAndes.negocio.TipoProducto;
import uniandes.isis2304.superAndes.negocio.Ventas;

public class PersistenciaSuperAndes 
{
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());

	public final static String SQL = "javax.jdo.query.SQL";

	private static PersistenciaSuperAndes instance;

	private PersistenceManagerFactory pmf;

	private List<String> tablas;

	private SQLSucursal sqlSucursal;

	private SQLProveedor sqlProveedor;

	private SQLCategoria sqlCategoria;

	private SQLTipoProducto sqlTipoProducto;

	private SQLProducto sqlProducto;

	private SQLProductoProveedor sqlProductoProveedor;

	private SQLProductoSucursal sqlProductoSucursal;

	private SQLProductoRedimible sqlProductoRedimible;

	private SQLPromocion sqlPromocion;

	private SQLOrdenPedido sqlOrdenPedido;

	private SQLInfoProductoProveedor sqlInfProProveedor;

	private SQLAlmacenamiento sqlAlmacenamiento;

	private SQLClientes sqlClientes;

	private SQLPersonas sqlPersonas;

	private SQLEmpresas sqlEmpresas;

	private SQLVentas sqlVentas;

	private SQLInfoProductoSucursal sqlInfProSucursal;

	private SQLResoluciones sqlResoluciones;

	private SQLInformacion sqlInformacion;
	
	private SQLCarrito sqlCarrito;
	
	private SQLInfoProductoCarrito sqlInfProdCarrito;

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
		tablas.add("CARRITO");
		tablas.add("INFO_PRODUCTO_CARRITO");
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
		sqlSucursal = new SQLSucursal(this);
		sqlProveedor = new SQLProveedor(this);
		sqlCategoria = new SQLCategoria(this);
		sqlTipoProducto = new SQLTipoProducto(this);
		sqlProducto = new SQLProducto(this);
		sqlProductoProveedor = new SQLProductoProveedor(this);
		sqlProductoSucursal = new SQLProductoSucursal(this);
		sqlProductoRedimible = new SQLProductoRedimible(this);
		sqlPromocion = new SQLPromocion(this);
		sqlOrdenPedido = new SQLOrdenPedido(this);
		sqlInfProProveedor= new SQLInfoProductoProveedor(this);
		sqlAlmacenamiento = new SQLAlmacenamiento(this);
		sqlClientes = new SQLClientes(this);
		sqlPersonas = new SQLPersonas(this);
		sqlEmpresas = new SQLEmpresas(this);		
		sqlVentas = new SQLVentas(this);
		sqlInfProSucursal = new SQLInfoProductoSucursal(this);
		sqlResoluciones = new SQLResoluciones(this);
		sqlInformacion = new SQLInformacion(this);
		sqlCarrito = new SQLCarrito(this);
		sqlInfProdCarrito = new SQLInfoProductoCarrito(this);

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
	// Sucursal
	//------------------------------------------------------------------------	
	public Sucursal adicionarSucursal(String pNombre, String pDireccion, String pCiudad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm, pNombre, pDireccion, pCiudad);
			tx.commit();

			log.trace("Inserción de Información de sucursal: " + pNombre + ": " + tuplasInsertadas + " tuplas insertada");
			return new Sucursal(pNombre, pDireccion, pCiudad);
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
	
	public long eliminarSucursalPorId(long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlSucursal.eliminarSucursalPorId(pm, id);
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
	
	public Sucursal darSucursalPordId(long id)
	{
		return sqlSucursal.darSucursalPorId(pmf.getPersistenceManager(),id);
	}
	
	public List<Sucursal> darSucursales()
	{
		return sqlSucursal.darSucursales(pmf.getPersistenceManager());
	}
	
	//------------------------------------------------------------------------
	// Proveedor
	//------------------------------------------------------------------------	
	public Proveedor adicionarProveedor(String nit, String pNombre, double calificacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProveedor.adicionarProveedor(pm, nit, pNombre, calificacion);
			tx.commit();

			log.trace("Inserción de Información de proveedor: " + nit + ": " + tuplasInsertadas + " tuplas insertada");
			return new Proveedor(nit, pNombre, calificacion);
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
	
	public long eliminarProveedorPorNit(String nit)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProveedor.eliminarProveedorPorNit(pm, nit);
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
	
	public Proveedor darProveedorPorNit(String nit)
	{
		return sqlProveedor.darProveedorPorNit(pmf.getPersistenceManager(), nit);
	}
	
	public List<Proveedor> darProveedores()
	{
		return sqlProveedor.darProveedores(pmf.getPersistenceManager());
	}
	//------------------------------------------------------------------------
	// Categoria
	//------------------------------------------------------------------------	
	public Categoria adicionarCategoria(String pNombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCategoria.adicionarCategoria(pm, pNombre);
			tx.commit();

			log.trace("Inserción de Información de producto proveedor: " + pNombre + ": " + tuplasInsertadas + " tuplas insertada");
			return new Categoria(pNombre);
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
	
	public long eliminarCategoriaPorId(long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCategoria.eliminarCategoriaPorId(pm, id);
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
	
	public Categoria darCategoriaPorId(long id)
	{
		return sqlCategoria.darCategoriaPorId(pmf.getPersistenceManager(), id);
	}
	
	
	
	//------------------------------------------------------------------------
	// TipoProducto
	//------------------------------------------------------------------------	
	public Producto adicionarProducto(String codigoBarras, long idCategoria, long idTipoProducto, String nombre, String marca,
			String presentacion, int cantidadPresent, String uniMedida, int volumen, int peso)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProducto.adicionarProducto(pm, codigoBarras, idCategoria, idTipoProducto, nombre, marca, 
					presentacion, cantidadPresent, uniMedida, volumen, peso );
			tx.commit();

			log.trace("Inserción de Información de producto: " + codigoBarras + ": " + tuplasInsertadas + " tuplas insertada");
			return new Producto(codigoBarras, idCategoria, idTipoProducto, nombre, marca, 
					presentacion, cantidadPresent, uniMedida, volumen, peso);
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
	
	public long eliminarProductoPorCodBarras(String id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProducto.eliminarProductoPorCodBarras(pm, id);
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
	
	public Producto darProductoPorCodBarras(String id)
	{
		return sqlProducto.darProductoPorCodBarras(pmf.getPersistenceManager(), id);
	}
	
	public List<Producto> darProductos()
	{
		return sqlProducto.darProductos(pmf.getPersistenceManager());
	}
	
	//------------------------------------------------------------------------
	// TipoProducto
	//------------------------------------------------------------------------	
	public TipoProducto adicionarTipoProducto(long idCategoria, String pNombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlTipoProducto.adicionarTipoProducto(pm, idCategoria, pNombre );
			tx.commit();

			log.trace("Inserción de Información de tipo producto: " + pNombre + ": " + tuplasInsertadas + " tuplas insertada");
			return new TipoProducto(idCategoria, pNombre);
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
	
	public long eliminarTipoProductoPorId(long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlTipoProducto.eliminarTipoProductoPorId(pm, id);
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
	// ProductoProveedor
	//------------------------------------------------------------------------	
		public ProductoProveedor adicionarProductoProveedor(long codigoBarras, String nit, int esExclusivo, int precioUnitario, int precioUniMedida)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long tuplasInsertadas = sqlProductoProveedor.adicionarProductoProveedor(pm, codigoBarras, nit, esExclusivo, precioUnitario, precioUniMedida);
				tx.commit();

				log.trace("Inserción de Información de producto proveedor: " + codigoBarras + ": " + tuplasInsertadas + " tuplas insertada");
				return new ProductoProveedor(codigoBarras, nit, esExclusivo, precioUnitario, precioUniMedida);
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
		
		public long eliminarProductoProveedorPorCodBarras(long id)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long resp = sqlProductoProveedor.eliminarProductoProveedorPorCodBarras(pm, id);
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
	
	public ProductoProveedor darProductoProveedorPorCodBarras(long id)
	{
		return sqlProductoProveedor.darProductoProveedorPorCodBarras(pmf.getPersistenceManager(), id);
	}
	
	//------------------------------------------------------------------------
	// ProductoSucursal
	//------------------------------------------------------------------------	
		public ProductoSucursal adicionarProductoSucursal(long codigoBarras, long idSucursal, int precioUnitario, int precioUniMedida,
				int numeroRecompra, int nivelReorde)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long tuplasInsertadas = sqlProductoSucursal.adicionarProductoSucursal(pm, codigoBarras, idSucursal, precioUnitario, precioUniMedida, numeroRecompra, nivelReorde);
				tx.commit();

				log.trace("Inserción de Información de producto sucursal: " + codigoBarras + ": " + tuplasInsertadas + " tuplas insertada");
				return new ProductoSucursal(codigoBarras, idSucursal, precioUnitario, precioUniMedida, numeroRecompra, nivelReorde);
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
		
		public long eliminarProductoSucursalPorCodBarras(long id)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long resp = sqlProductoSucursal.eliminarProductoSucursalPorCodBarras(pm, id);
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
	
	public ProductoSucursal darProductoSucursalPorCodBarras(long id)
	{
		return sqlProductoSucursal.darProductoSucursalPorCodBarras(pmf.getPersistenceManager(), id);
	}
	
	//------------------------------------------------------------------------
	// ProductoRedimible
	//------------------------------------------------------------------------	
		public ProductoRedimible adicionarProductoRedimible(long codigoBarras, long idSucursal, int puntos)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long tuplasInsertadas = sqlProductoRedimible.adicionarProductoRedimible(pm, codigoBarras, idSucursal, puntos);
				tx.commit();

				log.trace("Inserción de Información de producto redimible: " + codigoBarras + ": " + tuplasInsertadas + " tuplas insertada");
				return new ProductoRedimible(codigoBarras, idSucursal, puntos);
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
		
		public long eliminarProductoRedimiblePorCodBarras(long id)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long resp = sqlProductoRedimible.eliminarProductoRedimiblePorCodBarras(pm, id);
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
	
	public ProductoRedimible darProductoRedimiblePorCodBarras(long id)
	{
		return sqlProductoRedimible.darProductoRedimiblePorCodBarras(pmf.getPersistenceManager(), id);
	}
	
	//------------------------------------------------------------------------
	// Promocion
	//------------------------------------------------------------------------	
		public Promocion adicionarPromocion(long idSucursal, String codigoBarras, String nombre, Date fechaInicio,
				Date fechaFin, int tipoPromo, int valorOriginal, int valorPromo)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long tuplasInsertadas = sqlPromocion.adicionarPromocion(pm, idSucursal, codigoBarras, nombre,  fechaInicio,
						 fechaFin, tipoPromo,valorOriginal, valorPromo);
				tx.commit();

				log.trace("Inserción de Información de promocion: " + codigoBarras + ": " + tuplasInsertadas + " tuplas insertada");
				return new Promocion(idSucursal, codigoBarras, nombre,  fechaInicio,
						 fechaFin, tipoPromo,valorOriginal, valorPromo);
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
		
		public long eliminarPromocionPorId(long id)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long resp = sqlPromocion.eliminarPromocionPorId(pm, id);
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
	
	public Promocion darPromocionPorId(long id)
	{
		return sqlPromocion.darPromocionPorId(pmf.getPersistenceManager(), id);
	}
	
	public List<Promocion> darPromociones()
	{
		return sqlPromocion.darPromociones(pmf.getPersistenceManager());
	}
	
	//------------------------------------------------------------------------
	// OrdenPedido
	//------------------------------------------------------------------------	
		public OrdenPedido adicionarOrdenPedido(long idSucursal, String nit, Timestamp fechaExp,
				Timestamp fechaEst, Timestamp fechaEntrega, String estado)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long tuplasInsertadas = sqlOrdenPedido.adicionarOrdenPedido(pm, idSucursal, nit, fechaExp,  fechaEst,
						 fechaEntrega, estado);
				tx.commit();

				log.trace("Inserción de Información de Orden Pedido: " + idSucursal + ": " + tuplasInsertadas + " tuplas insertada");
				return new OrdenPedido(idSucursal, nit, fechaExp,  fechaEst,
						 fechaEntrega, estado);
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
		
		public long eliminarOrdenPedidoPorId(long id)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long resp = sqlOrdenPedido.eliminarOrdenPedidoPorId(pm, id);
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
	
	public OrdenPedido darOrdenPedidoPorId(long id)
	{
		return sqlOrdenPedido.darOrdenPedidoPorId(pmf.getPersistenceManager(), id);
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

		public Almacenamiento agregarAlamacenamientoSucursal(long idSucursal, String codigoBarras, long idCategoria, long idTipoProducto, double capacidadVol, double capacidadPeso, int cantidad, int tipoAlma, int nivelReavastecimiento)
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try
			{
				tx.begin();
				long tuplasInsertadas = sqlAlmacenamiento.agregarAlmacenamientoSucursal(pm, idSucursal, codigoBarras, idCategoria, idTipoProducto, capacidadVol, capacidadPeso, cantidad, tipoAlma, nivelReavastecimiento);
				tx.commit();


				long id = sqlAlmacenamiento.siguienteId(pm);
				log.trace("Inserción Almacenamiento a sucursal: " + idSucursal + " : " + tuplasInsertadas +" tuplas Insertadas");
				return new Almacenamiento(id, idSucursal, codigoBarras, idCategoria, idTipoProducto, capacidadVol, capacidadPeso, cantidad, 2, nivelReavastecimiento);
			}
			catch(Exception e)
			{
				e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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

	public Ventas agregarVenta(long idSucursal, String email)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = sqlVentas.darSiguienteId(pm);
			long factE = 0;
			long tuplaInsertadas = sqlVentas.agregarVenta(pm, idSucursal, email, factE, "");
			tx.commit();
			log.trace("Insercion de venta en sucursal : " + idSucursal + " : " + tuplaInsertadas + " tuplas insertadas");
			return new Ventas(id, idSucursal, email, factE, "", new Date());
		}
		catch(Exception e)
		{
			e.printStackTrace();
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

	public InfoProdSucursal agregarInfProdSucursal(long idVenta, String codigoBarras, int cantidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlInfProSucursal.agregarInfoProductoSucursal(pm, idVenta, codigoBarras, cantidad);
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
	

	//------------------------------------------------------------------------
	// Carrito
	//------------------------------------------------------------------------
	
	public Carrito agregarCarrito(String email, long idSucursal, long precio, String estado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCarrito.adicionarCarrito(pm, email, idSucursal, precio, estado);
			tx.commit();
			log.trace("Insercion de Carrito:  " + email + " : " + tuplasInsertadas + " tuplas insertadas");
			return new Carrito(email,idSucursal, precio, estado);
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
	
	public List<Carrito> darCarritos()
	{
		return sqlCarrito.darCarritos(pmf.getPersistenceManager());
		
	}
	
	public long darIdCarrito(String email, long idSucursal)
	{
		return sqlCarrito.darIdCarrito(pmf.getPersistenceManager(), email, idSucursal);
	}
	
	public void eliminarCarritos()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			
			
			for (Carrito car : darCarritos()) {
				tx.begin();
				if(car.getEstado().equals("Abandonado"))
				{

				   for(InfoProdCarrito infoProd: darInfoProdCarritos())
				   {
					   if(infoProd.getIdCarrito() == darIdCarrito(car.getEmail(), car.getIdSucursal()))
					       sqlAlmacenamiento.actualizarCantidadesAlmacenamiento(pm, car.getIdSucursal(), infoProd.getCodigoBarras() ,infoProd.getCantidad(), 2);
				   }
				   sqlCarrito.eliminarCarritoPorId(pm, car.getEmail(), car.getIdSucursal() );
				}
				tx.commit();
			}
			
			
			
		}
		catch(Exception e)
		{
			log.error("Exception :" + e.getMessage() + "\n" + darDetalleException(e));
			
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
	
	public long abandonarCarrito(String email, long idSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			
			tx.begin();
			long resp = sqlCarrito.actualizarEstadoCarrito(pm, email, idSucursal);
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
	
	//------------------------------------------------------------------------
	// Info Prod Carrito
	//------------------------------------------------------------------------
	
	public List<InfoProdCarrito> darInfoProdCarritos()
	{
		return sqlInfProdCarrito.darInfoProdCarritos(pmf.getPersistenceManager());
	}
	
	
	
	
}

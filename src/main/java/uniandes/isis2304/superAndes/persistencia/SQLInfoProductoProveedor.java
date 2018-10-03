package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.InfoProdProveedor;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto INFO_PRODUCTO_PROVEEDOR de SuperAndes
 * N�rese que es una clase que es s�lo conocida en el paquete de persistencia
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public class SQLInfoProductoProveedor 
{
	//---------------------------------------------------------------------------
	// Constantes
	//---------------------------------------------------------------------------
	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	//---------------------------------------------------------------------------
	// Atr�butos
	//---------------------------------------------------------------------------
	
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes psa;
	
	//---------------------------------------------------------------------------
	// M�todos
	//---------------------------------------------------------------------------
	
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLInfoProductoProveedor( PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarInfoProductoProveedor(PersistenceManager pm, long idOrden, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO INFO_PRODUCTO_PROVEEDOR (Id_orden, codigo_barras, cantidad_producto) values (?,?,1)");
		q.setParameters(idOrden, codigoBarras);
		return (long) q.executeUnique();
	}
	
	public long eliminarInfoProductoProveedor(PersistenceManager pm, long idOrden, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM INFO_PRODUCTO_PROVEEDOR WHERE Id_orden = ? AND codigo_barras = ?");
		q.setParameters(idOrden, codigoBarras);
		return (long) q.executeUnique();
	}
	
	public InfoProdProveedor darInfoProdProveedorEspecifico(PersistenceManager pm, long idOrden, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFO_PRODUCTO_PROVEEDOR WHERE Id_orden = ? AND codigo_barras = ?");
		q.setResultClass(InfoProdProveedor.class);
		q.setParameters(idOrden, codigoBarras);
		return (InfoProdProveedor) q.executeUnique();
	}
	
	public List<InfoProdProveedor> darInfoProdProveedorPorOrden(PersistenceManager pm, long idOrden)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFO_PRODUCTO_PROVEEDOR WHERE Id_orden = ?");
		q.setResultClass(InfoProdProveedor.class);
		q.setParameters(idOrden);
		return (List<InfoProdProveedor>) q.executeUnique();
	}
	
	public long aumentarNumeroProductos(PersistenceManager pm, long idOrden, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "UPDATE INFO_PRODUCTO_PROVEEDOR SET cantidad_producto = cantidad_Producto + 1 "
				+ "WHERE Id_orden = ? AND codigo_barras = ?");
		q.setParameters(idOrden, codigoBarras);
		return (long) q.executeUnique();
	}
}

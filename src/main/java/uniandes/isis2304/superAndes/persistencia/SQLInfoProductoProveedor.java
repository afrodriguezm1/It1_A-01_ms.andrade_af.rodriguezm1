package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.InfoProdProveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto INFO_PRODUCTO_PROVEEDOR de SuperAndes
 * Nórese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public class SQLInfoProductoProveedor 
{
	//---------------------------------------------------------------------------
	// Constantes
	//---------------------------------------------------------------------------
	
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	//---------------------------------------------------------------------------
	// Atríbutos
	//---------------------------------------------------------------------------
	
	private PersistenciaSuperAndes psa;
	
	//---------------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------------
	
	public SQLInfoProductoProveedor( PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarInfoProductoProveedor(PersistenceManager pm, long idOrden, String codigoBarras, int cantidadProducto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO INFO_PRODUCTO_PROVEEDOR (Id_orden, codigo_barras, cantidad_producto) values (?,?,?)");
		q.setParameters(idOrden, codigoBarras, cantidadProducto);
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
}

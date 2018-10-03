package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.InfoProdProveedor;
import uniandes.isis2304.superAndes.negocio.InfoProdSucursal;

public class SQLInfoProductoSucursal 
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
	public SQLInfoProductoSucursal(PersistenciaSuperAndes psa)
	{
		this.psa = psa;	
	}
	
	public long agregarInfoProductoSucursal(PersistenceManager pm, long idVenta, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO INFO_PRODUCTO_SUCURSAL (id_venta, codigo_barras, cantidad_producto) VALUES (?, ?, 1)");
		q.setParameters(idVenta, codigoBarras);
		return (long) q.executeUnique();
	}
	
	public long eliminarInfoProductoSucursal(PersistenceManager pm, long idVenta, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM INFO_PRODUCTO_SUCURSAL WHERE id_venta = ? AND codigo_barras = ?");
		q.setParameters(idVenta, codigoBarras);
		return (long) q.executeUnique();
	}
	
	public InfoProdSucursal darInfoProdSucursalEspecifico(PersistenceManager pm, long idVenta, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFO_PRODUCTO_SUCURSAL WHERE Id_venta = ? AND codigo_barras = ?");
		q.setResultClass(InfoProdSucursal.class);
		q.setParameters(idVenta, codigoBarras);
		return (InfoProdSucursal) q.executeUnique();
	}
	
	public List<InfoProdSucursal> darInfoProdSucursalPorVenta(PersistenceManager pm, long idVenta)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFO_PRODUCTO_SUCURSAL WHERE Id_venta = ?");
		q.setResultClass(InfoProdSucursal.class);
		q.setParameters(idVenta);
		return (List<InfoProdSucursal>) q.executeUnique();
	}
	
	public long aumentarNumeroProductos(PersistenceManager pm, long idVenta, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "UPDATE INFO_PRODUCTO_SUCURSAL SET cantidad_producto = cantidad_Producto + 1 "
				+ "WHERE Id_venta = ? AND codigo_barras = ?");
		q.setParameters(idVenta, codigoBarras);
		return (long) q.executeUnique();
	}
}
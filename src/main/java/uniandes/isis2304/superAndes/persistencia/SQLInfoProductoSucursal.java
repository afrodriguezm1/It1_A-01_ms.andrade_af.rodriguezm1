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
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	//---------------------------------------------------------------------------
	// Atríbutos
	//---------------------------------------------------------------------------

	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes psa;

	//---------------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------------

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLInfoProductoSucursal(PersistenciaSuperAndes psa)
	{
		this.psa = psa;	
	}
	
	public long agregarInfoProductoSucursal(PersistenceManager pm, long idVenta, long idSucursal, String codigoBarras, int cantidad, long precioTotal, long precioUnitario)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO INFO_PRODUCTO_SUCURSAL (idVenta, idSucursal, codigoBarras, cantidadProducto, precioTotal, precioUnitario) VALUES (?, ?, ?, ?, ?, ?)");
		q.setParameters(idVenta, idSucursal, codigoBarras, cantidad, precioTotal, precioUnitario);
		return (long) q.executeUnique();
	}
	
	public long eliminarInfoProductoSucursal(PersistenceManager pm, long idVenta, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM INFO_PRODUCTO_SUCURSAL WHERE idVenta = ? AND codigoBarras = ?");
		q.setParameters(idVenta, codigoBarras);
		return (long) q.executeUnique();
	}
	
	public InfoProdSucursal darInfoProdSucursalEspecifico(PersistenceManager pm, long idVenta, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFO_PRODUCTO_SUCURSAL WHERE IdVenta = ? AND codigoBarras = ?");
		q.setResultClass(InfoProdSucursal.class);
		q.setParameters(idVenta, codigoBarras);
		return (InfoProdSucursal) q.executeUnique();
	}
	
	public List<InfoProdSucursal> darInfoProdSucursalPorVenta(PersistenceManager pm, long idVenta)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFO_PRODUCTO_SUCURSAL WHERE IdVenta = ?");
		q.setResultClass(InfoProdSucursal.class);
		q.setParameters(idVenta);
		return (List<InfoProdSucursal>) q.executeUnique();
	}
	
	public long aumentarNumeroProductos(PersistenceManager pm, long idVenta, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "UPDATE INFO_PRODUCTO_SUCURSAL SET cantidadProducto = cantidadProducto + 1 "
				+ "WHERE IdVenta = ? AND codigoBarras = ?");
		q.setParameters(idVenta, codigoBarras);
		return (long) q.executeUnique();
	}
}

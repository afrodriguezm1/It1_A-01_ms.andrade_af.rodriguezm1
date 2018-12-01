package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.InfoProdCarrito;

public class SQLInfoProductoCarrito 
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
	public SQLInfoProductoCarrito(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarInfoProdCarrito(PersistenceManager pm, long idCarrito, String emailCliente, long idSucursal, String codigoBarras, int cantidad, long precioTotal, long precioUnitario)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO INFO_PRODUCTO_CARRITO (IDCARRITO, EMAILCLIENTE, IDSUCURSAL, CODIGOBARRAS, CANTIDAD, PRECIOTOTAL, precioUnitario)"
				+ "VALUES (?,?,?,?,?,?)");
		q.setParameters(idCarrito,emailCliente,idSucursal, codigoBarras, cantidad, precioTotal, precioUnitario);
		return (long) q.executeUnique();
	}
	
	public long eliminarInfoProdCarrito(PersistenceManager pm, long idCarrito, String emailCliente, long idSucursal, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM INFO_PRODUCTO_CARRITO WHERE IDCARRITO = ? AND CODIGOBARRAS = ? "
				+ "AND EMAIL_CLIENTE = ? AND ID_SUCURSAL = ?");
		q.setParameters(idCarrito, codigoBarras,emailCliente, idSucursal);
		return (long) q.executeUnique();
	}
	
	public long eliminarTodosInfoProdCarrito(PersistenceManager pm, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM INFO_PRODUCTO_CARRITO WHERE IDCARRITO = ?");
		q.setParameters(idCarrito);
		return (long) q.execute();
	}
	
	public long actualizarCantidadProd(PersistenceManager pm, long idCarrito, String codigoBarras, int cantidad)
	{
		Query q = pm.newQuery(SQL, "UPDATE INFO_PRODUCTO_CARRITO SET CANTIDAD = CANTIDAD + (?) WHERE IDCARRITO = ? AND CODIGOBARRAS = ?");
		q.setParameters(cantidad, idCarrito, codigoBarras);
		return (long) q.execute();
	}
	
	/**
	 * Dar toda la info de todos los carritos
	 */
	public List<InfoProdCarrito> darInfoProdCarritos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFO_PRODUCTO_CARRITO");
		q.setResultClass(InfoProdCarrito.class);
		return (List<InfoProdCarrito>) q.executeList();
	}
	
	/**
	 * Dar toda la info de todos los carritos
	 */
	public List<InfoProdCarrito> darInfoProdCarritosId(PersistenceManager pm, long idCarrito, String emailCliente, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFO_PRODUCTO_CARRITO WHERE IDCARRITO = ? AND EMAILCLIENTE = ? AND IDSUCURSAL = ?");
		q.setParameters(idCarrito, emailCliente, idSucursal);
		q.setResultClass(InfoProdCarrito.class);
		return (List<InfoProdCarrito>) q.executeList();
	}
	
	/**
	 * Dar toda la info de todos los carritos
	 */
	public InfoProdCarrito darInfoProdCarritoId(PersistenceManager pm, long idCarrito, String emailCliente, long idSucursal, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFO_PRODUCTO_CARRITO WHERE IDCARRITO = ? AND EMAILCLIENTE = ? AND IDSUCURSAL = ? AND CODIGOBARRAS = ?");
		q.setParameters(idCarrito, emailCliente, idSucursal, codigoBarras);
		q.setResultClass(InfoProdCarrito.class);
		return (InfoProdCarrito) q.executeUnique();
	}
}

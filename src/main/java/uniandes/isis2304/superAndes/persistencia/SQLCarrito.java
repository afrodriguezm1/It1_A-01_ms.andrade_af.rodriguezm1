package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Carrito;
import uniandes.isis2304.superAndes.negocio.Sucursal;

public class SQLCarrito 
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
	public SQLCarrito(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}

	/**
	 * 
	 * @param pm
	 * @param email
	 * @param idSucursal
	 * @param precio
	 * @param estado
	 * @return
	 */
	public long adicionarCarrito(PersistenceManager pm, String emailCliente, long idSucursal, long precio, String estado)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO CARRITO (EmailCliente, IdSucursal, Precio, Estado) values (?, ?, ?, ?)");
		q.setParameters(emailCliente, idSucursal, precio, estado);
		return (long) q.executeUnique();
	}

	/**
	 * Dar carrito por email de cliente
	 * @param pm
	 * @param email
	 * @return
	 */
	public Carrito darCarritoPorId(PersistenceManager pm, String emailCliente, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARRITO WHERE EMAILCLIENTE = ? AND idSucursal = ?");
		q.setResultClass(Carrito.class);
		q.setParameters(emailCliente, idSucursal);
		return (Carrito) q.executeUnique();
	}
	
	/**
	 * Dar carrito por email de cliente
	 * @param pm
	 * @param email
	 * @return
	 */
	public long darIdCarrito(PersistenceManager pm, String emailCliente, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT ID FROM CARRITO WHERE EmailCliente = ? AND IdSucursal = ?");
		q.setParameters(emailCliente, idSucursal);
		return (long) q.executeUnique();
	}

	/**
	 * Elimina un carrito por email
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarCarritoPorId (PersistenceManager pm, String emailCliente, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM CARRITO WHERE EmailCCliente = ? AND IdSucursal = ?");
		q.setParameters(emailCliente, idSucursal );
		return (long) q.executeUnique();
	}

	/**
	 * Dar todos los carritos
	 */
	public List<Carrito> darCarritos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARRITO");
		q.setResultClass(Carrito.class);
		return (List<Carrito>) q.executeList();
	}

	public long actualizarPrecioCarrito(PersistenceManager pm, String emailCliente, long idSucursal, long precio)
	{
		Query q = pm.newQuery(SQL, "UPDATE CARRITO SET PRECIO = PRECIO + (?) WHERE EMAILCLIENTE = ? AND IDSUCURSAL = ?");
		q.setParameters(precio, emailCliente, idSucursal);
		return (long) q.execute();
	}

	public long actualizarEstadoCarrito(PersistenceManager pm, String emailCliente, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "UPDATE CARRITO SET ESTADO = 'Abandonado' WHERE EMAILCLIENTE = ? AND IDSUCURSAL = ?");
		q.setParameters(emailCliente, idSucursal);
		return (long) q.execute();

	}
}
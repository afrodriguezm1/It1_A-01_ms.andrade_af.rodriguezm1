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
	public long adicionarCarrito(PersistenceManager pm, String email, long idSucursal, long precio, String estado)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO CARRITO (Email_Cliente, Id_Sucursal, Precio, Estado) values (?, ?, ?, ?)");
		q.setParameters(email, idSucursal, precio, estado);
		return (long) q.executeUnique();
	}

	/**
	 * Dar carrito por email de cliente
	 * @param pm
	 * @param email
	 * @return
	 */
	public Carrito darCarritoPorId(PersistenceManager pm, String email, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARRITO WHERE Email_Cliente = ? AND Id_Sucursal = ?");
		q.setResultClass(Carrito.class);
		q.setParameters(email, idSucursal);
		return (Carrito) q.executeUnique();
	}
	
	/**
	 * Dar carrito por email de cliente
	 * @param pm
	 * @param email
	 * @return
	 */
	public long darIdCarrito(PersistenceManager pm, String email, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT ID FROM CARRITO WHERE Email_Cliente = ? AND Id_Sucursal = ?");
		q.setParameters(email, idSucursal);
		return (long) q.executeUnique();
	}

	/**
	 * Elimina un carrito por email
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarCarritoPorId (PersistenceManager pm, String email, long idSucursal)
	{
		Query toDelete = pm.newQuery(SQL, "SELECT ID FROM CARRITO WHERE  " );
		Query q = pm.newQuery(SQL, "DELETE FROM CARRITO WHERE Email_Cliente = ? AND Id_Sucursal = ?");
		q.setParameters(email, idSucursal );
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

	public long actualizarPrecioCarrito(PersistenceManager pm, String email, long idSucursal, long precio)
	{
		Query q = pm.newQuery(SQL, "UPDATE CARRITO SET PRECIO = PRECIO + (?) WHERE EMAIL_CLIENTE = ? AND ID_SUCURSAL = ?");
		q.setParameters(precio, email, idSucursal);
		return (long) q.execute();
	}

	public long actualizarEstadoCarrito(PersistenceManager pm, String email, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "UPDATE CARRITO SET ESTADO = 'Abandonado' WHERE EMAIL_CLIENTE = ? AND ID_SUCURSAL = ?");
		q.setParameters(email, idSucursal);
		return (long) q.execute();

	}
}
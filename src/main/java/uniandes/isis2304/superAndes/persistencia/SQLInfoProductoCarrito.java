package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLInfoProductoCarrito 
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
	public SQLInfoProductoCarrito(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarInfoProdCarrito(PersistenceManager pm, long idCarrito, String codigoBarras, int cantidad)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO INFO_PRODUCTO_CARRITO (ID_CARRITO, CODIGO_BARRAS, CANTIDAD, PRECIO_TOTAL)"
				+ "VALUES (?,?,?,?)");
		q.setParameters(idCarrito, codigoBarras, cantidad, 0);
		return (long) q.executeUnique();
	}
}

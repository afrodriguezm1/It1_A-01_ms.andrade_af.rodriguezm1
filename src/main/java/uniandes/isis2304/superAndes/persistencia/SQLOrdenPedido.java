package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.OrdenPedido;
import uniandes.isis2304.superAndes.negocio.Promocion;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Proveedor de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mario Andrade
 */
public class SQLOrdenPedido
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLOrdenPedido (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	/**
	 * 
	 * @param pm
	 * @param pId
	 * @param pNombre
	 * @return
	 */
	public long adicionarOrdenPedido(PersistenceManager pm, long idSucursal, String nitProveedor, Timestamp fechaExp,
			Timestamp fechaEst, Timestamp fechaEntrega, String estado)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO ORDEN_PEDIDO (idSucursal, nitProveedor, fechaExp, fechaEst, fechaEntrega, estado) "
        		+ "values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idSucursal, nitProveedor, fechaExp, fechaEst, fechaEntrega, estado);
        return (long) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public OrdenPedido darOrdenPedidoPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ORDEN_PEDIDO WHERE Id = ?");
		q.setResultClass(OrdenPedido.class);
		q.setParameters(id);
		return (OrdenPedido) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarOrdenPedidoPorId(PersistenceManager pm, long id )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM ORDEN_PEDIDO WHERE Id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
}

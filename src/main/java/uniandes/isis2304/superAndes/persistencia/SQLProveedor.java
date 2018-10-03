package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Proveedor de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mario Andrade
 */
public class SQLProveedor
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
	public SQLProveedor (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	/**
	 * 
	 * @param pm
	 * @param pId
	 * @param pNombre
	 * @param pDireccion
	 * @param pCiudad
	 * @return
	 */
	public long adicionarProveedor(PersistenceManager pm, String nit, String pNombre, double calificacion)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO PROVEEDOR (Nit, Nombre, Calificacion) values ( ?, ?, ?)");
        q.setParameters(nit, pNombre, calificacion);
        return (long) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public Proveedor darProveedorPorNit(PersistenceManager pm, String nit)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PROVEEDOR WHERE Nit = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(nit);
		return (Proveedor) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarProveedorPorNit(PersistenceManager pm, String nit )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM PROVEEDOR WHERE Nit = ?");
        q.setParameters(nit);
        return (long) q.executeUnique();
	}
}
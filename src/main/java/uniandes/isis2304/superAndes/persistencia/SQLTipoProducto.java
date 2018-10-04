package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Proveedor de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mario Andrade
 */
public class SQLTipoProducto
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
	public SQLTipoProducto (PersistenciaSuperAndes pp)
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
	public long adicionarTipoProducto(PersistenceManager pm, long idCategoria, String pNombre)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO  TIPO_PRODUCTO (Id_categoria, Nombre) values ( ?, ?)");
        q.setParameters(idCategoria, pNombre);
        return (long) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public TipoProducto darTipoProductoPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM TIPO_PRODUCTO WHERE Id = ?");
		q.setResultClass(TipoProducto.class);
		q.setParameters(id);
		return (TipoProducto) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarTipoProductoPorId(PersistenceManager pm, long id )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM TIPO_PRODUCTO WHERE Id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
}

package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoRedimible;
import uniandes.isis2304.superAndes.negocio.ProductoSucursal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Proveedor de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mario Andrade
 */
public class SQLProductoRedimible
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
	public SQLProductoRedimible (PersistenciaSuperAndes pp)
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
	public long adicionarProductoRedimible(PersistenceManager pm,long codigoBarras, long idSucursal, int puntos)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO PRODUCTO_REDIMIBLE (Codigo_barras, Id_sucursal, Puntos) "
        		+ "values (?, ?, ?)");
        q.setParameters(codigoBarras, idSucursal, puntos);
        return (long) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public ProductoRedimible darProductoRedimiblePorCodBarras(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PRODUCTO_REDIMIBLE WHERE Codigo_barras = ?");
		q.setResultClass(ProductoRedimible.class);
		q.setParameters(id);
		return (ProductoRedimible) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarProductoRedimiblePorCodBarras(PersistenceManager pm, long id )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM PRODUCTO_REDIMIBLE WHERE Codigo_barras = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
}

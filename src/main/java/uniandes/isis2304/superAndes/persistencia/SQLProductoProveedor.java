package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.ProductoProveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Proveedor de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mario Andrade
 */
public class SQLProductoProveedor
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
	public SQLProductoProveedor (PersistenciaSuperAndes pp)
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
	public long adicionarProductoProveedor(PersistenceManager pm, long codigoBarras, String nit, int esExclusivo, int precioUnitario, int precioUniMedida)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO PRODUCTO_PROVEEDOR (Codigo_barras, Nit, Es_exclusivo, Precio_unitario, Precio_unidad_medida) values (?, ?, ?, ?, ?)");
        q.setParameters(codigoBarras, nit, esExclusivo, precioUnitario, precioUniMedida);
        return (long) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public ProductoProveedor darProductoProveedorPorCodBarras(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PRODUCTO_PROVEEDOR WHERE Codigo_barras = ?");
		q.setResultClass(ProductoProveedor.class);
		q.setParameters(id);
		return (ProductoProveedor) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarProductoProveedorPorCodBarras(PersistenceManager pm, long id )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM PRODUCTO_PROVEEDOR WHERE Codigo_barras = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
}

package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoProveedor;
import uniandes.isis2304.superAndes.negocio.ProductoSucursal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Proveedor de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mario Andrade
 */
public class SQLProductoSucursal
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
	public SQLProductoSucursal (PersistenciaSuperAndes pp)
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
	public long adicionarProductoSucursal(PersistenceManager pm,long codigoBarras, long idSucursal, int precioUnitario, int precioUniMedida,
			int numeroRecompra, int nivelReorden)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO PRODUCTO_SUCURSAL (Codigo_barras, Id_sucursal, Precio_unitario, Precio_unidad_medida, Numero_recompra, Nive_Reorden) "
        		+ "values (?, ?, ?, ?, ?, ?)");
        q.setParameters(codigoBarras, idSucursal, precioUnitario, precioUniMedida, numeroRecompra, nivelReorden);
        return (long) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public ProductoSucursal darProductoSucursalPorCodBarras(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PRODUCTO_SUCURSAL WHERE Codigo_barras = ?");
		q.setResultClass(ProductoSucursal.class);
		q.setParameters(id);
		return (ProductoSucursal) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarProductoSucursalPorCodBarras(PersistenceManager pm, long id )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM PRODUCTO_SUCURSAL WHERE Codigo_barras = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
}

package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Proveedor de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mario Andrade
 */
public class SQLProducto
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
	public SQLProducto (PersistenciaSuperAndes pp)
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
	public long adicionarProducto(PersistenceManager pm, long codigoBarras, long idCategoria, long idTipoProducto, String nombre, String marca,
			String presentacion, int cantidadPresent, String uniMedida, int volumen, int peso)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO PRODUCTO (Codigo_barras, Id_categoria, Id_tipo_producto, Nombre, Marca, Presentacion, Cantidad_presen, Uni_medida, Volumen, Peso) "
        		+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(codigoBarras, idCategoria, idTipoProducto, nombre, marca,
    			presentacion, cantidadPresent,uniMedida, volumen, peso);
        return (long) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public Producto darProductoPorCodBarras(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM  PRODUCTO WHERE Codigo_barras = ?");
		q.setResultClass(Producto.class);
		q.setParameters(id);
		return (Producto) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarProductoPorCodBarras(PersistenceManager pm, long id )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM PRODUCTO WHERE Codigo_barras = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
}

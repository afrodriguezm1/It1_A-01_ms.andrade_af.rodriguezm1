package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Carrito;
import uniandes.isis2304.superAndes.negocio.Categoria;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Proveedor de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mario Andrade
 */
public class SQLCategoria
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
	public SQLCategoria (PersistenciaSuperAndes pp)
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
	public long adicionarCategoria(PersistenceManager pm,  String pNombre)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO  CATEGORIA (Nombre) values (?)");
        q.setParameters(pNombre);
        return (long) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public Categoria darCategoriaPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM  CATEGORIA  WHERE Id = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(id);
		return (Categoria) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarCategoriaPorId(PersistenceManager pm, String id )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM  CATEGORIA WHERE Nombre = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	
	/**
	 * @return 
	 * 
	 *
	 */
	public List<Categoria> darCategorias(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CATEGORIA");
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();
	}
}

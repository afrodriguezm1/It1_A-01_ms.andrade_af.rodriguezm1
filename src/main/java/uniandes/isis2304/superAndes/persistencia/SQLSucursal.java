package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.Sucursal;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto Sucursal de SuperAndes
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 * 
 * @author Mario Andrade
 */
public class SQLSucursal 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLSucursal (PersistenciaSuperAndes pp)
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
	public long adicionarSucursal(PersistenceManager pm,String pNombre, String pDireccion, String pCiudad)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO SUCURSAL (Nombre, Direccion, Ciudad) values (?, ?, ?)");
        q.setParameters(pNombre, pDireccion, pCiudad);
        return (long) q.executeUnique();
	}
	/**
	 * Dar sucursal por ID
	 * @param pm
	 * @param id
	 * @return
	 */
	public Sucursal darSucursalPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SUCURSAL WHERE Id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(id);
		return (Sucursal) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarSucursalPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM SUCURSAL WHERE Id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	
	/**
	 * 
	 */
	public List<Sucursal> darSucursales(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SUCURSAL");
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>) q.executeList();
	}
	
	
	
	
}

package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoSucursal;
import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Proveedor de SuperAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mario Andrade
 */
public class SQLPromocion
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
	public SQLPromocion (PersistenciaSuperAndes pp)
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
	public long adicionarPromocion(PersistenceManager pm, long idSucursal, String codigoBarras, String nombre, Date fechaInicio,
			Date fechaFin, int tipoPromo, int valorOriginal, int valorPromo)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO PROMOCION (IdSucursal, CodigoBarras, Nombre, FechaInicio, FechaFin, TipoPromo, Valor1, Valor2) "
        		+ "values ( ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(idSucursal, codigoBarras, nombre, fechaInicio, fechaFin, tipoPromo, valorOriginal, valorPromo);
        return (long) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public Promocion darPromocion(PersistenceManager pm, String codBarras, long idSucursal, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PROMOCION WHERE CodigoBarras = ? AND  IdSucursal = ? AND Nombre = ?");
		q.setResultClass(Promocion.class);
		q.setParameters(codBarras, idSucursal, nombre);
		return (Promocion) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarPromocion(PersistenceManager pm, String codBarras, long idSucursal, String nombre )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM PROMOCION WHERE CodigoBarras = ? AND  IdSucursal = ? AND Nombre = ?");
        q.setParameters(codBarras, idSucursal, nombre);
        return (long) q.executeUnique();
	}
	
	/**
	 * 
	 */
	public List<Promocion> darPromociones(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PROMOCION");
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
}

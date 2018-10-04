package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoSucursal;
import uniandes.isis2304.superAndes.negocio.Promocion;

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
	public long adicionarPromocion(PersistenceManager pm, long idSucursal, long codigoBarras, String nombre, Timestamp fechaInicio,
			Timestamp fechaFin, int tipoPromo, int valorOriginal, int valorPromo)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO PROMOCION (Id_sucursal, Codigo_barras, Nombre, Fecha_inicio, Fecha_fin, Tipo_promo, Valor1, Valor2) "
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
	public Promocion darPromocionPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PROMOCION WHERE Id = ?");
		q.setResultClass(Promocion.class);
		q.setParameters(id);
		return (Promocion) q.executeUnique();
	}
	/**
	 * 
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarPromocionPorId(PersistenceManager pm, long id )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM PROMOCION WHERE Id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
}

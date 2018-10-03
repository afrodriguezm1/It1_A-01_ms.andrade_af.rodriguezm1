package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Informacion;

public class SQLInformacion 
{
	//---------------------------------------------------------------------------
	// Constantes
	//---------------------------------------------------------------------------

	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	//---------------------------------------------------------------------------
	// Atríbutos
	//---------------------------------------------------------------------------

	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes psa;

	//---------------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------------

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLInformacion(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarInformacion(PersistenceManager pm, String nit, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO INFORMACION (nit, nombre, facturaE) VALUES (?,?, 0)");
		q.setParameters(nit, nombre);
		return(long) q.executeUnique();
	}
	
	public Informacion darInformacion(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFORMACION");
		q.setResultClass(Informacion.class);
		return (Informacion) q.executeUnique();
	}
	
	public boolean facturaElectronica(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM INFORMACION");
		q.setResultClass(Informacion.class);
		return ((Informacion) q.executeUnique()).getFacturaE();
	}
}

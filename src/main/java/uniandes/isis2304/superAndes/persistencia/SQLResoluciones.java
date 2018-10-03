package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Resoluciones;

public class SQLResoluciones 
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
	public SQLResoluciones(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarResolucion(PersistenceManager pm, long numeroResolucion, Date fechaHabilitacion, Date fechaVencimiento, long inicioConsecutivo, long finConsecutivo, long numeroActual)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO RESOLUCIONES (numero_resolucion, fecha_habilitacion, fecha_vencimiento, Inicio_cense, fin_conse"
				+ ", numero_act) VALUES (?,?,?,?,?,?)");
		q.setParameters(numeroActual, fechaHabilitacion, fechaVencimiento, inicioConsecutivo, finConsecutivo, numeroActual);
		return(long) q.executeUnique();
	}
	
	public long darConsecutivoActual(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL , "SELECT * FROM RESOLUCIONES WHERE ROWID = ( SELECT MAX(ROWID) FROM RESOLUCIONES)");
		q.setResultClass(Resoluciones.class);
		Resoluciones res = (Resoluciones)q.executeUnique();
		return res.getNumActual();
	}
	
	public long incrementarConsecutivoActual(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "UPDATE RESULUCIONES SET numero_act = numero_act + 1 WHERE ROWID = ( SELECT MAX(ROWID) FROM RESOLUCIONES)");
		return (long) q.executeUnique();
	}
}

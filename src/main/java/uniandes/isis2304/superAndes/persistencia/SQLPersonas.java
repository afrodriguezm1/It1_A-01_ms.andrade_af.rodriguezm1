package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Personas;

public class SQLPersonas 
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
	public SQLPersonas(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarClientePersona(PersistenceManager pm, String email, long documento, long puntaje)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO PERSONAS (email, documento, puntaje) VALUES (?,?,?)");
		q.setParameters(email, documento, puntaje);
		return (long ) q.executeUnique();
	}
	
	public long eliminarClientePersona(PersistenceManager pm, String email)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM PERSONAS WHERE email = ?");
		q.setParameters(email);
		return (long) q.executeUnique();
	}
	
	public long agregarPuntosClientePersona(PersistenceManager pm, String email, long puntaje)
	{
		Query q = pm.newQuery(SQL, "UPDATE PERSONAS SET puntaje =  puntaje + (?) WHERE email = ?");
		q.setParameters(puntaje, email);
		return (long) q.executeUnique();
	}
	
	public Personas buscarClientePersonaPorEmail(PersistenceManager pm, String email)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PERSONAS WHERE email = ?");
		q.setResultClass(Personas.class);
		q.setParameters(email);
		return (Personas) q.executeUnique();
	}
	
	public Personas buscarClientePersonaPorDocumento(PersistenceManager pm, long documento)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PERSONAS WHERE documento = ?");
		q.setResultClass(Personas.class);
		q.setParameters(documento);
		return (Personas) q.executeUnique();
	}
	
	public List<Personas> buscarClientesPersonas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL , "SELECT * FROM PERSONAS");
		q.setResultClass(Personas.class);
		return (List<Personas>) q.executeUnique();
	}

}

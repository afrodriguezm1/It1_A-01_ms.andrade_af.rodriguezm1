package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Clientes;

public class SQLClientes 
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
	public SQLClientes(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarCliente(PersistenceManager pm, String email, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO CLIENTES (email, nombre) values (?,?)");
		q.setParameters(email, nombre);
		return (long) q.executeUnique();
	}
	
	public long eliminarCliente(PersistenceManager pm, String email)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM CLIENTES WHERE email = ?");
		q.setParameters(email);
		return (long) q.executeResultUnique();
	}
	
	public Clientes darClientePorEmail(PersistenceManager pm, String email)
	{
		Query q = pm.newQuery(SQL, "SELEC * FROM CLIENTES WHERE email = ?");
		q.setResultClass(Clientes.class);
		q.setParameters(email);
		return (Clientes) q.executeUnique();
	}
	
	public List<Clientes> darClientesConNombreParecido(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CLIENTES WHERE nombre LIKE '%?%'");
		q.setResultClass(Clientes.class);
		return (List<Clientes>) q.executeList();
	}
	
	public List<Clientes> darClientes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CLIENTES");
		q.setResultClass(Clientes.class);
		return (List<Clientes>) q.executeList();
	}
}

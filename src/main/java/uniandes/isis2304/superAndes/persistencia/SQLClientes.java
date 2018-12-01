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
	
	public long agregarCliente(PersistenceManager pm, String email, String nombre, String documento, String direccion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO CLIENTES (email, nombre, documento, direccion) values (?,?,?,?)");
		q.setParameters(email, nombre, documento, direccion);
		return (long) q.executeUnique();
	}
	
	public long eliminarCliente(PersistenceManager pm, String documento)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM CLIENTES WHERE documento = ?");
		q.setParameters(documento);
		return (long) q.executeUnique();
	}
	
	public Clientes darClientePorDocumento(PersistenceManager pm, String documento)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CLIENTES WHERE documento = ?");
		q.setResultClass(Clientes.class);
		q.setParameters(documento);
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
	
	public List<Clientes> reqFunC10(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "");
		q.setResultClass(Clientes.class);
		return (List<Clientes>) q.executeList();
	}
}

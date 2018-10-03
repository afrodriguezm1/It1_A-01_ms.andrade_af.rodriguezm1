package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Empresas;

public class SQLEmpresas 
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
	public SQLEmpresas(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarClienteEmpresa(PersistenceManager pm, String email, String nit, String direccion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO EMPRESAS (email, nit, direccion) VALUES (?, ?, ?)");
		q.setParameters(email, nit, direccion);
		return (long) q.executeUnique();
	}
	
	public long eliminarClienteEmpresa(PersistenceManager pm, String email)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM EMPRESAS WHERE email = ?");
		q.setParameters(email);
		return (long) q.executeUnique();
	}
	
	public Empresas buscarClienteEmpresasPorEmail(PersistenceManager pm, String email)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM EMPRESAS WHERE email = ?");
		q.setResultClass(Empresas.class);
		q.setParameters(email);
		return (Empresas) q.executeUnique();
	}
	
	public Empresas buscarClienteEmpresasPorNit(PersistenceManager pm, String nit)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM EMPRESAS WHERE nit = ?");
		q.setResultClass(Empresas.class);
		q.setParameters(nit);
		return (Empresas) q.executeUnique();
	}
	
	public List<Empresas> buscarClientesEmpresas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM EMPRESAS");
		q.setResultClass(Empresas.class);
		return (List<Empresas>) q.executeUnique();
	}
}

package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Ventas;

public class SQLVentas 
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
	public SQLVentas(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarVenta(PersistenceManager pm, long idSucursal, String emailCliente, long precio)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO VENTAS (idSucursal, emailCliente, fecha_venta, precio)"
				+ "VALUES (?, ?, TO_DATE(SYSDATE, 'DD-MON-YYYY'), ?)");
		q.setParameters(idSucursal, emailCliente, precio);
		return (long) q.executeUnique();
	}
	
	public List<Ventas> buscarVentasDeCliente(PersistenceManager pm, String email)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM VENTAS WHERE emailCliente = ?");
		q.setResultClass(Ventas.class);
		q.setParameters(email);
		return (List<Ventas>)q.executeUnique();
	}
	
	public List<Ventas> buscarVentasEntreTiempo(PersistenceManager pm, Date fechaInicio, Date fechaFin, String email)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM VENTAS WHERE emailCliente = ? AND fechaVenta BETWEEN ? AND ?");
		q.setResultClass(Ventas.class);
		q.setParameters(email, fechaInicio, fechaFin);
		return (List<Ventas>) q.executeUnique();
	}
	
	public List<Ventas> buscarVentasPorSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELEC * FROM VENTAS WHERE idSucursal = ?");
		q.setResultClass(Ventas.class);
		q.setParameters(idSucursal);
		return (List<Ventas>) q.executeUnique();
	}
	
	public long darSiguienteId(PersistenceManager pm)
	{
		try
		{
			Query q = pm.newQuery(SQL, "SELECT id FROM VENTAS WHERE ROWID = (SELECT MAX(ROWID) FROM VENTAS)");
			q.setResultClass(Ventas.class);
			return((Ventas)q.executeUnique()).getId() + 1;
		}
		catch(Exception e)
		{}
		return 1;
		
	}
	
	public long darIdActual(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT id FROM VENTAS WHERE ROWID = (SELECT MAX(ROWID) FROM VENTAS)");
		q.setResultClass(Ventas.class);
		return((Ventas)q.executeUnique()).getId();
	}
}

package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.datanucleus.CDIHandler;

import uniandes.isis2304.superAndes.negocio.Almacenamiento;
import uniandes.isis2304.superAndes.negocio.Ventas;

public class SQLAlmacenamiento 
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
	public SQLAlmacenamiento(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}

	public long agregarAlmacenamientoSucursal(PersistenceManager pm, long idSucursal, String codigoBarrasProducto, long idCategoriaProd, long idTipoProd, double capaVol, double capaPeso,  int cantidad, int tipoAlma, int nivelReavast)
	{
		Query b = pm.newQuery(SQL, "INSERT INTO ALMACENAMIENTO (idSucursal, codigoBarrasProducto, idCategoriaProd, idTipoProd, capaVol"
				+ ", capaPeso, cantidad, tipoAlma, nivelReavast) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		b.setParameters(idSucursal, codigoBarrasProducto, idCategoriaProd, idTipoProd, capaVol, capaPeso, cantidad, tipoAlma, nivelReavast);
		return (long) b.executeUnique();
	}

	public long eliminarAlmacenamientoSucursal(PersistenceManager pm, long idSucursal, String codigoBarrasProducto, int tipoAlma)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM ALMACENAMIENTO WHERE idSucursal = ? AND codigo_barrasProducto = ? AND tipoAlma = ?");
		q.setParameters(idSucursal, codigoBarrasProducto, tipoAlma);
		return(long) q.executeUnique();
	}

	public long actualizarCantidadesAlmacenamiento(PersistenceManager pm, long idSucursal, String codigoBarrasProducto, int cantidad, int tipoAlma)
	{
		Query q = pm.newQuery(SQL, "UPDATE ALMACENAMIENTO SET cantidad = cantidad + (?) WHERE idSucursal = ? AND codigobarrasProducto = ? AND tipoAlma = ?");
		q.setParameters(cantidad, idSucursal, codigoBarrasProducto, tipoAlma);
		return(long) q.executeUnique();
	}
	
	public Almacenamiento buscarAlmacenamientoEnEspecifico(PersistenceManager pm, long idSucursal, String codigoBarras, int tipoAlma)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ALMACENAMIENTO WHERE idSucursal = ? AND codigoBarrasProducto = ? AND tipoAlma = ?");
		q.setResultClass(Almacenamiento.class);
		q.setParameters(idSucursal, codigoBarras, tipoAlma);
		return (Almacenamiento) q.executeUnique();
	}
	
	public List<Almacenamiento> buscarAlmacenesPorSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ALMACENAMIENTO WHERE idSucursal = ?");
		q.setResultClass(Almacenamiento.class);
		q.setParameters(idSucursal);
		return (List<Almacenamiento>) q.executeList();
	}
	
	public List<Almacenamiento> buscarAlmacenes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ALMACENAMIENTO");
		q.setResultClass(Almacenamiento.class);
		return (List<Almacenamiento>) q.executeList();
	}
	
	public long siguienteId(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT id FROM ALMACENAMIENTO WHERE ROWID = (SELECT MAX(ROWID) FROM ALMACENAMIENTO)");
		q.setResultClass(Almacenamiento.class);
		return((Almacenamiento)q.executeUnique()).getId() + 1;
	}
}

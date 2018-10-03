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

	public long agregarBodegaSucursal(PersistenceManager pm, long idSucursal, String codigoBarrasProducto, long idCategoria, long idTipoProducto, double capacidadVol, double capacidadPeso, int cantidad, int nivelReavastecimiento)
	{
		Query b = pm.newQuery(SQL, "INSERT INTO ALMACENAMIENTO (id_sucursal, codigo_barras_producto, id_categoria, id_tipo_prod, capa_vol"
				+ ", capa_peso, cantidad, tipo_almacen, nivel_reavast) VALUES (?, ?, ?, ?, ?, ?, ?, 2, ?)");
		b.setParameters(idSucursal, codigoBarrasProducto, idCategoria, idTipoProducto, capacidadVol, capacidadPeso, cantidad, nivelReavastecimiento);
		return (long) b.executeUnique();
	}

	public long agregarEstanteSucursal(PersistenceManager pm, long idSucursal, String codigoBarrasProducto, long idCategoria, long idTipoProducto, double capacidadVol, double capacidadPeso, int cantidad, int nivelReavastecimiento)
	{
		Query b = pm.newQuery(SQL, "INSERT INTO ALMACENAMIENTO (id_sucursal, codigo_barras_producto, id_categoria, id_tipo_prod, capa_vol"
				+ ", capa_peso, cantidad, tipo_almacen, nivel_reavast) VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?)");
		b.setParameters(idSucursal, codigoBarrasProducto, idCategoria, idTipoProducto, capacidadVol, capacidadPeso, cantidad, nivelReavastecimiento);
		return (long) b.executeUnique();
	}

	public long eliminarAlmacenamientoSucursal(PersistenceManager pm, long idSucursal, String codigoBarrasProducto, int tipoAlmacenamiento)
	{
		Query q = pm.newQuery(SQL, "DELETE  FROM ALMACENAMIENTO WHERE id_sucursal = ? AND codigo_barras_producto = ? AND tipo_alma = ?");
		q.setParameters(idSucursal, codigoBarrasProducto, tipoAlmacenamiento);
		return(long) q.executeUnique();
	}

	public long actualizarCantidadesAlmacenamiento(PersistenceManager pm, long idSucursal, String codigoBarrasProducto, int cantidad, int tipoAlmacenamiento)
	{
		Query q = pm.newQuery(SQL, "UPDATE ALMACENAMIENTO SET cantidad = cantidad + (?) WHERE id_sucursal = ? AND codio_barras_producto = ? AND tipo_alma = ?");
		q.setParameters(cantidad, idSucursal, codigoBarrasProducto, tipoAlmacenamiento);
		return(long) q.executeUnique();
	}
	
	public Almacenamiento buscarAlmacenamientoEnEspecifico(PersistenceManager pm, long idSucursal, String codigoBarras, int tipoAlmacenamiento)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ALMACENAMIENTO WHERE id_sucursal = ? AND codigo_barras_producto = ? AND tipo_almacenamiento = ?");
		q.setResultClass(Almacenamiento.class);
		q.setParameters(idSucursal, codigoBarras, tipoAlmacenamiento);
		return (Almacenamiento) q.executeUnique();
	}
	
	public List<Almacenamiento> buscarAlmacenesPorSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ALMACENAMIENTO WHERE id_sucursal = ?");
		q.setResultClass(Almacenamiento.class);
		q.setParameters(idSucursal);
		return (List<Almacenamiento>) q.executeUnique();
	}
	
	public List<Almacenamiento> buscarAlmacenes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ALMACENAMIENTO");
		q.setResultClass(Almacenamiento.class);
		return (List<Almacenamiento>) q.executeUnique();
	}
	
	public long siguienteId(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT id FROM ALMACENAMIENTO WHERE ROWID = (SELECT MAX(ROWID) FROM ALMACENAMENTO)");
		q.setResultClass(Almacenamiento.class);
		return((Almacenamiento)q.executeUnique()).getId() + 1;
	}
}

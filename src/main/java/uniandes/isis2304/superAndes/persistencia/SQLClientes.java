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
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	//---------------------------------------------------------------------------
	// Atr�butos
	//---------------------------------------------------------------------------

	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes psa;

	//---------------------------------------------------------------------------
	// M�todos
	//---------------------------------------------------------------------------

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
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
	

	public List<Clientes> darRequFunC10(PersistenceManager pm, long id, String restri)
	{
		Query q = pm.newQuery(SQL, "SELECT EMAIL, NOMBRE, DOCUMENTO, DIRECCION FROM INFO_PRODUCTO_SUCURSAL IPS, ( SELECT * FROM CLIENTES C, VENTAS V WHERE C.DOCUMENTO = V.DOCUMENTOCLIENTE) B WHERE IPS.IDVENTA = B.ID" + restri + " AND V.idSucursal = "+id+" GROUP BY B.DOCUMENTO, B.NOMBRE, B.DIRECCION, B.EMAIL ORDER BY DOCUMENTO;");
		q.setResultClass(Clientes.class);
		return (List<Clientes>) q.executeList();
	}
	
	public List<Clientes> darRequFunC11(PersistenceManager pm, long id, String restri)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CLIENTES MINUS(SELECT EMAIL, NOMBRE, DOCUMENTO, DIRECCION FROM INFO_PRODUCTO_SUCURSAL IPS, ( SELECT * FROM CLIENTES C, VENTAS V WHERE C.DOCUMENTO = V.DOCUMENTOCLIENTE) B WHERE IPS.IDVENTA = B.ID" + restri + " AND V.idSucursal = "+id+" GROUP BY B.DOCUMENTO, B.NOMBRE, B.DIRECCION, B.EMAIL ORDER BY DOCUMENTO);");
		q.setResultClass(Clientes.class);
		return (List<Clientes>) q.executeList();
	}
	
	public List<Clientes> darRequFun13(PersistenceManager pm, long tipo)
	{
		String sentencia = "";
		if(tipo == 1)
		{
			sentencia = "select Nombre, DocumentoCliente, Direccion, Email from( select COUNT(mes) as conteo, Nombre, documentoCliente, direccion, email from (SELECT EXTRACT(MONTH FROM fechaVenta) AS mes, to_char(fechaventa,'YYYY') AS Anio, documentoCliente, email, direccion, nombre from ventas V, Clientes c where c.documento = v.documentoCliente group by documentoCLiente, email, direccion, nombre, EXTRACT(MONTH FROM fechaVenta), to_char(fechaventa,'YYYY')) group by email, direccion, documentoCliente, nombre);";
		}
		else if(tipo == 2)
		{
			sentencia = "select c.EMAIL, c.NOMBRE, c.DOCUMENTO, c.DIRECCION from info_producto_sucursal ips, ventas v, clientes c where ips.idventa = v.id and v.documentoCliente = c.documento and ips.preciounitario >= 100000;";
		}
		else
			sentencia ="select c.EMAIL, c.NOMBRE, c.DOCUMENTO, c.DIRECCION from info_producto_sucursal ips, producto p, ventas v, clientes c where (p.idCategoria = 8 OR p.idCategoria = 9) AND ips.codigobarras = p.codigobarras and ips.idventa = v.id AND v.documentocliente = c.documento;";
		Query q = pm.newQuery(SQL, sentencia);
		q.setResultClass(Clientes.class);
		return (List<Clientes>) q.executeList();
	}
}

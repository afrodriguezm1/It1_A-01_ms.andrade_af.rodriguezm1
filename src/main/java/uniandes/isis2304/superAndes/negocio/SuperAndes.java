package uniandes.isis2304.superAndes.negocio;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

/**
 * Clase principal del negocio
 * Satisface todos los requerimientos funcionales del negocio
 * 
 * @author Andres y Mario
 *
 */
public class SuperAndes 
{
	//---------------------------------------------------------------------
	// Constantes
	//---------------------------------------------------------------------
	
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());
	
	private PersistenciaSuperAndes psa;
	
	public SuperAndes()
	{
		psa = PersistenciaSuperAndes.getInstance();
	}
	
	public SuperAndes(JsonObject tableConfig)
	{
		psa = PersistenciaSuperAndes.getInstance(tableConfig);
	}
	
	public void cerrarUnidadPersistencia()
	{
		psa.cerrarUnidadPersistencia();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Clientes
	 *****************************************************************/
	
	public Clientes agregarCliente(String email, String nombre)
	{
		log.info("Adicionando cliente:" + email);
		Clientes cliente = psa.agregarCliente(email, nombre);
		log.info("Adicionando cliente: " + email);
		return cliente;
	}
	
	public Empresas agregarEmpresa(String email, String nit, String direccion)
	{
		log.info("Adicionando empresa: " + email);
		Empresas empresa = psa.agregarEmpresa(email, nit, direccion);
		log.info("Adicionando empresa: " + email);
		return empresa;
	}
	
	public Personas agregarPersona(String email, long documento)
	{
		log.info("Adicionando persona: " + email);
		Personas persona = psa.agregarClientePersona(email, documento);
		log.info("Adicionando persona: " + email);
		return persona;
	}
	
	public Clientes darCliente(String email)
	{
		log.info("Buscando cliente por email: " + email);
		Clientes cl = psa.darClientePorEmail(email);
		return cl;
	}
	
	public List<VOClientes> darVOClientes()
	{
		log.info("Generando los VO de clientes");
		{
			List<VOClientes> voClientes = new LinkedList<VOClientes>();
			for(Clientes tb : psa.darClientes())
			{
				voClientes.add(tb);
			}
			log.info("Generando los VO de Clientes: " + voClientes.size() +" existentes");
			return voClientes;
		}
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Almacenes
	 *****************************************************************/
	
	public Almacenamiento agregarAlmacenamiento(long idSucursal, String codBarras,long idCat, long idTipo, double capaVol, double capaPeso, int tipo, int nivelRevast)
	{
		log.info("Adicionando Almacenamiento a sucursal: " + idSucursal + ", Con codigo de barras:" + codBarras);
		Almacenamiento almacenamiento = psa.agregarAlamacenamientoSucursal(idSucursal, codBarras, idCat, idTipo, capaVol, capaPeso, 0, tipo, nivelRevast);
		log.info("Adicionando Almacenamiento: " + almacenamiento);
		return almacenamiento;
	}
	
	public List<VOAlmacenamiento> darVOAlmacenamiento(long idSucursal)
	{
		log.info("Generando los VO de almacenamiento de la sucursal" + idSucursal);
		List<VOAlmacenamiento> voAlma = new LinkedList<VOAlmacenamiento>();
		for(Almacenamiento al : psa.darAlmacenesPorSucursal(idSucursal))
		{
			voAlma.add(al);
		}
		log.info("Generando los VO de almacenamientos: " + voAlma.size() + " existentes");
		return voAlma;
	}
	
	public long eliminarAlmacenamientoSucursal(long idSucursal, String codigoBarras, int tipo)
	{
		log.info("Eliminando Almacenamiento de sucursal: " + idSucursal + ", del producto con codig de barras: " + codigoBarras);
		long resp = psa.eliminarAlmacenDeSucursal(idSucursal, codigoBarras, tipo);
		log.info("Eliminando Almacenamiento de sucursal: " + resp + " tuplas eliminadas");
		return resp;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar ventas
	 *****************************************************************/
	
	public Ventas nuevaVenta(long idSucursal, String email)
	{
		log.info("Adicionando Nueva Venta: " + email);
		Ventas ventas = psa.agregarVenta(idSucursal, email);
		log.info("Adicionando Nueva Venta: " + email);
		return ventas;
	}
	
	public InfoProdSucursal agregarProductoVenta(long idVenta, String codigoBarras, int cantidad)
	{
		log.info("Adicionando producto a venta: " + codigoBarras);
		InfoProdSucursal info = psa.agregarInfProdSucursal(idVenta, codigoBarras, cantidad);
		log.info("Adicionando producto a venta: " + info);
		return info;
	}
}
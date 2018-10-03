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
}

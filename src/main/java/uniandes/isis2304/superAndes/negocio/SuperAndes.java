package uniandes.isis2304.superAndes.negocio;

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
}

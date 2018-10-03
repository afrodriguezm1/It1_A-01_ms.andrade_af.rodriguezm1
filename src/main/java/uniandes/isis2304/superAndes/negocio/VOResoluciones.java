package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * 
 * Interfaz para los m�todos get de RESOLUCIONES.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public interface VOResoluciones 
{
	//---------------------------------------------------------------------
	// M�todos
	//---------------------------------------------------------------------
	
	public long getNumeroResolucion();
	
	public Date getFechaHabilitacion();
	
	public Date getfechaVencimiento();
	
	public long getInicioConsecutivo();
	
	public long getFinConsecutivo();
	
	public long getNumActual();
	
	@Override
	public String toString();
}

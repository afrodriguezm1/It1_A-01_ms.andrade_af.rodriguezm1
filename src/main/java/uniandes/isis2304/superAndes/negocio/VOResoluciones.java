package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * 
 * Interfaz para los métodos get de RESOLUCIONES.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public interface VOResoluciones 
{
	//---------------------------------------------------------------------
	// Métodos
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

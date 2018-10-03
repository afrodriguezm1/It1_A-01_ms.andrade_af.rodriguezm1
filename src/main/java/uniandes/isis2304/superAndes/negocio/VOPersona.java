package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de PERSONAS.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public interface VOPersona 
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	public String getEmail();
	
	public long getDocumento();
	
	public long getPuntaje();
	
	@Override
	public String toString();
}

package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de EMPRESAS.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public interface VOEmpresas 
{
	public String getEmail();
	
	public String getNit();
	
	public String getDireccion();
	
	@Override
	public String toString();
}

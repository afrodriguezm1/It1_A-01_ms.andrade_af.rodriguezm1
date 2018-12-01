package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�tdos get de CLIENTE.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones de la la interfaz
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public interface VOClientes 
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	/**
	 * @return El email del cliente
	 */
	public String getEmail();
	
	/**
	 * @return El nombre del cliente
	 */
	public String getNombre();
	
	public String getDocumento();
	
	public String getDireccion();
	
	/**
	 * @return Una cadena de caracteres con la informaci�n b�sica del cliente
	 */
	@Override
	public String toString();
}

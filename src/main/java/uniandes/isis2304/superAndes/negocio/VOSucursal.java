package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los m�todos get de Sucursal.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */
public interface VOSucursal
{
	//---------------------------------------------------------------------
	// M�todos
	//---------------------------------------------------------------------
	
	public long getId();
	
	public String getNombre();
	
	public String getDireccion();
	
	public String getCiudad();
	
	@Override
	public String toString();
}

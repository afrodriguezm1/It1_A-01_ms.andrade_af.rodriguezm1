package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los métodos get de Sucursal.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */
public interface VOSucursal
{
	//---------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------
	
	public long getId();
	
	public String getNombre();
	
	public String getDireccion();
	
	public String getCiudad();
	
	@Override
	public String toString();
}

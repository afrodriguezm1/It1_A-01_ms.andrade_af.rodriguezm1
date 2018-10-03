package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los m�todos get de Proveedor.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */
public interface VOProveedor 
{
	public String getNit();
	
	public String getNombre();
	
	public double getCalificacion();
	
	@Override
	public String toString();
}

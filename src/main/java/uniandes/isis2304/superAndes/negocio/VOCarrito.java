package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de Carrito.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * 
 * @author Andres Rodiguez - 201716905
 *
 */
public interface VOCarrito 
{
	//---------------------------------------------------------------------
	// M�todos
	//---------------------------------------------------------------------
	
	
	/**
	 * @return El email del cliente que tiene el carrito
	 */
	public String getEmail();
	
	/**
	 * @return El id de la sucursal del carrito
	 */
	public long getIdSucursal();
	
	/**
	 * @return El precio total que lleva el carrito
	 */
	public long getPrecioTotal();
	
	/**
	 * @return El estado del carrito
	 */
	public String getEstado();
}

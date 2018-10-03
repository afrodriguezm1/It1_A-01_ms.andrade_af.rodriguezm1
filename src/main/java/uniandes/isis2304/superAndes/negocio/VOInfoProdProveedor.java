package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de Info Producto Proveedor
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public interface VOInfoProdProveedor 
{
	//---------------------------------------------------------------------
	// M�todos
	//---------------------------------------------------------------------
	
	/**
	 * @return El id de la Orden de Pedido
	 */
	public long getIdOrden();
	
	/**
	 * @return El c�digo de barras del Producto
	 */
	public String getCodigoBarras();
	
	/**
	 * @return La cantidad de productos en la orden de pedido
	 */
	public int getCantidadProducto();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de la info del producto
	 */
	@Override
	public String toString();
}

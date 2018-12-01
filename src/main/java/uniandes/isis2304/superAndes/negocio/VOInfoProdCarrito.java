package uniandes.isis2304.superAndes.negocio;

public interface VOInfoProdCarrito 
{
	//---------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------
	
	/**
	 * @return El id del carrito al que pertenece
	 */
	public long getIdCarrito();
	
	public String getEmailCliente();
	
	public long getIdSucursal();
	
	/**
	 * @return El codigo de barras del producto de la info
	 */
	public String getCodigoBarras();
	
	/**
	 * @return La cantidad de productos de la info
	 */
	public int getCantidad();
	
	/**
	 * @return El precio total de un producto de la info
	 */
	public long getPrecioTotal();
	
	public long getPrecioUnitario();
}

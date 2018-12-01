package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto InfoProductoProveedor del negocio de
 * SuperAndes
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public class InfoProdProveedor implements VOInfoProdProveedor
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	/**
	 * El identificador ÚNICO de la orden de pedido
	 */
	private long idOrden;
	
	/**
	 * El código de barras del producto
	 */
	private String codigoBarras;
	
	/**
	 * El número de productos que se pidieron
	 */
	private int cantidadProducto;
	
	private long precioTotal;
	
	private long precioUnitario;

	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
	public InfoProdProveedor()
	{
		this.idOrden = 0;
		this.codigoBarras = "";
		this.cantidadProducto = 0;
		this.precioTotal = 0;
		this.precioUnitario = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param idOrden - El id de la orden de pedido
	 * @param codigoBarras - El codigo de barras del producto
	 * @param cantidadProduct - La cantidad de productos
	 */
	public InfoProdProveedor(long idOrden, String codigoBarras, int cantidadProducto, long precioTotal, long precioUnitario)
	{
		this.idOrden = idOrden;
		this.codigoBarras = codigoBarras;
		this.cantidadProducto = cantidadProducto;
		this.precioTotal = precioTotal;
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return El id de la orden de pedido
	 */
	public long getIdOrden() 
	{
		return idOrden;
	}
	
	/**
	 * @param idOrden - El nuevo id de la orden
	 */
	public void setIdOrden(long idOrden)
	{
		this.idOrden = idOrden;
	}

	/**
	 * @return El código de barras del producto
	 */
	public String getCodigoBarras() 
	{
		return codigoBarras;
	}
	
	/**
	 * @param codigoBarras - El nuevo codigo de Barras
	 */
	public void setCodigoBarras(String codigoBarras)
	{
		this.codigoBarras = codigoBarras;
	}

	/**
	 * @return la cantidad de productos
	 */
	public int getCantidadProducto()
	{
		return cantidadProducto;
	}
	
	/**
	 * @param cantidadProduct - La nueva cantidad de productos
	 */
	public void setCantidadProducto(int cantidadProducto)
	{
		this.cantidadProducto = cantidadProducto;
	}
	
	public String toString()
	{
		return "InfoProdProveedor [idOrden=" + idOrden + ", codigoBarras=" + codigoBarras + ", cantidadProducto=" + cantidadProducto + "]";
	}

	@Override
	public long getPrecioTotal() 
	{
		return this.precioTotal;
	}
	
	public void setPrecioTotal(long precioTotal)
	{
		this.precioTotal = precioTotal;
	}

	@Override
	public long getPrecioUnitario() 
	{
		return this.precioUnitario;
	}
	
	public void setPrecioUnitario(long precioUnitario)
	{
		this.precioUnitario = precioUnitario;
	}
}

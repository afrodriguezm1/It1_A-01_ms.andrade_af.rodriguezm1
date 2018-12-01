package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto InfoProductoProveedor del negocio de
 * SuperAndes
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public class InfoProdSucursal implements VOInfoProdSucursal
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	/**
	 * El identificador ÚNICO de la orden de pedido
	 */
	private long idVenta;
	
	private long idSucursal;
	
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
	public InfoProdSucursal()
	{
		this.idVenta = 0;
		this.idVenta = 0;
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
	public InfoProdSucursal(long idVenta, long idSucursal, String codigoBarras, int cantidadProducto, long precioTotal, long precioUnitario)
	{
		this.idVenta = idVenta;
		this.idSucursal = idSucursal;
		this.codigoBarras = codigoBarras;
		this.cantidadProducto = cantidadProducto;
		this.precioTotal = precioTotal;
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return El id de la orden de pedido
	 */
	public long getIdVenta() 
	{
		return idVenta;
	}
	
	/**
	 * @param idOrden - El nuevo id de la orden
	 */
	public void setIdVenta(long idVenta)
	{
		this.idVenta = idVenta;
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
		return "InfoProdProveedor [idOrden=" + idVenta + ", codigoBarras=" + codigoBarras + ", cantidadProducto=" + cantidadProducto + "]";
	}

	@Override
	public long getIdSucursal() 
	{
		return this.idSucursal;
	}
	
	public void setIdSucursal(long idSucursal)
	{
		this.idSucursal = idSucursal;
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

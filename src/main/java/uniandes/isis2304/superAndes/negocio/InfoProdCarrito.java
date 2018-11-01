package uniandes.isis2304.superAndes.negocio;

public class InfoProdCarrito implements VOInfoProdCarrito
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	private long idCarrito;
	
	private String codigoBarras;
	
	private int cantidad;
	
	private long precioTotal;
	
	//---------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------
	
	public  InfoProdCarrito() 
	{
		this.idCarrito = 0;
		this.codigoBarras = "";
		this.cantidad = 0;
		this.precioTotal = 0;
	}
	
	public InfoProdCarrito(long id, String codigoBarras, int cantidad, long precioTotal)
	{
		this.idCarrito = id;
		this.codigoBarras = codigoBarras;
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
	}

	@Override
	public long getIdCarrito() 
	{
		return idCarrito;
	}
	
	public void setIdCarrito(long idCarrito)
	{
		this.idCarrito = idCarrito;
	}

	@Override
	public String getCodigoBarras() 
	{
		return this.codigoBarras;
	}
	
	public void setCodigoBarras(String codigoBarras)
	{
		this.codigoBarras = codigoBarras;
	}

	@Override
	public int getCantidad() 
	{
		return this.cantidad;
	}

	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
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

}

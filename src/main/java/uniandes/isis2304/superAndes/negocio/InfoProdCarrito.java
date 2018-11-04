package uniandes.isis2304.superAndes.negocio;

public class InfoProdCarrito implements VOInfoProdCarrito
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	private long idCarrito;
	
	private String email;
	
	private long idSucursal;
	
	private String codigoBarras;
	
	private int cantidad;
	
	private long precioTotal;
	
	//---------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------
	
	public  InfoProdCarrito() 
	{
		this.idCarrito = 0;
		this.email = "";
		this.idSucursal = 0;
		this.codigoBarras = "";
		this.cantidad = 0;
		this.precioTotal = 0;
	}
	
	public InfoProdCarrito(long id, String email, long idSucursal, String codigoBarras, int cantidad, long precioTotal)
	{
		this.idCarrito = id;
		this.email = email;
		this.idSucursal = idSucursal;
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
	public String getEmail() 
	{
		return this.email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
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

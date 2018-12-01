package uniandes.isis2304.superAndes.negocio;

public class InfoProdCarrito implements VOInfoProdCarrito
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	private long idCarrito;
	
	private String emailCliente;
	
	private long idSucursal;
	
	private String codigoBarras;
	
	private int cantidad;
	
	private long precioUnitario;
	
	private long precioTotal;
	
	//---------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------
	
	public  InfoProdCarrito() 
	{
		this.idCarrito = 0;
		this.emailCliente = "";
		this.idSucursal = 0;
		this.codigoBarras = "";
		this.cantidad = 0;
		this.precioTotal = 0;
		this.precioUnitario = 0;
	}
	
	public InfoProdCarrito(long id, String email, long idSucursal, String codigoBarras, int cantidad, long precioTotal, long precioUnitario)
	{
		this.idCarrito = id;
		this.emailCliente = email;
		this.idSucursal = idSucursal;
		this.codigoBarras = codigoBarras;
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
		this.precioUnitario = precioUnitario;
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
	public String getEmailCliente() 
	{
		return this.emailCliente;
	}
	
	public void setEmailCliente(String email)
	{
		this.emailCliente = email;
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

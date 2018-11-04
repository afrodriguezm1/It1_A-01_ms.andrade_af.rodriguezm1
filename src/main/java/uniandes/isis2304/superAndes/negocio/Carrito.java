package uniandes.isis2304.superAndes.negocio;

public class Carrito implements VOCarrito
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------

	private long id;
	
	private String email;
	
	private long idSucursal;
	
	private long precio;
	
	private String estado;
	
	//---------------------------------------------------------------------
		// Métodos
		//---------------------------------------------------------------------
	
	public Carrito()
	{
		this.email = "";
		this.idSucursal = 0;
		this.precio = 0;
		this.estado = "";
	}
	
	public Carrito( String email, long idSucursal, long precio, String estado)
	{
		this.email = email;
		this.idSucursal = idSucursal;
		this.precio = precio;
		this.estado = estado;
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
	
	public void setIdsucursal(long idSucursal)
	{
		this.idSucursal = idSucursal;
	}

	@Override
	public long getPrecioTotal() 
	{
		return this.precio;
	}
	
	public void setPrecio(long precio)
	{
		this.precio = precio;
	}

	@Override
	public String getEstado() 
	{
		return this.estado;
	}
	
	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	@Override
	public long getId() 
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}

}
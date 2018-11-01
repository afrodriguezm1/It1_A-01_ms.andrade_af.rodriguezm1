package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Clase para modelar el concepto VENTA del negocio de SuperAndes
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public class Ventas implements VOVentas
{

	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	private long id;
	
	private long idSucursal;
	
	private String emailCliente;
	
	private long consecutivoFE;
	
	private String CUFE;
	
	private Date fechaVenta;
	
	private long precio;
	
	public Ventas()
	{
		this.id = 0;
		this.idSucursal = 0;
		this.emailCliente = "";
		this.consecutivoFE = 0;
		this.CUFE = "";
		this.fechaVenta = null;
	}
	
	public Ventas(long id, long idSucursal, String emailCliente, long consecutivoFE, String CUFE, Date fechaVenta)
	{
		this.id = id;
		this.idSucursal = idSucursal;
		this.emailCliente = emailCliente;
		this.consecutivoFE = consecutivoFE;
		this.CUFE = CUFE;
		this.fechaVenta = fechaVenta;
	}

	public long getId() 
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}

	public long getIdSucursal() 
	{
		return idSucursal;
	}
	
	public void setIdSucursal(long idSucursal)
	{
		this.idSucursal = idSucursal;
	}

	public String getEmailCliente() 
	{
		return emailCliente;
	}
	
	public void setEmailCliente(String emailCliente)
	{
		this.emailCliente = emailCliente;	
	}

	public long getConsecutivoFE() 
	{
		return consecutivoFE;
	}
	
	public void setConsecutivoFE(long consecutivoFE)
	{
		this.consecutivoFE = consecutivoFE;
	}

	public String getCUFE() 
	{
		return CUFE;
	}
	
	public void setCUFE(String CUFE)
	{
		this.CUFE = CUFE;
	}
	
	public Date getFechaVenta() 
	{
		return fechaVenta;
	}
	
	public void setFechaVenta(Date fechaVenta)
	{
		this.fechaVenta = fechaVenta;
	}
	
	public long getPrecio()
	{
		return precio;
	}
	
	public void setPrecio(long precio)
	{
		this.precio = precio;
	}
	
	@Override
	public String toString()
	{
		return "Venta [id=" + id + ", idSucursal=" + idSucursal + ", emailCliente=" + emailCliente + ", consecutivoFE=" + consecutivoFE + ", CUFE=" + CUFE
				+ ", fechaVenta=" + fechaVenta +"]";
	}
}

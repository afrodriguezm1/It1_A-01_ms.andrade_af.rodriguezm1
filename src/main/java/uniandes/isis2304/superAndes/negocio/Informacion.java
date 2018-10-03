package uniandes.isis2304.superAndes.negocio;

public class Informacion implements VOInformacion
{
	private String nit;
	
	private String nombre;
	
	private boolean facturaE;
	
	public Informacion()
	{
		nit = "";
		nombre ="";
		facturaE = false;
	}
	
	public Informacion(String nit, String nombre, boolean facturaE)
	{
		this.nit = nit;
		this.nombre = nombre;
		this.facturaE = facturaE;
	}

	public String getNit() 
	{
		return nit;
	}
	
	public void setNit(String nit)
	{
		this.nit = nit;
	}

	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public boolean getFacturaE() 
	{
		return facturaE;
	}
	
	public void setFacturaE(boolean facturaE)
	{
		this.facturaE = facturaE;
	}
	
	public String toString()
	{
		return "Informacion [nit=" + nit +", nombre=" + nombre + ", facturaE=" + facturaE + "]"; 
	}
}

package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar una sucursal
 * @author Mario Andrade
 *
 */
public class Sucursal implements VOSucursal
{

	public long id;
	
	/**
	 * Nombre de la sucursal
	 */
	private String nombre;
	
	/**
	 * Dirección de la sucursal
	 */
	private String direccion;
	
	/**
	 * Ciudad de la sucursal
	 */
	private String ciudad;
	
	/**
	 * Constructor por defecto
	 */
	public Sucursal()
	{
		this.id = 0;
		this.nombre = "";
		this.direccion ="";
		this.ciudad = "";
	}
	
	
	/**
	 * Constructor de una sucursal con valores
	 * @param pId
	 * @param pNombre
	 * @param pDireccion
	 * @param pCiudad
	 */
	public Sucursal(long id, String pNombre, String pDireccion, String pCiudad)
	{
		this.id = id;
		this.nombre = pNombre;
		this.direccion = pDireccion;
		this.ciudad = pCiudad;
		
	}
	
	public Sucursal(String pNombre, String pDireccion, String pCiudad)
	{
		this.nombre = pNombre;
		this.direccion = pDireccion;
		this.ciudad = pCiudad;
		
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


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}


	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}


	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sucursal [ nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad + "]";
	}
	
	
}

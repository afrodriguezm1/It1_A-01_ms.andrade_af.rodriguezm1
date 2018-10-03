package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar una sucursal
 * @author Mario Andrade
 *
 */
public class Sucursal implements VOSucursal
{
	/**
	 * Identificador unico de la sucursal
	 */
	private long id;
	
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
		id = 0;
		nombre = "";
		direccion ="";
		ciudad = "";
	}
	
	
	/**
	 * Constructor de una sucursal con valores
	 * @param pId
	 * @param pNombre
	 * @param pDireccion
	 * @param pCiudad
	 */
	public Sucursal(long pId, String pNombre, String pDireccion, String pCiudad)
	{
		this.id = pId;
		this.nombre = pNombre;
		this.direccion = pDireccion;
		this.ciudad = pCiudad;
		
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad + "]";
	}
	
	
}

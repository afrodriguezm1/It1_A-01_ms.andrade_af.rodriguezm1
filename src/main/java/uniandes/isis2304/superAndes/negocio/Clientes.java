package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de CLIENTE del negocio de SuperAndes
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public class Clientes implements VOClientes
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	/**
	 * El email del cliente
	 */
	private String email;
	
	/**
	 * El nombre del cliente
	 */
	private String nombre;
	
	private String documento;
	
	private String direccion;
	
	//---------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
	public Clientes()
	{
		this.email = "";
		this.nombre = "";
		this.documento = "";
		this.direccion = "";
	}
	
	/**
	 * Constructor con valores
	 * @param email - Email del cliente
	 * @param nombre - El nombre del cliente
	 */
	public Clientes(String email, String nombre, String documento, String direccion)
	{
		this.email = email;
		this.nombre = nombre;
		this.documento = documento;
		this.direccion = direccion;
	}
	
	/**
	 * @return Email del cliente
	 */
	public String getEmail() 
	{
		return email;
	}
	
	/**
	 * @param email - El nuevo email del cliente
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return El nombre del cliente
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre - Nuevo nombre del cliente
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return Una cadena de caracteres con la información básica del cliente
	 */
	@Override
	public String toString()
	{
		return "Clientes [email=" + email + ",\n nombre=" + nombre + ",\n documento="+ this.documento +",\n direccion="+ this.direccion+"]";
	}

	@Override
	public String getDocumento() 
	{
		return this.documento;
	}

	@Override
	public String getDireccion() 
	{
		return this.direccion;
	}
}

package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar una categoria
 * @author Mario Andrade
 *
 */
public class Categoria implements VOCategoria
{
	
	private long id;
		
	/**
	 * Nombre de la categoria.
	 */
	private String nombre;
	
	/**
	 * COnstructor por defecto
	 */
	public Categoria()
	{
		this.id = 0;
		this.nombre ="";
	}
	
	/**
	 * Constructor de una categoria con valores.
	 * @param id
	 * @param nombre
	 */
	public Categoria(long id, String nombre) 
	{	this.id = id;
		this.nombre = nombre;
	}
	
	public Categoria( String nombre) 
	{
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "Categoria [nombre=" + nombre + "]";
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

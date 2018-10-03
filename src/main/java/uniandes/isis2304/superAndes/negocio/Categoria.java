package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar una categoria
 * @author Mario Andrade
 *
 */
public class Categoria implements VOCategoria
{
	/**
	 * Id unico de la categoria
	 */
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
	public Categoria(long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}

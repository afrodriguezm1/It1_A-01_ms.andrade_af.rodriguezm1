package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar una categoria
 * @author Mario Andrade
 *
 */
public class Categoria implements VOCategoria
{

	
	/**
	 * Nombre de la categoria.
	 */
	private String nombre;
	
	/**
	 * COnstructor por defecto
	 */
	public Categoria()
	{
		this.nombre ="";
	}
	
	/**
	 * Constructor de una categoria con valores.
	 * @param id
	 * @param nombre
	 */
	public Categoria( String nombre) {
		super();

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categoria [nombre=" + nombre + "]";
	}
	
	
}

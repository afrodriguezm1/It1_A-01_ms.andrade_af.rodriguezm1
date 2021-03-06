package uniandes.isis2304.superAndes.negocio;
/**
 * Clase que modela el tipo producto
 * @author Mario Andrade
 *
 */
public class TipoProducto implements VOTipoProducto
{

	private long id;
	
	/**
	 * Id de la categoria del producto.
	 */
	private long idCategoria;
	
	/**
	 * Nombre del tipo de producto.
	 */
	private String nombre;
	
	/**
	 * Constructor por defecto.
	 */
	public TipoProducto()
	{
		this.id = 0;
		this.idCategoria = 0;
		this.nombre = "";
	}

	/**
	 * Constructor del tipo de producto con valores
	 * @param id
	 * @param idCategoria
	 * @param nombre
	 */
	public TipoProducto(long id, long idCategoria, String nombre) {
		this.id = id;
		this.idCategoria = idCategoria;
		this.nombre = nombre;
	}
	
	public TipoProducto( long idCategoria, String nombre) 
	{
		this.idCategoria = idCategoria;
		this.nombre = nombre;
	}

	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}


	/**
	 * @return the idCategoria
	 */
	public long getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
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
		return "TipoProducto [idCategoria=" + idCategoria + ", nombre=" + nombre + "]";
	}
	
	
	
	

}

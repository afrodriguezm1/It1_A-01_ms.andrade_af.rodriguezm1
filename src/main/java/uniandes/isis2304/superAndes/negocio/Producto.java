package uniandes.isis2304.superAndes.negocio;
/**
 * Clase que modela un producto.
 * @author Mario Andrade
 *
 */
public class Producto implements VOProducto
{
	/**
	 * Codigo de barras del producto
	 */
	private long codigoBarras;
	
	/**
	 * Id categoria del producto
	 */
	private long idCategoria;
	
	/**
	 * Id tipo de producto
	 */
	private long idTipoProducto;
	
	/**
	 * Nombre del producto
	 */
	private String nombre;
	
	/**
	 * Marca del producto
	 */
	private String marca;
	
	/**
	 * Presentacion del producto
	 */
	private String presentacion;
	
	/**
	 * Cantidad de la presentacion
	 */
	private int cantidadPresent;
	
	/**
	 * Unidad de medida del producto
	 */
	private String uniMedida;
	
	/**
	 * Volumen del producto
	 */
	private int volumen;
	
	/**
	 * Peso del producto
	 */
	private int peso;
	
	/**
	 * Constructor por defecto
	 */
	public Producto()
	{
		this.codigoBarras = 0;
		this.idCategoria = 0;
		this.idTipoProducto = 0;
		this.nombre = "";
		this.marca = "";
		this.presentacion = "";
		this.cantidadPresent = 0;
		this.uniMedida = "";
		this.volumen = 0;
		this.peso = 0;
	}

	/**
	 * Constructor de un producto con valores.
	 * @param codigoBarras
	 * @param idCategoria
	 * @param idTipoProducto
	 * @param nombre
	 * @param marca
	 * @param presentacion
	 * @param cantidadPresent
	 * @param uniMedida
	 * @param volumen
	 * @param peso
	 */
	public Producto(long codigoBarras, long idCategoria, long idTipoProducto, String nombre, String marca,
			String presentacion, int cantidadPresent, String uniMedida, int volumen, int peso) {
		this.codigoBarras = codigoBarras;
		this.idCategoria = idCategoria;
		this.idTipoProducto = idTipoProducto;
		this.nombre = nombre;
		this.marca = marca;
		this.presentacion = presentacion;
		this.cantidadPresent = cantidadPresent;
		this.uniMedida = uniMedida;
		this.volumen = volumen;
		this.peso = peso;
	}

	/**
	 * @return the codigoBarras
	 */
	public long getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * @param codigoBarras the codigoBarras to set
	 */
	public void setCodigoBarras(long codigoBarras) {
		this.codigoBarras = codigoBarras;
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
	 * @return the idTipoProducto
	 */
	public long getIdTipoProducto() {
		return idTipoProducto;
	}

	/**
	 * @param idTipoProducto the idTipoProducto to set
	 */
	public void setIdTipoProducto(long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
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
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the presentacion
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * @param presentacion the presentacion to set
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * @return the cantidadPresent
	 */
	public int getCantidadPresent() {
		return cantidadPresent;
	}

	/**
	 * @param cantidadPresent the cantidadPresent to set
	 */
	public void setCantidadPresent(int cantidadPresent) {
		this.cantidadPresent = cantidadPresent;
	}

	/**
	 * @return the uniMedida
	 */
	public String getUniMedida() {
		return uniMedida;
	}

	/**
	 * @param uniMedida the uniMedida to set
	 */
	public void setUniMedida(String uniMedida) {
		this.uniMedida = uniMedida;
	}

	/**
	 * @return the volumen
	 */
	public int getVolumen() {
		return volumen;
	}

	/**
	 * @param volumen the volumen to set
	 */
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}

	/**
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Producto [codigoBarras=" + codigoBarras + ", idCategoria=" + idCategoria + ", idTipoProducto="
				+ idTipoProducto + ", nombre=" + nombre + ", marca=" + marca + ", presentacion=" + presentacion
				+ ", cantidadPresent=" + cantidadPresent + ", uniMedida=" + uniMedida + ", volumen=" + volumen
				+ ", peso=" + peso + "]";
	}
	
	
	
	
}

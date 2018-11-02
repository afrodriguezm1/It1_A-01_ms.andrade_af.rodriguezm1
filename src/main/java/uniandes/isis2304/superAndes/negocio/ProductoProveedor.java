package uniandes.isis2304.superAndes.negocio;
/**
 * Clase que representa la relacion entre un producto y proveedor
 * @author Mario Andrade
 *
 */
public class ProductoProveedor implements VOProductoProveedor
{
	/**
	 * Codigo de barras del producto
	 */
	private long codigoBarras;
	
	/**
	 * NIT del proveedor
	 */
	private String nitProveedor;
	
	/**
	 * Booleano numerico para saber si es exclusivo
	 */
	private int esExclusivo;
	
	/**
	 * Precio unitario del producto
	 */
	private int precioUnitario;
	
	/**
	 * Precio por unidad de medida del producto
	 */
	private int precioUniMedida;
	
	/**
	 * Constructor por defecto
	 */
	public ProductoProveedor()
	{
		this.codigoBarras = 0;
		this.nitProveedor = "";
		this.esExclusivo = 0;
		this.precioUnitario =0;
		this.precioUniMedida = 0;
	}

	/**
	 * Constructor de un producto proveedor con valores
	 * @param codigoBarras
	 * @param nit
	 * @param esExclusivo
	 * @param precioUnitario
	 * @param precioUniMedida
	 */
	public ProductoProveedor(long codigoBarras, String nit, int esExclusivo, int precioUnitario, int precioUniMedida) {
		super();
		this.codigoBarras = codigoBarras;
		this.nitProveedor = nit;
		this.esExclusivo = esExclusivo;
		this.precioUnitario = precioUnitario;
		this.precioUniMedida = precioUniMedida;
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
	 * @return the nit
	 */
	public String getNitProveedor() {
		return nitProveedor;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNitProveedor(String nit) {
		this.nitProveedor = nit;
	}

	/**
	 * @return the esExclusivo
	 */
	public int getEsExclusivo() {
		return esExclusivo;
	}

	/**
	 * @param esExclusivo the esExclusivo to set
	 */
	public void setEsExclusivo(int esExclusivo) {
		this.esExclusivo = esExclusivo;
	}

	/**
	 * @return the precioUnitario
	 */
	public int getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return the precioUniMedida
	 */
	public int getPrecioUniMedida() {
		return precioUniMedida;
	}

	/**
	 * @param precioUniMedida the precioUniMedida to set
	 */
	public void setPrecioUniMedida(int precioUniMedida) {
		this.precioUniMedida = precioUniMedida;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductoProveedor [codigoBarras=" + codigoBarras + ", nit=" + nitProveedor + ", esExclusivo=" + esExclusivo
				+ ", precioUnitario=" + precioUnitario + ", precioUniMedida=" + precioUniMedida + "]";
	}
	
	
	
}

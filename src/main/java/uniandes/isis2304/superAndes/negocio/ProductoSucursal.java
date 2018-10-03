package uniandes.isis2304.superAndes.negocio;
/**
 * Clase que modela la relación de un producto y una sucursal.
 * @author Mario Andrade
 *
 */
public class ProductoSucursal implements VOProductoSucursal
{
	/**
	 * Codigo de barras del producto
	 */
	private long codigoBarras;
	
	/**
	 * Id de la sucursal
	 */
	private long idSucursal;
	
	/**
	 * Precio unitario del producto
	 */
	private int precioUnitario;
	
	/**
	 * precio unidad de medida del producto
	 */
	private int precioUniMedida;
	
	/**
	 * Numero de recompra del producto
	 */
	private int numeroRecompra;
	
	/**
	 * Numero minimo de productos para hacer reorden
	 */
	private int nivelReorden;
	
	/**
	 * COnstructor por defecto
	 */
	public ProductoSucursal()
	{
		this.codigoBarras =0;
		this.idSucursal = 0;
		this.precioUnitario =0;
		this.precioUniMedida =0;
		this.numeroRecompra = 0;
		this.nivelReorden =0;
	}

	/**
	 * Constructor de un producto en una sucursal con valores
	 * @param codigoBarras
	 * @param idSucursal
	 * @param precioUnitario
	 * @param precioUniMedida
	 * @param numeroRecompra
	 * @param nivelReorden
	 */
	public ProductoSucursal(long codigoBarras, long idSucursal, int precioUnitario, int precioUniMedida,
			int numeroRecompra, int nivelReorden) {
		super();
		this.codigoBarras = codigoBarras;
		this.idSucursal = idSucursal;
		this.precioUnitario = precioUnitario;
		this.precioUniMedida = precioUniMedida;
		this.numeroRecompra = numeroRecompra;
		this.nivelReorden = nivelReorden;
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
	 * @return the idSucursal
	 */
	public long getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
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

	/**
	 * @return the numeroRecompra
	 */
	public int getNumeroRecompra() {
		return numeroRecompra;
	}

	/**
	 * @param numeroRecompra the numeroRecompra to set
	 */
	public void setNumeroRecompra(int numeroRecompra) {
		this.numeroRecompra = numeroRecompra;
	}

	/**
	 * @return the nivelReorden
	 */
	public int getNivelReorden() {
		return nivelReorden;
	}

	/**
	 * @param nivelReorden the nivelReorden to set
	 */
	public void setNivelReorden(int nivelReorden) {
		this.nivelReorden = nivelReorden;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductoSucursal [codigoBarras=" + codigoBarras + ", idSucursal=" + idSucursal + ", precioUnitario="
				+ precioUnitario + ", precioUniMedida=" + precioUniMedida + ", numeroRecompra=" + numeroRecompra
				+ ", nivelReorden=" + nivelReorden + "]";
	}
	
	
}

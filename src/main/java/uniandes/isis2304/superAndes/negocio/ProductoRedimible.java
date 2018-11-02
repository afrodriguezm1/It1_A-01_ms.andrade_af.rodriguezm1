package uniandes.isis2304.superAndes.negocio;
/**
 * Clase que modela un producto redimible por puntos
 * @author Mario Andrade
 */
public class ProductoRedimible implements VOProductoRedimible
{
	/**
	 * Codigo de barras del producto
	 */
	private long codigoBarras;
	
	/**
	 * Id de la sucursal
	 */
	private long idSucursal;
	
	/*
	 * Puntos requeridos para redimir el producto
	 */
	private int puntos;
	
	/**
	 * Constructor por defecto
	 */
	public ProductoRedimible()
	{
		this.codigoBarras = 0;
		this.idSucursal =0;
		this.puntos = 0;
	}

	/**
	 * Constructor de un producto redimible con valores
	 * @param codigoBarras
	 * @param idSucursal
	 * @param puntos
	 */
	public ProductoRedimible(long codigoBarras, long idSucursal, int puntos) {
		this.codigoBarras = codigoBarras;
		this.idSucursal = idSucursal;
		this.puntos = puntos;
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
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductoRedimible [codigoBarras=" + codigoBarras + ", idSucursal=" + idSucursal + ", puntos=" + puntos
				+ "]";
	}
	
	
	
	
}

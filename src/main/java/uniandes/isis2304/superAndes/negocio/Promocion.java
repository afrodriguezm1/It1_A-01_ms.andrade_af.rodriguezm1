package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Clase que modela una promoción de un producto
 * @author Mario Andrade
 *
 */
public class Promocion implements VOPromocion
{
	/**
	 * Id de la promocion
	 */
	private long id;
	
	/**
	 * Id de la sucursal
	 */
	private long idSucursal;
	
	/**
	 * Codigo de barras del producto
	 */
	private long codigoBarras;
	
	/**
	 * Nombre de la promoción
	 */
	private String nombre;
	
	/**
	 * Fecha de inicio de la promocion
	 */
	private Timestamp fechaInicio;
	
	/**
	 * Fecha de fin de la promocion
	 */
	private Timestamp fechaFin;
	
	/**
	 * Numero que indica cual es el tipo de promocion 
	 */
	private int tipoPromo;
	
	/**
	 * Valor original del producto en la promocion
	 */
	private int valorOriginal;
	
	/**
	 * Valor del producto(s) en la promocion
	 */
	private int valorPromo;
	
	/**
	 * Costructor por defecto
	 */
	public Promocion()
	{
		this.id =0;
		this.idSucursal = 0;
		this.codigoBarras = 0;
		this.nombre = "";
		this.fechaInicio = null;
		this.fechaFin = null;
		this.tipoPromo = 0;
		this.valorOriginal =0;
		this.valorPromo = 0;
	}

	/**
	 * Constructor de una promocion con valores
	 * @param id
	 * @param idSucursal
	 * @param codigoBarras
	 * @param nombre
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoPromo
	 * @param valorOriginal
	 * @param valorPromo
	 */
	public Promocion(long id, long idSucursal, long codigoBarras, String nombre, Timestamp fechaInicio,
			Timestamp fechaFin, int tipoPromo, int valorOriginal, int valorPromo) {
		super();
		this.id = id;
		this.idSucursal = idSucursal;
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoPromo = tipoPromo;
		this.valorOriginal = valorOriginal;
		this.valorPromo = valorPromo;
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
	 * @return the fechaInicio
	 */
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Timestamp getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the tipoPromo
	 */
	public int getTipoPromo() {
		return tipoPromo;
	}

	/**
	 * @param tipoPromo the tipoPromo to set
	 */
	public void setTipoPromo(int tipoPromo) {
		this.tipoPromo = tipoPromo;
	}

	/**
	 * @return the valorOriginal
	 */
	public int getValorOriginal() {
		return valorOriginal;
	}

	/**
	 * @param valorOriginal the valorOriginal to set
	 */
	public void setValorOriginal(int valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	/**
	 * @return the valorPromo
	 */
	public int getValorPromo() {
		return valorPromo;
	}

	/**
	 * @param valorPromo the valorPromo to set
	 */
	public void setValorPromo(int valorPromo) {
		this.valorPromo = valorPromo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Promocion [id=" + id + ", idSucursal=" + idSucursal + ", codigoBarras=" + codigoBarras + ", nombre="
				+ nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tipoPromo=" + tipoPromo
				+ ", valorOriginal=" + valorOriginal + ", valorPromo=" + valorPromo + "]";
	}
	
	
	
	
		
}

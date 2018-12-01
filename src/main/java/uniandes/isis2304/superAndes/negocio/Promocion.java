package uniandes.isis2304.superAndes.negocio;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Clase que modela una promoción de un producto
 * @author Mario Andrade
 *
 */
public class Promocion implements VOPromocion
{
	private long id;

	/**
	 * Id de la sucursal
	 */
	private long idSucursal;
	
	/**
	 * Codigo de barras del producto
	 */
	private String codigoBarras;
	
	/**
	 * Nombre de la promoción
	 */
	private String nombre;
	
	/**
	 * Fecha de inicio de la promocion
	 */
	private Date fechaInicio;
	
	/**
	 * Fecha de fin de la promocion
	 */
	private Date fechaFin;
	
	/**
	 * Numero que indica cual es el tipo de promocion 
	 */
	private int tipoPromocion;
	
	/**
	 * Valor original del producto en la promocion
	 */
	private int valor1;
	
	/**
	 * Valor del producto(s) en la promocion
	 */
	private int valor2;
	
	/**
	 * Costructor por defecto
	 */
	public Promocion()
	{

		this.idSucursal = 0;
		this.codigoBarras = "";
		this.nombre = "";
		this.fechaInicio = null;
		this.fechaFin = null;
		this.tipoPromocion = 0;
		this.valor1 =0;
		this.valor2 = 0;
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
	public Promocion( long idSucursal, String codigoBarras, String nombre, Date fechaInicio,
			Date fechaFin, int tipoPromo, int valorOriginal, int valorPromo) {

		this.idSucursal = idSucursal;
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoPromocion = tipoPromo;
		this.valor1 = valorOriginal;
		this.valor2 = valorPromo;
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
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * @param codigoBarras the codigoBarras to set
	 */
	public void setCodigoBarras(String codigoBarras) {
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
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the tipoPromo
	 */
	public int getTipoPromocion() {
		return tipoPromocion;
	}

	/**
	 * @param tipoPromo the tipoPromo to set
	 */
	public void setTipoPromocion(int tipoPromo) {
		this.tipoPromocion = tipoPromo;
	}

	/**
	 * @return the valorOriginal
	 */
	public int getValor1() {
		return valor1;
	}

	/**
	 * @param valorOriginal the valorOriginal to set
	 */
	public void setValor1(int valorOriginal) {
		this.valor1 = valorOriginal;
	}

	/**
	 * @return the valorPromo
	 */
	public int getValor2() {
		return valor2;
	}

	/**
	 * @param valorPromo the valorPromo to set
	 */
	public void setValor2(int valorPromo) {
		this.valor2= valorPromo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Promocion [idSucursal=" + idSucursal + ", codigoBarras=" + codigoBarras + ", nombre="
				+ nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tipoPromo=" + tipoPromocion
				+ ", valorOriginal=" + valor1 + ", valorPromo=" + valor2 + "]";
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

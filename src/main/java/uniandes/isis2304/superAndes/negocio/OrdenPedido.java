package uniandes.isis2304.superAndes.negocio;
/**
 * Clase que modela un pedido hecho por una sucursal
 * @author Mario Andrade
 *
 */

import java.sql.Timestamp;

public class OrdenPedido implements VOOrdenPedido
{
	/**
	 * Id del pedido
	 */
	private long id;
	
	/**
	 * Id de la sucursal
	 */
	private long idSucursal;
	
	/**
	 * Nit del proveedor
	 */
	private String nitProveedor;
	
	/**
	 * Fecha de expedicion de la orden
	 */
	private Timestamp fechaExp;
	
	/**
	 * Fecha estimada de entrega de la orden
	 */
	private Timestamp fechaEst;
	
	/**
	 * Fecha de entrega de la orden
	 */
	private Timestamp fechaEntrega;
	
	/*
	 * Estado de entrega del producto
	 */
	private String estado;
	
	/**
	 * Constructor por defecto
	 */
	public OrdenPedido()
	{
		this.id =0;
		this.idSucursal =0;
		this.nitProveedor = "";
		this.fechaEntrega = null;
		this.fechaEst = null;
		this.fechaExp = null;
		this.estado = "";
	}

	/**
	 * Constructor de un pedido con valores
	 * @param id
	 * @param idSucursal
	 * @param nitProveedor
	 * @param fechaExp
	 * @param fechaEst
	 * @param fechaEntrega
	 * @param estado
	 */
	public OrdenPedido(long id, long idSucursal, String nitProveedor, Timestamp fechaExp, Timestamp fechaEst,
			Timestamp fechaEntrega, String estado) {
		super();
		this.id = id;
		this.idSucursal = idSucursal;
		this.nitProveedor = nitProveedor;
		this.fechaExp = fechaExp;
		this.fechaEst = fechaEst;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
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
	 * @return the nitProveedor
	 */
	public String getNitProveedor() {
		return nitProveedor;
	}

	/**
	 * @param nitProveedor the nitProveedor to set
	 */
	public void setNitProveedor(String nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	/**
	 * @return the fechaExp
	 */
	public Timestamp getFechaExp() {
		return fechaExp;
	}

	/**
	 * @param fechaExp the fechaExp to set
	 */
	public void setFechaExp(Timestamp fechaExp) {
		this.fechaExp = fechaExp;
	}

	/**
	 * @return the fechaEst
	 */
	public Timestamp getFechaEst() {
		return fechaEst;
	}

	/**
	 * @param fechaEst the fechaEst to set
	 */
	public void setFechaEst(Timestamp fechaEst) {
		this.fechaEst = fechaEst;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Timestamp getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrdenPedido [id=" + id + ", idSucursal=" + idSucursal + ", nitProveedor=" + nitProveedor + ", fechaExp="
				+ fechaExp + ", fechaEst=" + fechaEst + ", fechaEntrega=" + fechaEntrega + ", estado=" + estado + "]";
	}
	
	
}

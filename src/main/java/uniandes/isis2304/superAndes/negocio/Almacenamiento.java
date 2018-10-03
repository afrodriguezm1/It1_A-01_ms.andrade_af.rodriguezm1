package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto ALMACENAMIENTO del negocio de SuperAndes
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public class Almacenamiento implements VOAlmacenamiento
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	/**
	 * El identificador ÚNICO del almacen
	 */
	private long id;
	
	/**
	 * El identificador de la sucursal que es dueña del almacen
	 */
	private long idSucursal;
	
	/**
	 * El identidicador de la categoria del almacen
	 */
	private long idCategoria;
	
	/**
	 * El identificador del tipo de producto del almacen
	 */
	private long idTipoProducto;
	
	/**
	 * Capacidad en volumen del almacenamiento
	 */
	private double capacidadVol;
	
	/**
	 * Capacidad en peso del almacenamiento
	 */
	private double capacidadPeso;
	
	/**
	 * Cantidad de productos actualmente en el almacenamiento
	 */
	private int cantidad;
	
	/**
	 * Tipo de almacenamiento del almacen
	 */
	private int tipoAlmacenamiento;
	
	/**
	 * Nivel en el que se reavastece el almacenamiento
	 */
	private int nivelReAvastecimiento;
	
	//---------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
	public Almacenamiento()
	{
		this.id = 0;
		this.idSucursal = 0;
		this.idCategoria = 0;
		this.idTipoProducto = 0;
		this.capacidadVol = 0;
		this.capacidadPeso = 0;
		this.cantidad = 0;
		this.tipoAlmacenamiento = 0;
		this.nivelReAvastecimiento = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param id - El id del almacenamiento
	 * @param idSucursal
	 * @param idCategoria
	 * @param idTipoProducto
	 * @param capacidadVol
	 * @param capacidadPeso
	 * @param cantidad
	 * @param tipoAlmacenamiento
	 * @param nivelReAvastecimiento
	 */
	public Almacenamiento(long id, long idSucursal, long idCategoria, long idTipoProducto, double capacidadVol, double capacidadPeso, int cantidad, int tipoAlmacenamiento, int nivelReAvastecimiento)
	{
		this.id = id;
		this.idSucursal = idSucursal;
		this.idCategoria = idCategoria;
		this.idTipoProducto = idTipoProducto;
		this.capacidadVol = capacidadVol;
		this.capacidadPeso = capacidadPeso;
		this.cantidad = cantidad;
		this.tipoAlmacenamiento = tipoAlmacenamiento;
		this.nivelReAvastecimiento = nivelReAvastecimiento;
	}

	/**
	 * @return El id del almacenamiento
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del almacenamiento
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * @return id de la sucursal dueña del almacenamiento
	 */
	public long getIdSucursal() 
	{
		return idSucursal;
	}
	
	/**
	 * @param idSucursal - La nueva id de la sucursal
	 */
	public void setIdSucursal(long idSucursal)
	{
		this.idSucursal = idSucursal;
	}

	/**
	 * @return id de la categoria del almacenamiento
	 */
	public long getIdCategoria() 
	{
		return idCategoria;
	}

	/**
	 * @param idCategoria - La nueva id de la categoria del almacenamiento
	 */
	public void setIdCategoria(long idCategoria)
	{
		this.idCategoria = idCategoria;
	}

	/**
	 * @return La id del tipo de producto del almacenamiento
	 */
	public long getIdTipoProducto() 
	{
		return idTipoProducto;
	}
	
	/**
	 * @param idTipoProducto - La nueva Id del tipo de producto del almacenamiento
	 */
	public void setIdTipoProducto(long idTipoProducto)
	{
		this.idTipoProducto = idTipoProducto;
	}

	/**
	 * @return La capacidad de volumen del almacenamiento
	 */
	public double getCapacidadVolumen() 
	{
		return capacidadVol;
	}
	
	/**
	 * @param capacidadVol - La nueva capacidad de volumen
	 */
	public void setCapacidadVolumen(double capacidadVol)
	{
		this.capacidadVol = capacidadVol;
	}

	/**
	 * @return Capacidad en peso del almacenamiento
	 */
	public double getCapacidadPeso() 
	{
		return capacidadPeso;
	}
	
	/**
	 * @param capacidadPeso - La nueva capacidad de peso del almacenamiento
	 */
	public void setCapacidadPeso(double capacidadPeso)
	{
		this.capacidadPeso = capacidadPeso;
	}

	/**
	 * @return La cantidad actual de productos en el almacenamiento
	 */
	public int getCantidad() 
	{
		return cantidad;
	}
	
	/**
	 * @param cantidad - La nueva cantidad del almacenamiento
	 */
	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
	}

	/**
	 * @return El tipo de Almacenamiento
	 */
	public int getTipoAlmacen() 
	{
		return tipoAlmacenamiento;
	}
	
	/**
	 * @param tipoAlmacenamiento - El nuevo tipo de almacenamiento
	 */
	public void setTipoAlmacen(int tipoAlmacenamiento)
	{
		this.tipoAlmacenamiento = tipoAlmacenamiento;
	}

	/**
	 * @return Nivel de reavastecimiento del almacenamiento
	 */
	public int getNivelReavastecimiento() 
	{
		return nivelReAvastecimiento;
	}
	
	/**
	 * @param nivelReAvastecimiento - El nuevo nivel de reavastecimiento del almacen
	 */
	public void setNivelReavastecimiento(int nivelReAvastecimiento)
	{
		this.nivelReAvastecimiento = nivelReAvastecimiento;
	}
	
	/**
	 * @return Una cadena de caracteres con la información básica del bebedor
	 */
	@Override
	public String toString()
	{
		return "Almacenamiento [id=" + id + ", idSucursal=" + idSucursal + ", idCategoria=" + idCategoria +", idTipoProducto=" + idTipoProducto
				+ ", capacidadVol=" + capacidadVol + ", capacidadPeso=" + capacidadPeso + ", cantidad=" + cantidad + ", tipoAlmacenamiento=" + tipoAlmacenamiento 
				+ ", nivelReAvastecimiento=" + nivelReAvastecimiento + "]";
	}
}

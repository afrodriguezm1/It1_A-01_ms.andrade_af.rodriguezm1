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
	 * El código del producto guardado en el almacen
	 */
	private String codigoBarrasProducto;
	
	/**
	 * El identidicador de la categoria del almacen
	 */
	private long idCategoriaProd;
	
	/**
	 * El identificador del tipo de producto del almacen
	 */
	private long idTipoProd;
	
	/**
	 * Capacidad en volumen del almacenamiento
	 */
	private double capaVol;
	
	/**
	 * Capacidad en peso del almacenamiento
	 */
	private double capaPeso;
	
	/**
	 * Cantidad de productos actualmente en el almacenamiento
	 */
	private int cantidad;
	
	/**
	 * Tipo de almacenamiento del almacen
	 */
	private int tipoAlma;
	
	/**
	 * Nivel en el que se reavastece el almacenamiento
	 */
	private int nivelReAvast;
	
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
		this.codigoBarrasProducto = "";
		this.idCategoriaProd = 0;
		this.idTipoProd = 0;
		this.capaVol = 0;
		this.capaPeso = 0;
		this.cantidad = 0;
		this.tipoAlma = 0;
		this.nivelReAvast = 0;
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
	public Almacenamiento(long id, long idSucursal, String codigoBarrasProducto, long idCategoria, long idTipoProducto, double capacidadVol, double capacidadPeso, int cantidad, int tipoAlmacenamiento, int nivelReAvastecimiento)
	{
		this.id = id;
		this.idSucursal = idSucursal;
		this.codigoBarrasProducto = codigoBarrasProducto;
		this.idCategoriaProd = idCategoria;
		this.idTipoProd = idTipoProducto;
		this.capaVol = capacidadVol;
		this.capaPeso = capacidadPeso;
		this.cantidad = cantidad;
		this.tipoAlma = tipoAlmacenamiento;
		this.nivelReAvast = nivelReAvastecimiento;
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
	 * @return codigo del producto que se almacena
	 */
	public String getCodigoBarrasProducto()
	{
		return codigoBarrasProducto;
	}
	
	/**
	 * @param codigoBarrasProducto - El nuevo código de barras del producto
	 */
	public void setCodigoBarrasProducto(String codigoBarrasProducto)
	{
		this.codigoBarrasProducto = codigoBarrasProducto;
	}

	/**
	 * @return id de la categoria del almacenamiento
	 */
	public long getIdCategoriaProd() 
	{
		return idCategoriaProd;
	}

	/**
	 * @param idCategoria - La nueva id de la categoria del almacenamiento
	 */
	public void setIdCategoriaProd(long idCategoria)
	{
		this.idCategoriaProd = idCategoria;
	}

	/**
	 * @return La id del tipo de producto del almacenamiento
	 */
	public long getIdTipoProd() 
	{
		return idTipoProd;
	}
	
	/**
	 * @param idTipoProducto - La nueva Id del tipo de producto del almacenamiento
	 */
	public void setIdTipoProd(long idTipoProducto)
	{
		this.idTipoProd = idTipoProducto;
	}

	/**
	 * @return La capacidad de volumen del almacenamiento
	 */
	public double getCapaVol() 
	{
		return capaVol;
	}
	
	/**
	 * @param capacidadVol - La nueva capacidad de volumen
	 */
	public void setCapaVol(double capacidadVol)
	{
		this.capaVol = capacidadVol;
	}

	/**
	 * @return Capacidad en peso del almacenamiento
	 */
	public double getCapaPeso() 
	{
		return capaPeso;
	}
	
	/**
	 * @param capacidadPeso - La nueva capacidad de peso del almacenamiento
	 */
	public void setCapaPeso(double capacidadPeso)
	{
		this.capaPeso = capacidadPeso;
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
	public int getTipoAlma() 
	{
		return tipoAlma;
	}
	
	/**
	 * @param tipoAlmacenamiento - El nuevo tipo de almacenamiento
	 */
	public void setTipoAlma(int tipoAlmacenamiento)
	{
		this.tipoAlma = tipoAlmacenamiento;
	}

	/**
	 * @return Nivel de reavastecimiento del almacenamiento
	 */
	public int getNivelReavast() 
	{
		return nivelReAvast;
	}
	
	/**
	 * @param nivelReAvastecimiento - El nuevo nivel de reavastecimiento del almacen
	 */
	public void setNivelReavastecimiento(int nivelReAvastecimiento)
	{
		this.nivelReAvast = nivelReAvastecimiento;
	}
	
	/**
	 * @return Una cadena de caracteres con la información básica del bebedor
	 */
	@Override
	public String toString()
	{
		return "Almacenamiento [id=" + id + ", idSucursal=" + idSucursal + ", codigoBarrasProducto=" + codigoBarrasProducto + ", idCategoria=" + idCategoriaProd +", idTipoProducto=" + idTipoProd
				+ ", capacidadVol=" + capaVol + ", capacidadPeso=" + capaPeso + ", cantidad=" + cantidad + ", tipoAlmacenamiento=" + tipoAlma
				+ ", nivelReAvastecimiento=" + nivelReAvast + "]";
	}
}

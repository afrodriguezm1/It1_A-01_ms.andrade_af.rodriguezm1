package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de ALMACENAMIENTO.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * 
 * @author Andres Rodiguez - 201716905
 *
 */
public interface VOAlmacenamiento 
{
	//---------------------------------------------------------------------
	// M�todos
	//---------------------------------------------------------------------
	
	/**
	 * @return El id �NICO del almacenamiento
	 */
	public long getId();
	
	/**
	 * @return El id de la sucursal due�a del almacenamiento
	 */
	public long getIdSucursal();
	
	/**
	 * @return El codigo de barras del producto almacenado
	 */
	public String getCodigoBarrasProducto();
	
	/**
	 * @return El id de la categoria que acepta el almacenamiento
	 */
	public long getIdCategoriaProd();
	
	/**
	 * @return El id del tipo de producto que acepta el almacenamiento
	 */
	public long getIdTipoProd();
	
	/**
	 * @return La capacidad de volumen del almacenamiento
	 */
	public double getCapaVol();
	
	/**
	 * @return La capacidad de peso del almacenamiento
	 */
	public double getCapaPeso();
	
	/**
	 * @return Cantidad actual del producto en el almacenamiento
	 */
	public int getCantidad();
	
	/**
	 * @return Tipo de almacenamiento
	 */
	public int getTipoAlma();
	
	/**
	 * @return Nivel de reavastecimiento
	 */
	public int getNivelReavast();
}

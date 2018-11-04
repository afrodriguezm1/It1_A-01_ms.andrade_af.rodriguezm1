package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los m�todos get de ProductoSucursal.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */
public interface VOProductoSucursal
{
	public String getCodigoBarras();
	
	public long getIdSucursal();
	
	public long getPrecioUnitario();
	
	public long getPrecioUniMedida();
	
	public long getNumeroRecompra();
	
	public long getNivelReorden();
	
	@Override
	public String toString();
}

package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los m�todos get de ProductoSucursal.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */
public interface VOProductoSucursal
{
	public long getCodigoBarras();
	
	public long getIdSucursal();
	
	public int getPrecioUnitario();
	
	public int getPrecioUniMedida();
	
	public int getNumeroRecompra();
	
	public int getNivelReorden();
	
	@Override
	public String toString();
}

package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los m�todos get de ProductoProveedor.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */
public interface VOProductoProveedor 
{
	public long getCodigoBarras();
	
	public String getNitProveedor();
	
	public int getEsExclusivo();
	
	public int getPrecioUnitario();
	
	public int getPrecioUniMedida();
	
	@Override
	public String toString();
}

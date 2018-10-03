package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los métodos get de ProductoRedimible.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */
public interface VOProductoRedimible 
{
	public long getCodigoBarras();
	
	public long getIdSucursal();
	
	public int getPuntos();
	
	@Override
	public String toString();
}

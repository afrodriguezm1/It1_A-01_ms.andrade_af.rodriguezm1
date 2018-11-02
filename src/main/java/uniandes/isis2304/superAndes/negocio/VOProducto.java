package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los métodos get de Producto.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */
public interface VOProducto 
{
	public String getCodigoBarras();
	
	public long getIdCategoria();
	
	public long getIdTipoProducto();
	
	public String getNombre();
	
	public String getMarca();
	
	public String getPresentacion();
	
	public int getCantidadPresent();
	
	public String getUniMedida();
	
	public int getVolumen();
	
	public int getPeso();
	
	@Override
	public String toString();
}

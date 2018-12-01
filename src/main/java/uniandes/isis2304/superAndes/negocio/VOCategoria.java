package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los métodos get de Categoria.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */
public interface VOCategoria 
{

	public long getId();
	
	public String getNombre();
	
	@Override
	public String toString();
}

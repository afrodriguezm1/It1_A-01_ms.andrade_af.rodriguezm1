package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los m�todos get de OrdenPedido.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */

import java.sql.Timestamp;

public interface VOOrdenPedido 
{

	public long getId();
	
	public long getIdSucursal();
	
	public String getNitProveedor();
	
	public Timestamp getFechaExp();
	
	public Timestamp getFechaEst();
	
	public Timestamp getFechaEntrega();
	
	public String getEstado();
	
	@Override
	public String toString();
}

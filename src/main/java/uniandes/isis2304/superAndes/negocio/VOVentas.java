package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Interfaz para lo métodos get de VENTA
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public interface VOVentas 
{
	//---------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------
	
	public long getId();
	
	public long getIdSucursal();
	
	public String getEmailCliente();
	
	public long getConsecutivoFE();
	
	public String getCUFE();
	
	public Date getFechaVenta();
	
	@Override
	public String toString();
}

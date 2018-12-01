package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Interfaz para lo m�todos get de VENTA
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public interface VOVentas 
{
	//---------------------------------------------------------------------
	// M�todos
	//---------------------------------------------------------------------
	
	public long getId();
	
	public long getIdSucursal();
	
	public String getDocumentoCliente();
	
	public Date getFechaVenta();
	
	public long getPrecio();
	
	@Override
	public String toString();
}

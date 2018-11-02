package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los métodos get de Promocion.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */

import java.util.Date;


public interface VOPromocion
{

	
	public long getIdSucursal();
	
	public String getCodigoBarras();
	
	public String getNombre();
	
	public Date getFechaInicio();
	
	public Date getFechaFin();
	
	public int getTipoPromo();
	
	public int getValorOriginal();
	
	public int getValorPromo();
	
	@Override
	public String toString();
}

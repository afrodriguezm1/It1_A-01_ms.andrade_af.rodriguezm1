package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * Interfaz para los métodos get de Promocion.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author Mario Andrade
 * */

import java.sql.Timestamp;

public interface VOPromocion
{

	
	public long getIdSucursal();
	
	public long getCodigoBarras();
	
	public String getNombre();
	
	public Timestamp getFechaInicio();
	
	public Timestamp getFechaFin();
	
	public int getTipoPromo();
	
	public int getValorOriginal();
	
	public int getValorPromo();
	
	@Override
	public String toString();
}

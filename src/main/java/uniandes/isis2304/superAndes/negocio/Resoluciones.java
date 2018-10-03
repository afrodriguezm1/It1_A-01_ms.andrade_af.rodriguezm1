package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Clase para modelar el concepto RESOLUCION del negocio de SuperAndes
 * 
 * @author Andres Rodriguez - 201716905
 *
 */
public class Resoluciones implements VOResoluciones
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	private long numeroResolucion;
	
	private Date fechaHabilitacion;
	
	private Date fechaVencimiento;
	
	private long inicioConsecutivo;
	
	private long finConsecutivo;
	
	private long numActual;
	
	public Resoluciones()
	{
		this.numeroResolucion = 0;
		this.fechaHabilitacion = null;
		this.fechaVencimiento = null;
		this.inicioConsecutivo = 0;
		this.finConsecutivo = 0;
		this.numActual = 0;
	}
	
	public Resoluciones(long numeroResolucion, Date fechaHabilitacion, Date fechaVencimiento, long inicioConsecutivo, long finconsecutivo, long numActual)
	{
		this.numeroResolucion = numeroResolucion;
		this.fechaHabilitacion = fechaHabilitacion;
		this.fechaVencimiento = fechaVencimiento;
		this.inicioConsecutivo = inicioConsecutivo;
		this.finConsecutivo = finconsecutivo;
		this.numActual = numActual;
	}

	public long getNumeroResolucion() 
	{
		return numeroResolucion;
	}
	
	public void setNumeroResolucion(long numeroResolucion)
	{
		this.numeroResolucion = numeroResolucion;
	}

	public Date getFechaHabilitacion() 
	{
		return fechaHabilitacion;
	}
	
	public void setFechaHabilitacion(Date fechaHabilitacion)
	{
		this.fechaHabilitacion = fechaHabilitacion;
	}

	public Date getfechaVencimiento() 
	{
		return fechaVencimiento;
	}
	
	public void setFechaVencimiento(Date fechaVencimiento)
	{
		this.fechaVencimiento = fechaVencimiento;
	}

	public long getInicioConsecutivo() 
	{
		return inicioConsecutivo;
	}
	
	public void setInicioConsecutivo(long inicioConsecutivo)
	{
		this.inicioConsecutivo = inicioConsecutivo;
	}

	public long getFinConsecutivo() 
	{
		return finConsecutivo;
	}
	
	public void setFinConsecutivo(long finConsecutivo)
	{
		this.finConsecutivo = finConsecutivo;
	}

	public long getNumActual() 
	{
		return numActual;
	}
	
	public void setNumActual(long numActual)
	{
		this.numActual = numActual;
	}
	
	@Override
	public String toString()
	{
		return "Resolucion [numeroResolucion=" + numeroResolucion + ", fechaHabilitacion=" + fechaHabilitacion + ", fechaVencimiento=" + fechaVencimiento
				+ ", inicioConsecutivo=" + inicioConsecutivo + ", finConsecutivo" + finConsecutivo + ", numActual=" + numActual + "]";
	}
}

package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar un proveedor
 * @author Mario Andrade
 *
 */
public class Proveedor implements VOProveedor
{
 
	/**
	 * NIT del proveedor
	 */
	private String nit;
	
	/**
	 * Nombre del proveedor.
	 */
	private String nombre;
	
	/**
	 * Calificación dada al proveedor.
	 */
	private double calificacion;

	
	/**
	 * Constructor por defecto
	 */
	public Proveedor()
	{
		this.nit = "";
		this.nombre = "";
		this.calificacion =0;
	}
	
	/**
	 * Constructor de un proveedor con valores asignados
	 * @param nit
	 * @param nombre
	 * @param calificacion
	 */
	public Proveedor(String nit, String nombre, double calificacion) 
	{	
		this.nit = nit;
		this.nombre = nombre;
		this.calificacion = calificacion;
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the calificacion
	 */
	public double getCalificacion() {
		return calificacion;
	}

	/**
	 * @param calificacion the calificacion to set
	 */
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Proveedor [nit=" + nit + ", nombre=" + nombre + ", calificacion=" + calificacion + "]";
	}
	
	
}

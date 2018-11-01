package uniandes.isis2304.superAndes.persistencia;

public class SQLCarrito 
{
	//---------------------------------------------------------------------------
	// Constantes
	//---------------------------------------------------------------------------

	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	//---------------------------------------------------------------------------
	// Atríbutos
	//---------------------------------------------------------------------------

	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes psa;

	//---------------------------------------------------------------------------
	// Métodos
	//---------------------------------------------------------------------------

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLCarrito(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
}

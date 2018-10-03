package uniandes.isis2304.superAndes.negocio;

/**
 *  * Clase para modelar el concepto PERSONAS del negocio de SuperAndes
 * @author Andres Rodriguez - 201716905
 *
 */
public class Personas implements VOPersona
{
	//---------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------
	
	private String email;
	
	private long documento;
	
	private long puntaje;
	
	public Personas()
	{
		this.email = "";
		this.documento = 0;
		this.puntaje = 0;
	}
	
	public Personas(String email, long documento, long puntaje)
	{
		this.email= email;
		this.documento = documento;
		this.puntaje = puntaje;
	}

	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}

	public long getDocumento() 
	{
		return documento;
	}
	
	public void setDocumento(long documento)
	{
		this.documento = documento;
	}

	public long getPuntaje() 
	{
		return puntaje;
	}
	
	public void setPuntaje(long puntaje)
	{
		this.puntaje = puntaje;
	}
	
	@Override
	public String toString()
	{
		return "Persona [email=" + email + ", documento=" + documento +", puntaje=" + puntaje + "]";
	}
}

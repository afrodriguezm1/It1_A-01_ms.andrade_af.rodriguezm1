package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto EMPRESA del negocio de SuperAndes
 * @author Andres Rodriguez - 201716905
 *
 */
public class Empresas implements VOEmpresas
{
	//---------------------------------------------------------------------
		// Atributos
		//---------------------------------------------------------------------
		
		private String email;
		
		private String nit;
		
		private String direccion;
		
		public Empresas()
		{
			this.email = "";
			this.nit = "";
			this.direccion = "";
		}
		
		public Empresas(String email, String nit, String direccion)
		{
			this.email= email;
			this.nit = nit;
			this.direccion = direccion;
		}

		public String getEmail() 
		{
			return email;
		}
		
		public void setEmail(String email)
		{
			this.email = email;
		}

		public String getNit() 
		{
			return nit;
		}
		
		public void setNit(String nit)
		{
			this.nit = nit;
		}

		public String getDireccion() 
		{
			return direccion;
		}
		
		public void setdireccion(String direccion)
		{
			this.direccion = direccion;
		}
		
		@Override
		public String toString()
		{
			return "Empresa [email=" + email + ", nit=" +nit +", direccion=" + direccion + "]";
		}
}

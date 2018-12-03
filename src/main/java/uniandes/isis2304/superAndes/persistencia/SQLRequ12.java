package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Almacenamiento;
import uniandes.isis2304.superAndes.negocio.Requ12;

public class SQLRequ12 
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
		public SQLRequ12(PersistenciaSuperAndes psa)
		{
			this.psa = psa;
		}
		
		public List<Requ12> buscar(PersistenceManager pm, long id)
		{
			System.out.println("b");
			Query q = pm.newQuery(SQL, "SELECT * FROM (SELECT A.week, ProdmejorVen, ProdpeorVen, Provmassoli, provmenossoli FROM (select CODIGOBARRAS AS ProdMejorVen, MAX(conteo), week from( select CODIGOBARRAS, sum(cantidadProducto) as conteo, week from ( SELECT CODIGOBARRAS, to_char(fechaVenta,'WW') as week, cantidadProducto FROM VENTAS V, Info_Producto_Sucursal IPS  WHERE V.id = IPS.Idventa and V.idsucursal = ? ) group by codigoBarras, week) group by codigoBarras, week) A,(select CODIGOBARRAS AS PRODPeorVen, MIN(conteo), week from( select CODIGOBARRAS, sum(cantidadProducto) as conteo, week from ( SELECT CODIGOBARRAS, to_char(fechaVenta,'WW') as week, cantidadProducto FROM VENTAS V, Info_Producto_Sucursal IPS  WHERE V.id = IPS.Idventa and V.idsucursal = ? ) group by codigoBarras, week) group by codigoBarras, week) B,( SELECT NITPROVEEDOR AS PROVMASSOLI, MAX(CONTEO), WEEK FROM( SELECT NITPROVEEDOR, COUNT(WEEK) AS CONTEO, WEEK FROM( SELECT NITPROVEEDOR , TO_CHAR(FECHAEXP, 'WW') AS WEEK FROM ORDEN_PEDIDO WHERE IDSUCURSAL = ?)group by NITPROVEEDOR, week) GROUP BY NITPROVEEDOR, WEEK) C, ( SELECT NITPROVEEDOR AS PROVMENOSSOLI, MIN(CONTEO), WEEK FROM( SELECT NITPROVEEDOR, COUNT(WEEK) AS CONTEO, WEEK FROM( SELECT NITPROVEEDOR , TO_CHAR(FECHAEXP, 'WW') AS WEEK FROM ORDEN_PEDIDO WHERE IDSUCURSAL = ?)group by NITPROVEEDOR, week) GROUP BY NITPROVEEDOR, WEEK) D WHERE a.week = B.week AND c.week = D.week AND b.week = C.week) X, VENTAS y, INFO_PRODUCTO_SUCURSAL ips WHere Y.id = IPS.idVenta and x.prodmejorven = ips.codigoBarras ORDER BY week;");
			q.setResultClass(Requ12.class);
			q.setParameters(id,id,id,id);
			return (List<Requ12>) q.executeList();
		}

}

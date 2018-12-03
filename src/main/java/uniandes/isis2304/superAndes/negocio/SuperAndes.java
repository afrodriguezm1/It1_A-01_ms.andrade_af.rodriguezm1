package uniandes.isis2304.superAndes.negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

/**
 * Clase principal del negocio
 * Satisface todos los requerimientos funcionales del negocio
 * 
 * @author Andres y Mario
 *
 */
public class SuperAndes 
{
	//---------------------------------------------------------------------
	// Constantes
	//---------------------------------------------------------------------
	
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());
	
	private PersistenciaSuperAndes psa;
	
	public SuperAndes()
	{
		psa = PersistenciaSuperAndes.getInstance();
	}
	
	public SuperAndes(JsonObject tableConfig)
	{
		psa = PersistenciaSuperAndes.getInstance(tableConfig);
	}
	
	public void cerrarUnidadPersistencia()
	{
		psa.cerrarUnidadPersistencia();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Clientes
	 *****************************************************************/
	
	public Clientes agregarCliente(String email, String nombre, String documento, String direccion)
	{
		log.info("Adicionando cliente:" + email);
		Clientes cliente = psa.agregarCliente(email, nombre, documento, direccion);
		log.info("Adicionando cliente: " + email);
		return cliente;
	}
	
	public Clientes darCliente(String email)
	{
		log.info("Buscando cliente por email: " + email);
		Clientes cl = psa.darClientePorEmail(email);
		return cl;
	}
	
	public List<VOClientes> darVOClientes()
	{
		log.info("Generando los VO de clientes");
		{
			List<VOClientes> voClientes = new LinkedList<VOClientes>();
			for(Clientes tb : psa.darClientes())
			{
				voClientes.add(tb);
			}
			log.info("Generando los VO de Clientes: " + voClientes.size() +" existentes");
			return voClientes;
		}
	}
	
	public List<VOClientes> darVORequC10(int id, String restri)
	{
		log.info("Generando los VO de clintes");
		{
			List<VOClientes> voClientes = new LinkedList<VOClientes>();
			for(Clientes tb: psa.darRequFunC10(id, restri))
			{
				voClientes.add(tb);
			}
			log.info("Generando los VO de Clientes");
			return voClientes;
		}
	}
	
	public List<VOClientes> darVORequ11(int id, String restri)
	{
		log.info("Generando los VO de clintes");
		{
			List<VOClientes> voClientes = new LinkedList<VOClientes>();
			for(Clientes tb: psa.darRequFunC11(id, restri))
			{
				voClientes.add(tb);
			}
			log.info("Generando los VO de Clientes");
			return voClientes;
		}
	}
	
	public List<VOClientes> darVORequ13(int tipo)
	{
		log.info("Generando los VO de clintes");
		{
			List<VOClientes> voClientes = new LinkedList<VOClientes>();
			for(Clientes tb: psa.darRequFunC13(tipo))
			{
				voClientes.add(tb);
			}
			log.info("Generando los VO de Clientes");
			return voClientes;
		}
	}
	
	public long eliminarCliente(String email)
	{
		return psa.eliminarCliente(email);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las Categorias
	 *****************************************************************/
	public Categoria agregarCategoria(String nombre)
	{
		log.info("Adicionando Categoria: " + nombre);
		Categoria categoria = psa.adicionarCategoria(nombre);
		log.info("Adicionando empresa: " + nombre);
		return categoria;
	}
	
	public Categoria darCategoria(long id)
	{
		return psa.darCategoriaPorId(id);
	}
	
	public long eliminarCategoria(String id)
	{
		return psa.eliminarCategoriaPorId(id);
	}
	public List<VOCategoria> darVOCategoria()
	{
		log.info("Generando los VO de categoria");
		List<VOCategoria> voCategoria = new LinkedList<VOCategoria>();
		for(Categoria al : psa.darCategorias())
		{
			voCategoria.add(al);
		}
		log.info("Generando los VO de categoria: " + voCategoria.size() + " existentes");
		return voCategoria;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los tipos de producto
	 *****************************************************************/
	public TipoProducto darTipoProd(long id)
	{
		return psa.darTipoProd(id);
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Almacenes
	 *****************************************************************/
	
	public Almacenamiento agregarAlmacenamiento(long idSucursal, String codBarras,long idCat, long idTipo, double capaVol, double capaPeso, int tipo, int nivelRevast)
	{
		log.info("Adicionando Almacenamiento a sucursal: " + idSucursal + ", Con codigo de barras:" + codBarras);
		Almacenamiento almacenamiento = psa.agregarAlamacenamientoSucursal(idSucursal, codBarras, idCat, idTipo, capaVol, capaPeso, 0, tipo, nivelRevast);
		log.info("Adicionando Almacenamiento: " + almacenamiento);
		return almacenamiento;
	}
	
	public List<VOAlmacenamiento> darVOAlmacenamiento(long idSucursal)
	{
		log.info("Generando los VO de almacenamiento de la sucursal" + idSucursal);
		List<VOAlmacenamiento> voAlma = new LinkedList<VOAlmacenamiento>();
		for(Almacenamiento al : psa.darAlmacenesPorSucursal(idSucursal))
		{
			voAlma.add(al);
		}
		log.info("Generando los VO de almacenamientos: " + voAlma.size() + " existentes");
		return voAlma;
	}
	
	public long eliminarAlmacenamientoSucursal(long idSucursal, String codigoBarras, int tipo)
	{
		log.info("Eliminando Almacenamiento de sucursal: " + idSucursal + ", del producto con codig de barras: " + codigoBarras);
		long resp = psa.eliminarAlmacenDeSucursal(idSucursal, codigoBarras, tipo);
		log.info("Eliminando Almacenamiento de sucursal: " + resp + " tuplas eliminadas");
		return resp;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar ventas
	 *****************************************************************/
	
	public Ventas nuevaVenta(long idSucursal, String email, long precio)
	{
		log.info("Adicionando Nueva Venta: " + email);
		Ventas ventas = psa.agregarVenta(idSucursal, email, precio);
		log.info("Adicionando Nueva Venta: " + email);
		return ventas;
	}
	
	public List<Ventas> darVenta(String email)
	{
		return psa.darVentasDeUnCliente(email);
	}
	
	public InfoProdSucursal agregarProductoVenta(long idVenta, long idSucursal, String codigoBarras, int cantidad, long precioTotal, long precioUnitario)
	{
		log.info("Adicionando producto a venta: " + codigoBarras);
		InfoProdSucursal info = psa.agregarInfProdSucursal(idVenta, idSucursal, codigoBarras, cantidad, precioTotal, precioUnitario);
		log.info("Adicionando producto a venta: " + info);
		return info;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar productos
	 *****************************************************************/
	
	public Producto agregarProducto(String codigoBarras, long idCategoria, long idTipoProducto, String nombre, String marca,
			String presentacion, int cantidadPresent, String uniMedida, int volumen, int peso)
	{
		log.info("Adicionando producto:" + codigoBarras);
		Producto producto = psa.adicionarProducto(codigoBarras, idCategoria, idTipoProducto, nombre, marca, presentacion, cantidadPresent, uniMedida, volumen, peso);
		log.info("Adicionando producto:" + codigoBarras);
		return producto;
	}
	
	public Producto darProducto(String codBarras)
	{
		return psa.darProductoPorCodBarras(codBarras);
	}
	
	public long eliminarProducto(String codBarras)
	{
		return psa.eliminarProductoPorCodBarras(codBarras);
	}
	public List<VOProducto> darVOProductos()
	{
		log.info("Generando los VO de producto");
		{
			List<VOProducto> voProducto = new LinkedList<VOProducto>();
			for(Producto tb : psa.darProductos())
			{
				voProducto.add(tb);
			}
			log.info("Generando los VO de productos: " + voProducto.size() +" existentes");
			return voProducto;
		}
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar proveedores
	 *****************************************************************/
	public Proveedor agregarProveedor(String nit, String pNombre, double calificacion)
	{
		log.info("Adicionando proveedor:" + nit);
		Proveedor proveedor = psa.adicionarProveedor(nit, pNombre, calificacion);
		log.info("Adicionando proveedor:" + nit);
		return proveedor;
	}
	
	public Proveedor darProveedor(String nit)
	{
		return psa.darProveedorPorNit(nit);
	}
	
	public long eliminarProveedor(String nit)
	{
		return psa.eliminarProveedorPorNit(nit);
	}
	
	public List<VOProveedor> darVOProveedor()
	{
		log.info("Generando los VO de proveedor");
		{
			List<VOProveedor> voProveedor = new LinkedList<VOProveedor>();
			for(Proveedor tb : psa.darProveedores())
			{
				voProveedor.add(tb);
			}
			log.info("Generando los VO de proveedores: " + voProveedor.size() +" existentes");
			return voProveedor;
		}
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar promociones
	 *****************************************************************/
	public Promocion agregarPromocion(long idSucursal, String codigoBarras, String nombre, Date fechaInicio,
			Date fechaFin, int tipoPromo, int valorOriginal, int valorPromo)
	{
		log.info("Adicionando promocion:" + nombre);
		Promocion promocion = psa.adicionarPromocion(idSucursal, codigoBarras, nombre, fechaInicio, fechaFin, tipoPromo, valorOriginal, valorPromo);
				log.info("Adicionando promocion:" + nombre);
		return promocion;
	}
	
	public List<VOPromocion> darVOPromocion()
	{
		log.info("Generando los VO de promocion");
		{
			List<VOPromocion> voPromocion = new LinkedList<VOPromocion>();
			for(Promocion tb : psa.darPromociones())
			{
				voPromocion.add(tb);
			}
			log.info("Generando los VO de promociones: " + voPromocion.size() +" existentes");
			return voPromocion;
		}
	}
	
	public Promocion darPromocion(String codBarras, long idSucursal, String nombre)
	{
	   return psa.darPromocion(codBarras, idSucursal, nombre);
	}
	
	public long eliminarPromocion(String codBarras, long idSucursal, String nombre)
	{
		return psa.eliminarPromocion(codBarras, idSucursal, nombre);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar sucursales
	 *****************************************************************/
	public Sucursal agregarSucursal(String pNombre, String pDireccion, String pCiudad)
	{
		log.info("Adicionando sucursal:" + pNombre);
		Sucursal sucursal = psa.adicionarSucursal(pNombre, pDireccion, pCiudad);
				log.info("Adicionando sucursal:" + pNombre);
		return sucursal;
	}
	
	public Sucursal darSucursal(String nombre)
	{
	   return psa.darSucursalPordNombre(nombre);
	}
	
	public long eliminarSucursal(String nombre, String direccion)
	{
		return psa.eliminarSucursal(nombre, direccion);
	}
	
	public List<VOSucursal> darVOSucursal()
	{
		log.info("Generando los VO de sucursal");
		{
			List<VOSucursal> voSucursal = new LinkedList<VOSucursal>();
			for(Sucursal tb : psa.darSucursales())
			{
				voSucursal.add(tb);
			}
			log.info("Generando los VO de sucursales: " + voSucursal.size() +" existentes");
			return voSucursal;
		}
	}
	
	/**
	 * 
	 */
	public long darIdSucursal(long id)
	{
		return psa.darIdSucursal(id);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar carritos
	 *****************************************************************/
	public Carrito agregarCarrito(String email, long idSucursal, long precio, String estado)
	{
		log.info("Adicionando Carrito:" + email);
		Carrito carrito = psa.agregarCarrito(email, idSucursal, precio, estado);
				log.info("Adicionando Carrito:" + email);
		return carrito;
	}
	
	public void abandonarCarrito(String email, long idSucursal)
	{
		log.info("Abandonando Carrito:" + email);
		psa.abandonarCarrito(email, idSucursal);
		log.info("Abandonando Carrito:" + email);
	}
	
	public void eliminarCarritos()
	{
		log.info("Retornano productos de carritos abandonados:");
		psa.eliminarCarritos();
		log.info("Retornano productos de carritos abandonados:");
	}
	
	public List<VOCarrito> darVOCarrito()
	{
		log.info("Generando los VO de carrito");
		{
			List<VOCarrito> voCarrito = new LinkedList<VOCarrito>();
			for(Carrito tb : psa.darCarritos())
			{
				voCarrito.add(tb);
			}
			log.info("Generando los VO de carritos: " + voCarrito.size() +" existentes");
			return voCarrito;
		}
	}
	
	public Carrito darCarrito(String email, long idSucursal)
	{
		log.info("Dar carrito del cliente: " + email);
		Carrito car = psa.darCarrito(email, idSucursal);
			return car;
	}
	
	public Ventas finalizarCompra(long idCarrito,String email, long idSucursal, long precio)
	{
		log.info("Finalizar compra de carrito: " + idCarrito);		
		Ventas venta = psa.finalizarCompra(idCarrito, email, idSucursal, precio);
		return venta;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar carritos
	 *****************************************************************/
	public InfoProdCarrito agregarProductoCarrito(long idCarrito, String email, long idSucursal, String codigoBarras, int cantidad, long precioTotal, long precioUnitario)
	{
		log.info("Adicionando InfoProductoCarrito:" + idCarrito);
		InfoProdCarrito info = psa.agregarProductoCarrito(idCarrito, email, idSucursal, codigoBarras, cantidad, precioTotal, precioUnitario);
		log.info("Adicionando Carrito:" + idCarrito);
		return info;
	}
	
	public long eliminarProductoCarrito(long idCarrito, String email, long idSucursal, String codigoBarras)
	{
		log.info("Eliminar InfoProductoCarrito: " + codigoBarras);
		long resp = psa.eliminarProductoCarrito(idCarrito, email, idSucursal, codigoBarras);
		log.info("Eliminando InfoProductoCarrito ");
		return resp;
	}
	
	public List<VORequ12> darVORequ12(long id)
	{
		log.info("Generando los VO de clientes");
		{
			List<VORequ12> vorequ = new LinkedList<VORequ12>();
			for(Requ12 tb : psa.requ12(id))
			{
				vorequ.add(tb);
			}
			log.info("Generando los VO de Clientes: " + vorequ.size() +" existentes");
			return vorequ;
		}
	}
}

select c.EMAIL, c.NOMBRE, c.DOCUMENTO, c.DIRECCION
from info_producto_sucursal ips, ventas v, clientes c where ips.idventa = v.id and v.documentoCliente = c.documento and ips.preciounitario > 100000;

select c.EMAIL, c.NOMBRE, c.DOCUMENTO, c.DIRECCION
from info_producto_sucursal ips, producto p, ventas v, clientes c where (p.idCategoria = 8 OR p.idCategoria = 9) AND ips.codigobarras = p.codigobarras and ips.idventa = v.id AND v.documentocliente = c.documento;
select Nombre, DocumentoCliente, Direccion, Email from(
 select COUNT(mes) as conteo, Nombre, documentoCliente, direccion, email from (SELECT EXTRACT(MONTH FROM fechaVenta) AS mes, to_char(fechaventa,'YYYY') AS Anio, documentoCliente, email, direccion, nombre from ventas V, Clientes c where c.documento = v.documentoCliente group by documentoCLiente, email, direccion, nombre, EXTRACT(MONTH FROM fechaVenta), to_char(fechaventa,'YYYY')) group by email, direccion, documentoCliente, nombre);

select c.EMAIL, c.NOMBRE, c.DOCUMENTO, c.DIRECCION
from info_producto_sucursal ips, ventas v, clientes c where ips.idventa = v.id and v.documentoCliente = c.documento and ips.preciounitario > 100000;

select c.EMAIL, c.NOMBRE, c.DOCUMENTO, c.DIRECCION
from info_producto_sucursal ips, producto p, ventas v, clientes c where (p.idCategoria = 8 OR p.idCategoria = 9) AND ips.codigobarras = p.codigobarras and ips.idventa = v.id AND v.documentocliente = c.documento;

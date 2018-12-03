SELECT * FROM (SELECT A.week, Prod_mejor_Ven, Prod_peor_Ven, Prov_mas_soli, prov_menos_soli FROM (
select CODIGOBARRAS AS Prod_Mejor_Ven, MAX(conteo), week from(
select CODIGOBARRAS, sum(cantidadProducto) as conteo, week from (
SELECT CODIGOBARRAS, to_char(fechaVenta,'WW') as week, cantidadProducto FROM VENTAS V, Info_Producto_Sucursal IPS  WHERE V.id = IPS.Idventa and V.idsucursal = 2 ) group by codigoBarras, week) group by codigoBarras, week) A,(

select CODIGOBARRAS AS PROD_Peor_Ven, MIN(conteo), week from(
select CODIGOBARRAS, sum(cantidadProducto) as conteo, week from (
SELECT CODIGOBARRAS, to_char(fechaVenta,'WW') as week, cantidadProducto FROM VENTAS V, Info_Producto_Sucursal IPS  WHERE V.id = IPS.Idventa and V.idsucursal = 2 ) group by codigoBarras, week) group by codigoBarras, week) B,(

SELECT NITPROVEEDOR AS PROV_MAS_SOLI, MAX(CONTEO), WEEK FROM(
SELECT NITPROVEEDOR, COUNT(WEEK) AS CONTEO, WEEK FROM(
SELECT NITPROVEEDOR , TO_CHAR(FECHAEXP, 'WW') AS WEEK FROM ORDEN_PEDIDO WHERE IDSUCURSAL = 2)group by NITPROVEEDOR, week) GROUP BY NITPROVEEDOR, WEEK) C, (

SELECT NITPROVEEDOR AS PROV_MENOS_SOLI, MIN(CONTEO), WEEK FROM(
SELECT NITPROVEEDOR, COUNT(WEEK) AS CONTEO, WEEK FROM(
SELECT NITPROVEEDOR , TO_CHAR(FECHAEXP, 'WW') AS WEEK FROM ORDEN_PEDIDO WHERE IDSUCURSAL = 2)group by NITPROVEEDOR, week) GROUP BY NITPROVEEDOR, WEEK) D WHERE a.week = B.week AND c.week = D.week AND b.week = C.week) X, VENTAS y, INFO_PRODUCTO_SUCURSAL ips WHere Y.id = IPS.idVenta and x.prod_mejor_ven = ips.codigoBarras;

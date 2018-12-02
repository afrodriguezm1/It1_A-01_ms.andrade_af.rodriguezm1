select CODIGOBARRAS, Max(conteo), week from(
select CODIGOBARRAS, count(week) as conteo, week from (
SELECT CODIGOBARRAS, to_char(fechaVenta,'WW') as week FROM VENTAS V, Info_Producto_Sucursal IPS  WHERE V.id = IPS.Idventa and V.idsucursal = 2 ) group by codigoBarras, week) group by codigoBarras, week;

select CODIGOBARRAS, MIN(conteo), week from(
select CODIGOBARRAS, count(week) as conteo, week from (
SELECT CODIGOBARRAS, to_char(fechaVenta,'WW') as week FROM VENTAS V, Info_Producto_Sucursal IPS  WHERE V.id = IPS.Idventa and V.idsucursal = 2 ) group by codigoBarras, week) group by codigoBarras, week;

SELECT NITPROVEEDOR, MAX(CONTEO), WEEK FROM(
SELECT NITPROVEEDOR, COUNT(WEEK) AS CONTEO, WEEK FROM(
SELECT NITPROVEEDOR , TO_CHAR(FECHAEXP, 'WW') AS WEEK FROM ORDEN_PEDIDO WHERE IDSUCURSAL = 2)group by NITPROVEEDOR, week) GROUP BY NITPROVEEDOR, WEEK;

SELECT NITPROVEEDOR, MIN(CONTEO), WEEK FROM(
SELECT NITPROVEEDOR, COUNT(WEEK) AS CONTEO, WEEK FROM(
SELECT NITPROVEEDOR , TO_CHAR(FECHAEXP, 'WW') AS WEEK FROM ORDEN_PEDIDO WHERE IDSUCURSAL = 2)group by NITPROVEEDOR, week) GROUP BY NITPROVEEDOR, WEEK;

select TO_CHAR(fechaVenta, 'MM'), fechaventa from ventas;
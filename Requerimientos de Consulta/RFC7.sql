SELECT X.ID_SUCURSAL, x.PRECIO_TOT, ADD_MONTHS((SELECT MIN(FECHA_VENTA) FROM VENTAS), A) AS FECHA FROM (
SELECT ID_SUCURSAL, MAX(PRES) as PRECIO_TOT FROM(
SELECT ID_SUCURSAL, SUM(PRECIO) As Pres, A
FROM(
SELECT  d.id_sucursal,
        d.precio_unitario*c.cantidad_producto as precio,
        FLOOR((D.FECHA_VENTA - (SELECT MIN(FECHA_VENTA) FROM VENTAS))/(7*4)) AS A
FROM info_producto_sucursal C
inner join (SELECT  a.codigo_barras,
            a.id_sucursal,
            a.precio_unitario,
            b.id,
            B.FECHA_VENTA
            FROM    PRODUCTO_SUCURSAL A
                    INNER JOIN VENTAS B
                    ON a.id_sucursal = b.id_sucursal) D
            on c.codigo_barras = d.codigo_barras
            and c.id_venta = d.id)
GROUP BY ID_SUCURSAL, A)
GROUP BY ID_SUCURSAL) X INNER JOIN (SELECT ID_SUCURSAL, SUM(PRECIO) As Pres, A
FROM(
SELECT  d.id_sucursal,
        d.precio_unitario*c.cantidad_producto as precio,
        FLOOR((D.FECHA_VENTA - (SELECT MIN(FECHA_VENTA) FROM VENTAS))/(7*4)) AS A
FROM info_producto_sucursal C
inner join (SELECT  a.codigo_barras,
            a.id_sucursal,
            a.precio_unitario,
            b.id,
            B.FECHA_VENTA
            FROM    PRODUCTO_SUCURSAL A
                    INNER JOIN VENTAS B
                    ON a.id_sucursal = b.id_sucursal) D
            on c.codigo_barras = d.codigo_barras
            and c.id_venta = d.id)
GROUP BY ID_SUCURSAL, A) Y ON x.PRECIO_TOT = y.pres AND x.id_sucursal = Y.ID_SUCURSAL;

SELECT X.ID_SUCURSAL, x.PRECIO_TOT, ADD_MONTHS((SELECT MIN(FECHA_VENTA) FROM VENTAS), A) AS FECHA FROM (
SELECT ID_SUCURSAL, MIN(PRES) as PRECIO_TOT FROM(
SELECT ID_SUCURSAL, SUM(PRECIO) As Pres, A
FROM(
SELECT  d.id_sucursal,
        d.precio_unitario*c.cantidad_producto as precio,
        FLOOR((D.FECHA_VENTA - (SELECT MIN(FECHA_VENTA) FROM VENTAS))/(7*4)) AS A
FROM info_producto_sucursal C
inner join (SELECT  a.codigo_barras,
            a.id_sucursal,
            a.precio_unitario,
            b.id,
            B.FECHA_VENTA
            FROM    PRODUCTO_SUCURSAL A
                    INNER JOIN VENTAS B
                    ON a.id_sucursal = b.id_sucursal) D
            on c.codigo_barras = d.codigo_barras
            and c.id_venta = d.id)
GROUP BY ID_SUCURSAL, A)
GROUP BY ID_SUCURSAL) X INNER JOIN (SELECT ID_SUCURSAL, SUM(PRECIO) As Pres, A
FROM(
SELECT  d.id_sucursal,
        d.precio_unitario*c.cantidad_producto as precio,
        FLOOR((D.FECHA_VENTA - (SELECT MIN(FECHA_VENTA) FROM VENTAS))/(7*4)) AS A
FROM info_producto_sucursal C
inner join (SELECT  a.codigo_barras,
            a.id_sucursal,
            a.precio_unitario,
            b.id,
            B.FECHA_VENTA
            FROM    PRODUCTO_SUCURSAL A
                    INNER JOIN VENTAS B
                    ON a.id_sucursal = b.id_sucursal) D
            on c.codigo_barras = d.codigo_barras
            and c.id_venta = d.id)
GROUP BY ID_SUCURSAL, A) Y ON x.PRECIO_TOT = y.pres AND x.id_sucursal = Y.ID_SUCURSAL;
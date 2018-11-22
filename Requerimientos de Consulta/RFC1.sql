SELECT  ID_SUCURSAL,
        SUM(PRECIO) AS ACUMULADO
        FROM (
SELECT  d.id_sucursal,
        d.precio_unitario*c.cantidad_producto as precio
FROM info_producto_sucursal C
inner join (SELECT  a.codigo_barras,
            a.id_sucursal,
            a.precio_unitario,
            b.id
            FROM    PRODUCTO_SUCURSAL A
                    INNER JOIN VENTAS B
                    ON a.id_sucursal = b.id_sucursal
                    WHERE b.fecha_venta BETWEEN TO_DATE('01/01/2018', 'DD/MM/YYYY') AND TO_DATE('31/12/2018', 'DD/MM/YYYY')) D
            on c.codigo_barras = d.codigo_barras
            and c.id_venta = d.id)
GROUP BY ID_SUCURSAL;

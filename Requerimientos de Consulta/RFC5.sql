SELECT  A.ID_SUCURSAL,
        b.email,
        b.direccion,
        a.fecha_exp,
        a.fecha_Est,
        a.fecha_entrega
FROM   ORDEN_PEDIDO A
                INNER JOIN EMPRESAS B
                ON a.nit_proveedor = b.nit;

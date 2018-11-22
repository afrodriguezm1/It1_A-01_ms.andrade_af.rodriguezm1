SELECT nombre,
       output,
       numcompras
FROM  (SELECT nombre,
              numcompras,
              Nvl(Substr(fecha, 0, Instr(fecha, '-') + 4), fecha) AS output
       FROM  (SELECT Count(email)                    AS numCompras,
                     nombre,
                     To_char(fecha_venta, 'MM-YYYY') AS fecha
              FROM   clientes
                     inner join ventas
                             ON clientes.email = ventas.email_cliente
              GROUP  BY email,
                        nombre,
                        fecha_venta))
WHERE  numcompras >= 2
GROUP  BY nombre,
          output,
          numcompras; 

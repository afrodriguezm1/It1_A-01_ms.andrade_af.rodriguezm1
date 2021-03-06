SELECT EMAIL, NOMBRE, DOCUMENTO, DIRECCION
FROM INFO_PRODUCTO_SUCURSAL IPS, (
SELECT *
FROM CLIENTES C, VENTAS V
WHERE C.DOCUMENTO = V.DOCUMENTOCLIENTE) B
WHERE IPS.IDVENTA = B.ID
and IPS.CODIGOBARRAS = '1000000000003'
and B.FECHAVENTA between TO_DATE('01/02/2018', 'DD/MM/YYYY') AND TO_DATE('31/03/2018', 'DD/MM/YYYY')
GROUP BY B.DOCUMENTO, B.NOMBRE, B.DIRECCION, B.EMAIL
ORDER BY DOCUMENTO;
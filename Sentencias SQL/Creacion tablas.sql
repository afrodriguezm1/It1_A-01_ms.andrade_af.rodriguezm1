-- Creaci�n del secuenciador
 CREATE sequence SuperAndes_sequence;
 
-------------------------------------------------------------------------
-- Creaci�n de la tabla Sucursal 
-------------------------------------------------------------------------

 CREATE TABLE SUCURSAL
  (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
      Nombre VARCHAR2(255 BYTE) NOT NULL,
      Direccion VARCHAR2(255 BYTE) NOT NULL,
      Ciudad VARCHAR2(255 BYTE) NOT NULL,
      CONSTRAINT SUCURSAL_PK PRIMARY KEY (Id)
   );


-------------------------------------------------------------------------
-- Creaci�n de la tabla Proveedor
-------------------------------------------------------------------------

-- Creaci�n de la tabla Proveedor y especificaci�n de sus restricciones
 CREATE TABLE PROVEEDOR
  (Nit VARCHAR2(255 BYTE) NOT NULL,
       Nombre VARCHAR2(255 BYTE) NOT NULL,
       Calificacion NUMBER NOT NULL,
       CONSTRAINT PROVEEDOR_PK PRIMARY KEY (Nit)
   );
   
-- Restrincciones-------------------------------------------------------
 ALTER TABLE PROVEEDOR
 ADD CONSTRAINT NOMBRE_PROVEEDOR
      UNIQUE (Nombre)  
 ENABLE;

-- Validar calificacion entre 0 y 5
 ALTER TABLE PROVEEDOR
 ADD CONSTRAINT CK_Calificacion
     CHECK( Calificacion BETWEEN 0 AND 5)
 ENABLE;

-------------------------------------------------------------------------
-- Creaci�n de la tabla Categoria
-------------------------------------------------------------------------  

 CREATE TABLE CATEGORIA
  (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
      Nombre VARCHAR2(255 BYTE) NOT NULL,
      CONSTRAINT CATEGORIA_PK PRIMARY KEY (Id)
   );
   
    ALTER TABLE CATEGORIA
 ADD CONSTRAINT NOMBRECATEGORIA
      UNIQUE (Nombre)  
 ENABLE;
  
-------------------------------------------------------------------------
-- Creaci�n de la Tipo_Producto
-------------------------------------------------------------------------
  
 CREATE TABLE TIPO_PRODUCTO
   (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
       IdCategoria NUMBER,
       Nombre VARCHAR2(255 BYTE) NOT NULL,
       CONSTRAINT TIPO_PRODUCTO_PK PRIMARY KEY (Id, IdCategoria)
   );
   
 -- Restrincciones -----------------------------------------------------
 -- Agrega restrincci�n de no repetir nombre
 ALTER TABLE TIPO_PRODUCTO
 ADD CONSTRAINT UN_TIPOPROD_NOMBRE
      UNIQUE (Nombre)  
 ENABLE;
 
  -- Agrega FK hacia categoria
 ALTER TABLE TIPO_PRODUCTO
 ADD CONSTRAINT fk_categoria_tp
     FOREIGN KEY (IdCategoria)
     REFERENCES categoria(Id)    
 ENABLE;
 
-------------------------------------------------------------------------
-- Creaci�n de la tabla Producto
-------------------------------------------------------------------------
 
 CREATE TABLE PRODUCTO
   (CodigoBarras VARCHAR2(13),
        IdCategoria NUMBER NOT NULL,
        IdTipoProducto NUMBER NOT NULL,
        Nombre VARCHAR2(255 BYTE) NOT NULL,
        Marca VARCHAR2(255 BYTE) NOT NULL,
        Presentacion VARCHAR2(255 BYTE) NOT NULL,
        CantidadPresen NUMBER NOT NULL,
        UniMedida VARCHAR2(255 BYTE) NOT NULL,
        Volumen NUMBER NOT NULL,
        Peso NUMBER NOT NULL,
        CONSTRAINT PRODUCTO_PK PRIMARY KEY (CodigoBarras)
    );
    
-- Restrincciones --------------------------------------------------------

-- Agrega FK de idCategoria
 ALTER TABLE PRODUCTO
 ADD CONSTRAINT fk_categoria_prod
     FOREIGN KEY (IdCategoria)
     REFERENCES categoria(Id)    
 ENABLE;
 
 -- Agregar FK IdTipoProducto --------------------------------------------
 ALTER TABLE PRODUCTO
 ADD CONSTRAINT fk_tipo_producto_prod
     FOREIGN KEY (IdTipoProducto)
     REFERENCES categoria(Id)    
 ENABLE;
 
 -- Valida el tama�o del codigo de barras --------------------------------
 ALTER TABLE PRODUCTO
 ADD CONSTRAINT CK_Cod_Barras_prod
     CHECK (LENGTH (CodigoBarras) = 13)
 ENABLE;
 
 -- Valida opciones de unidad de medida ----------------------------------
 ALTER TABLE PRODUCTO
 ADD CONSTRAINT CK_Uni_medida
     CHECK (UniMedida IN ('gr', 'ml'))
 ENABLE;
 
-------------------------------------------------------------------------
-- Creaci�n de la tabla Producto_Proveedor
-------------------------------------------------------------------------
 
 CREATE TABLE PRODUCTO_PROVEEDOR
   (CodigoBarras VARCHAR2(13),
        NitProveedor VARCHAR2(255 BYTE),
        EsExclusivo NUMBER NOT NULL,
        PrecioUnitario NUMBER NOT NULL,
        PrecioUnidadMedida NUMBER NOT NULL,
        CONSTRAINT PRODUCTO_PROVEEDOR_PK PRIMARY KEY (CodigoBarras, NitProveedor)
    );

-- Restincciones --------------------------------------------------------

-- Agrega FK nit del proveedor
 ALTER TABLE PRODUCTO_PROVEEDOR
 ADD CONSTRAINT fk_proveedor_nit_prodProve
     FOREIGN KEY (NitProveedor)
     REFERENCES proveedor(Nit)    
 ENABLE;
 
 -- Agrega FK codigoBarras
 ALTER TABLE PRODUCTO_PROVEEDOR
 ADD CONSTRAINT fk_codBarras_prodProve
     FOREIGN KEY (CodigoBarras)
     REFERENCES producto(CodigoBarras)    
 ENABLE;
 
 -- Agrega CK del lenght = 13
 ALTER TABLE PRODUCTO_PROVEEDOR
 ADD CONSTRAINT CK_Cod_barras_prodProve
     CHECK (LENGTH (CodigoBarras) = 13)
 ENABLE;
 
 -- Boolean para saber si el producto es exclusivo
 -- 1 = si, 0 = no
 ALTER TABLE PRODUCTO_PROVEEDOR
 ADD CONSTRAINT CK_EsExclusivo
     CHECK (EsExclusivo = 1 OR EsExclusivo = 0)
 ENABLE;
 
-------------------------------------------------------------------------
-- Creaci�n de la tabla Producto_Sucursal
-------------------------------------------------------------------------

 CREATE TABLE PRODUCTO_SUCURSAL
   (CodigoBarras VARCHAR2(13),
        IdSucursal NUMBER,
        PrecioUnitario NUMBER NOT NULL,
        PrecioUnidadMedida NUMBER NOT NULL,
        NumeroRecompra NUMBER NOT NULL,
        NivelReorden NUMBER NOT NULL,
        CONSTRAINT PRODUCTO_SUCURSAL_PK PRIMARY KEY (CodigoBarras, IdSucursal)
    );

-- Restrincciones -------------------------------------------------------

-- Agregar FK de codigoBarras
 ALTER TABLE PRODUCTO_SUCURSAL
 ADD CONSTRAINT fk_producto_codBarras_prodSuc
     FOREIGN KEY (CodigoBarras)
     REFERENCES producto(CodigoBarras)    
 ENABLE;
 
 -- Agregar FK sucursalID
 ALTER TABLE PRODUCTO_SUCURSAL
 ADD CONSTRAINT fk_sucursal_id_prodSuc
     FOREIGN KEY (IdSucursal)
     REFERENCES sucursal(Id)    
 ENABLE;
 
 -- Agrega CK codigoBarras lenght
 ALTER TABLE PRODUCTO_SUCURSAL
 ADD CONSTRAINT CK_Cod_barras_prodSuc
     CHECK (LENGTH (CodigoBarras) = 13)
 ENABLE;
 
-------------------------------------------------------------------------
-- Creaci�n de la tabla Producto_Redimible
-------------------------------------------------------------------------

 CREATE TABLE PRODUCTO_REDIMIBLE
   (Codigobarras VARCHAR2(13),
        Idsucursal NUMBER,
        Puntos NUMBER NOT NULL,
        CONSTRAINT PRODUCTO_REDIMIBLE_PK PRIMARY KEY (CodigoBarras, IdSucursal)
    );

-- Restrincciones ------------------------------------------------------
-- Agrega FK codigoBarras
 ALTER TABLE PRODUCTO_REDIMIBLE
 ADD CONSTRAINT fk_producto_codBarras_prodRed
     FOREIGN KEY (CodigoBarras)
     REFERENCES producto(CodigoBarras)    
 ENABLE;
 
 -- Agregar FK idSucursal
 ALTER TABLE PRODUCTO_REDIMIBLE
 ADD CONSTRAINT fk_sucursal_id_prodRed
     FOREIGN KEY (IdSucursal)
     REFERENCES sucursal(Id)    
 ENABLE;
 
 -- Agregar CK cofigoBarras length
 ALTER TABLE PRODUCTO_REDIMIBLE
 ADD CONSTRAINT CK_Cod_barras_prodRed
     CHECK (LENGTH (CodigoBarras) = 13)
 ENABLE;
 
 -------------------------------------------------------------------------
-- Creaci�n de la tabla Promocion
-------------------------------------------------------------------------

 CREATE TABLE PROMOCION
   (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
       IdSucursal NUMBER,
       CodigoBarras VARCHAR2(13) NOT NULL,
       Nombre VARCHAR2(255 BYTE) NOT NULL,
       FechaInicio DATE NOT NULL,
       FechaFin DATE NOT NULL,
       TipoPromocion NUMBER NOT NULL,
       Valor1 NUMBER NOT NULL,
       Valor2 NUMBER NOT NULL,
       CONSTRAINT PROMOCION_PK PRIMARY KEY (Id, IdSucursal)
    );

-- Restrincciones ---------------------------------------------------------
-- Agregar FK codigoBarras
 ALTER TABLE PROMOCION
 ADD CONSTRAINT fk_producto_codBarras_prom
     FOREIGN KEY (CodigoBarras)
     REFERENCES producto(CodigoBarras)    
 ENABLE;
 
 -- Agregar FK idSucursal
 ALTER TABLE PROMOCION
 ADD CONSTRAINT fk_sucursal_id_prom
     FOREIGN KEY (IdSucursal)
     REFERENCES sucursal(Id)    
 ENABLE;
 
 -- Agregar CK codigoBarras length
ALTER TABLE PROMOCION
 ADD CONSTRAINT CK_Cod_barras_prom
     CHECK (LENGTH (CodigoBarras) = 13)
 ENABLE;
 
 -------------------------------------------------------------------------
-- Creaci�n de la tabla Orden_Pedido
-------------------------------------------------------------------------
 CREATE TABLE ORDEN_PEDIDO
   (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
       IdSucursal NUMBER,
       NitProveedor VARCHAR2(255 BYTE),
       FechaExp DATE NOT NULL,
       FechaEst DATE NOT NULL,
       FechaEntrega DATE NOT NULL,
       Estado VARCHAR2 (255 BYTE) NOT NULL,
       CONSTRAINT ORDEN_PEDIDO_PK PRIMARY KEY(Id, IdSucursal, NitProveedor)
    );
    
 -- Restrincciones --------------------------------------------------------
 -- Agrega FK idSucursal
 ALTER TABLE ORDEN_PEDIDO
 ADD CONSTRAINT fk_sucursal_id_ordPed
     FOREIGN KEY (IdSucursal)
     REFERENCES sucursal(Id)    
 ENABLE;
 
 -- Agregar FK nitProveedor
 ALTER TABLE ORDEN_PEDIDO
 ADD CONSTRAINT fk_proveedor_nit_ordPed
     FOREIGN KEY (NitProveedor)
     REFERENCES proveedor(Nit)    
 ENABLE;
 
 -- Agregar CK estado
 ALTER TABLE ORDEN_PEDIDO
 ADD CONSTRAINT CK_Estado
     CHECK (Estado IN ('Espera', 'Entregado'))
 ENABLE;

-------------------------------------------------------------------------
-- Creaci�n de la tabla Info_Producto_Proveedor
-------------------------------------------------------------------------
 CREATE TABLE INFO_PRODUCTO_PROVEEDOR
   (IdOrden NUMBER,
       CodigoBarras VARCHAR2(13) NOT NULL,
       CantidadProducto NUMBER NOT NULL,
       PrecioTotal NUMBER NOT NULL,
       PrecioUnitario NUMBER NOT NULL,
       CONSTRAINT INFO_PROD_PROVEEDOR_PK PRIMARY KEY (IdOrden, CodigoBarras)
    );
    
-- Restrincciones ---------------------------------------------------------
-- Agrega FK codigoBarras
 ALTER TABLE INFO_PRODUCTO_PROVEEDOR
 ADD CONSTRAINT fk_codBarras_infProdProv
     FOREIGN KEY (CodigoBarras)
     REFERENCES producto(CodigoBarras)    
 ENABLE;
 
 -- Agregar CK codigoBarras length
 ALTER TABLE INFO_PRODUCTO_PROVEEDOR
 ADD CONSTRAINT CK_Cod_barras_infProdProv
     CHECK (LENGTH (CodigoBarras) = 13)
 ENABLE;
 
 -------------------------------------------------------------------------
-- Creaci�n de la tabla Almacenamiento
-------------------------------------------------------------------------

CREATE TABLE ALMACENAMIENTO
   (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
       IdSucursal NUMBER,
       codigoBarrasProducto varchar(13),
       IdCategoriaProd NUMBER,
       IdTipoProd NUMBER,
       CapaVol NUMBER NOT NULL,
       CapaPeso NUMBER NOT NULL,
       Cantidad NUMBER NOT NULL,
       TipoAlma NUMBER NOT NULL,
       NivelReavast NUMBER NOT NULL,
       CONSTRAINT ALMACENAMIENTO_PK PRIMARY KEY (Id,
                                                 IdSucursal,
                                                 IdCategoriaProd,
                                                 IdTipoProd
                                                 )
    );
    
-- Restrincciones ----------------------------------------------------------
-- Agregra FK idSucursal
 ALTER TABLE ALMACENAMIENTO
 ADD CONSTRAINT fk_sucursal_id_alm
     FOREIGN KEY (IdSucursal)
     REFERENCES sucursal(Id)    
 ENABLE;
   
-- AgregaFK idCategoria    
 ALTER TABLE ALMACENAMIENTO
 ADD CONSTRAINT fk_categoria_id_alm
     FOREIGN KEY (IdCategoriaProd)
     REFERENCES categoria(Id)    
 ENABLE;
 
 -- Agrega FK tipoProducto
 ALTER TABLE ALMACENAMIENTO
 ADD CONSTRAINT fk_tipoProd_id_alm
     FOREIGN KEY (IdTipoProd, IdCategoriaProd)
     REFERENCES tipo_producto(Id, IdCategoria)    
 ENABLE;
 
 -- Agrega FK CodigoBarras
 ALTER TABLE ALMACENAMIENTO
ADD CONSTRAINT fk_codigo_barras_alm
	FOREIGN KEY(codigoBarrasProducto)
	REFERENCES producto(codigoBarras) 
ENABLE;
 
 -- Agrega CK de tipo
 -- 1 = Bodega, 2 = Estante
 ALTER TABLE ALMACENAMIENTO
 ADD CONSTRAINT CK_Tipo_alma
     CHECK ( TipoAlma = 1 OR TipoAlma = 2)    
 ENABLE;
 
 -------------------------------------------------------------------------
-- Creaci�n de la tabla Clientes
-------------------------------------------------------------------------

 CREATE TABLE CLIENTES
   (Email VARCHAR2 (255 BYTE),
          Nombre VARCHAR2 (255 BYTE) NOT NULL,
          CONSTRAINT CLIENTES_PK PRIMARY KEY (Email)
    );

-------------------------------------------------------------------------
-- Creaci�n de la tabla Personas
-------------------------------------------------------------------------

 CREATE TABLE PERSONAS
   (Email VARCHAR2 (255 BYTE),
          Documento NUMBER,
          Puntaje NUMBER NOT NULL,
          CONSTRAINT PERSONAS_PK PRIMARY KEY (Email, Documento)
    );
 
 -- Restrincciones ------------------------------------------------------
 -- Agrega FK emailCliente
 ALTER TABLE PERSONAS
 ADD CONSTRAINT fk_clientes_email_pers
     FOREIGN KEY (Email)
     REFERENCES clientes(Email)      
 ENABLE;    
          
-------------------------------------------------------------------------
-- Creaci�n de la tabla Empresa
-------------------------------------------------------------------------
 CREATE TABLE EMPRESAS
   (Email VARCHAR2 (255 BYTE),
          Nit VARCHAR2 (255 BYTE),
          Direccion VARCHAR2 (255 BYTE) NOT NULL,
          CONSTRAINT EMPRESAS_PK PRIMARY KEY (Email, Nit)
    );
 -- Restrincciones ------------------------------------------------------
 -- Agrega FK emailCliente
 ALTER TABLE EMPRESAS
 ADD CONSTRAINT fk_clientes_email_emp
     FOREIGN KEY (Email)
     REFERENCES clientes(Email)      
 ENABLE;   
 
 -------------------------------------------------------------------------
-- Creaci�n de la tabla Ventas
-------------------------------------------------------------------------

 CREATE TABLE VENTAS
   (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
       IdSucursal NUMBER,
       EmailCliente VARCHAR2 (255 BYTE),
       FechaVenta DATE NOT NULL,
       Precio NUMBER,
       CONSTRAINT VENTAS_PK PRIMARY KEY (Id, IdSucursal, EmailCliente)
    );

-- Restrincciones --------------------------------------------------------
-- Agregar FK emailCliente
 ALTER TABLE VENTAS
 ADD CONSTRAINT fk_clientes_email_vent
     FOREIGN KEY (EmailCliente)
     REFERENCES clientes(Email)      
 ENABLE;   
 
 -- Agregar FK idSucursal
 ALTER TABLE VENTAS
 ADD CONSTRAINT fk_sucursal_id_vent
     FOREIGN KEY (IdSucursal)
     REFERENCES sucursal(Id)      
 ENABLE;
 
 -------------------------------------------------------------------------
-- Creaci�n de la tabla Info_Prod_sucursal
-------------------------------------------------------------------------

 CREATE TABLE INFO_PRODUCTO_SUCURSAL
   (IdVenta NUMBER,
   idSucursal NUMBER,
             CodigoBarras VARCHAR2 (13),
             CantidadProducto NUMBER NOT NULL,
             PrecioTotal NUMBER,
             PrecioUnitario NUMBER NOT NULL,
             CONSTRAINT INFO_PROD_SUCURSAL_PK PRIMARY KEY (IdVenta,
                                                           CodigoBarras
                                                           )
   );
 
 -- Restrincciones ------------------------------------------------------ 
   
   ALTER TABLE INFO_PRODUCTO_SUCURSAL
 ADD CONSTRAINT fk_idSucursal_infPro
     FOREIGN KEY (idSucursal)
     REFERENCES sucursal(id)      
 ENABLE;
   
-- Agregar FK codBarras
ALTER TABLE INFO_PRODUCTO_SUCURSAL
 ADD CONSTRAINT fk_CodBarras_infProdSuc
     FOREIGN KEY (CodigoBarras, idSucursal)
     REFERENCES producto_sucursal(CodigoBarras, idSucursal)      
 ENABLE;
 
 -------------------------------------------------------------------------
-- Creaci�n de la tabla Carrito
-------------------------------------------------------------------------
 
 CREATE TABLE CARRITO
 (  ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
    EmailCliente VARCHAR2 (255 BYTE),
    IdSucursal NUMBER,
    PRECIO NUMBER,
    ESTADO VARCHAR2 (255 BYTE ) NOT NULL,
    CONSTRAINT CARRITO_PK PRIMARY KEY ( ID,
                                        EmailCliente,
                                        IdSucursal));

-- Restrincciones ----------------------------------------------------------
-- Agrega FK emailCliente                                        
ALTER TABLE CARRITO
ADD CONSTRAINT FK_EMAIL_CLIENTE_CARRITO
FOREIGN KEY (EMAILCLIENTE)
REFERENCES CLIENTES(EMAIL)
ENABLE;

-- Agregar FK idSucursal
ALTER TABLE CARRITO
ADD CONSTRAINT FK_ID_SUCURSAL_CARRITO
FOREIGN KEY (IDSUCURSAL)
REFERENCES SUCURSAL(ID)
ENABLE;

-- Agregar CK estado 
ALTER TABLE CARRITO
ADD CONSTRAINT CK_ESTADO_Carrito
CHECK (ESTADO IN ('Activo', 'Abandonado'))
ENABLE;

-------------------------------------------------------------------------
-- Creaci�n de la tabla Producto
-------------------------------------------------------------------------

CREATE TABLE INFO_PRODUCTO_CARRITO
(   IDCARRITO NUMBER,
    EmailCliente VARCHAR2(255),
    idSucursal number,
    CODIGOBARRAS VARCHAR2(13),
    CANTIDAD NUMBER,
    PRECIOTOTAL NUMBER,
    PrecioUnitario NUMBER,
    CONSTRAINT INFO_PRODUCTO_CARRITO_PK PRIMARY KEY (   IDCARRITO,
                                                        CODIGOBARRAS));
-- Restrincciones --------------------------------------------------------
-- Agregar FK idCarrito
ALTER TABLE INFO_PRODUCTO_CARRITO
ADD CONSTRAINT FK_ID_CARRITO
FOREIGN KEY (IDCARRITO, EmailCliente, idSucursal)
REFERENCES CARRITO(ID, EmailCliente, idSucursal)
ENABLE;

-- Agregar CodigoBarras
ALTER TABLE INFO_PRODUCTO_CARRITO
ADD CONSTRAINT  FK_CODIGO_BARRAS
FOREIGN KEY (CODIGOBARRAS, idSucursal)
REFERENCES PRODUCTO_SUCURSAL(CODIGOBARRAS, idSucursal)
ENABLE;
 
 COMMIT;
                
   
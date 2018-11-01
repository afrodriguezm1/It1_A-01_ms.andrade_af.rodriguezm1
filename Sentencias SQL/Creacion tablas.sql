-- Creación del secuenciador
 CREATE sequence SuperAndes_sequence;

-- Creación de la tabla Sucursal y especificación de sus restricciones
 CREATE TABLE SUCURSAL
  (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
      Nombre VARCHAR2(255 BYTE) NOT NULL,
      Direccion VARCHAR2(255 BYTE) NOT NULL,
      Ciudad VARCHAR2(255 BYTE) NOT NULL,
      CONSTRAINT SUCURSAL_PK PRIMARY KEY (Id)
   );

-- Creación de la tabla Proveedor y especificación de sus restricciones
 CREATE TABLE PROVEEDOR
  (Nit VARCHAR2(255 BYTE),
       Nombre VARCHAR2(255 BYTE) ,
       Calificacion NUMBER NOT NULL,
       CONSTRAINT PROVEEDOR_PK PRIMARY KEY (Nit, Nombre)
   );
   
 ALTER TABLE PROVEEDOR
 ADD CONSTRAINT CK_Calificacion
     CHECK( Calificacion BETWEEN 0 AND 5)
 ENABLE;
  
-- Creación de la tabla Categoría y especificación de sus restricciones
 CREATE TABLE CATEGORIA
  (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
      Nombre VARCHAR2(255 BYTE),
      CONSTRAINT CATEGORIA_PK PRIMARY KEY (Id, Nombre)
   );
  
 -- Creación de la tabla Tipo Producto y especificación de sus restricciones
 CREATE TABLE TIPO_PRODUCTO
   (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
       Id_categoria NUMBER,
       Nombre VARCHAR2(255 BYTE) NOT NULL,
       CONSTRAINT TIPO_PRODUCTO_PK PRIMARY KEY (Id, Id_categoria)
   );
 
 ALTER TABLE TIPO_PRODUCTO
 ADD CONSTRAINT fk_categoria_tp
     FOREIGN KEY (Id_categoria)
     REFERENCES categoria(Id)    
 ENABLE;
 
 ALTER TABLE TIPO_PRODUCTO
 ADD CONSTRAINT UN_TIPOPROD_NOMBRE
      UNIQUE (Nombre)  
 ENABLE;
 
  -- Creación de la tabla Producto y especificación de sus restricciones
 CREATE TABLE PRODUCTO
   (Codigo_barras VARCHAR2(13),
        Id_categoria NUMBER NOT NULL,
        Id_tipo_producto NUMBER NOT NULL,
        Nombre VARCHAR2(255 BYTE) NOT NULL,
        Marca VARCHAR2(255 BYTE) NOT NULL,
        Presentacion VARCHAR2(255 BYTE) NOT NULL,
        Cantidad_presen NUMBER NOT NULL,
        Uni_medida VARCHAR2(255 BYTE) NOT NULL,
        Volumen NUMBER NOT NULL,
        Peso NUMBER NOT NULL,
        CONSTRAINT PRODUCTO_PK PRIMARY KEY (Codigo_barras)
    );
 ALTER TABLE PRODUCTO
 ADD CONSTRAINT fk_categoria_prod
     FOREIGN KEY (Id_categoria)
     REFERENCES categoria(Id)    
 ENABLE;
 
 ALTER TABLE PRODUCTO
 ADD CONSTRAINT fk_tipo_producto_prod
     FOREIGN KEY (Id_tipo_producto)
     REFERENCES categoria(Id)    
 ENABLE;
 
 ALTER TABLE PRODUCTO
 ADD CONSTRAINT CK_Cod_Barras_prod
     CHECK (LENGTH (Codigo_barras) = 13)
 ENABLE;
 
 ALTER TABLE PRODUCTO
 ADD CONSTRAINT CK_Uni_medida
     CHECK (Uni_medida IN ('gr', 'ml'))
 ENABLE;
 
-- Creación de la tabla Producto Proveedor y especificación de sus restricciones
 CREATE TABLE PRODUCTO_PROVEEDOR
   (Codigo_barras VARCHAR2(13),
        Nit_proveedor VARCHAR2(255 BYTE),
        Es_exclusivo NUMBER NOT NULL,
        Precio_unitario NUMBER NOT NULL,
        Precio_unidad_medida NUMBER NOT NULL,
        CONSTRAINT PRODUCTO_PROVEEDOR_PK PRIMARY KEY (Codigo_barras, Nit_proveedor)
    );

 ALTER TABLE PRODUCTO_PROVEEDOR
 ADD CONSTRAINT fk_proveedor_nit_prodProve
     FOREIGN KEY (Nit_proveedor)
     REFERENCES proveedor(Nit)    
 ENABLE;
 
 ALTER TABLE PRODUCTO_PROVEEDOR
 ADD CONSTRAINT fk_producto_codBarras_prodProve
     FOREIGN KEY (Codigo_barras)
     REFERENCES producto(Codigo_barras)    
 ENABLE;
 
 ALTER TABLE PRODUCTO_PROVEEDOR
 ADD CONSTRAINT CK_Cod_barras_prodProve
     CHECK (LENGTH (Codigo_barras) = 13)
 ENABLE;
 -- Boolean para saber si el producto es exclusivo
 -- 1 = si, 0 = no
 ALTER TABLE PRODUCTO_PROVEEDOR
 ADD CONSTRAINT CK_Es_exclusivo
     CHECK (Es_exclusivo = 1 OR Es_exclusivo = 0)
 ENABLE;
 
-- Creación de la tabla Producto Sucursal y especificación de sus restricciones
 CREATE TABLE PRODUCTO_SUCURSAL
   (Codigo_barras VARCHAR2(13),
        Id_sucursal NUMBER,
        Precio_unitario NUMBER NOT NULL,
        Precio_unidad_medida NUMBER NOT NULL,
        Numero_recompra NUMBER NOT NULL,
        Nivel_reorden NUMBER NOT NULL,
        CONSTRAINT PRODUCTO_SUCURSAL_PK PRIMARY KEY (Codigo_barras, Id_sucursal)
    );

 ALTER TABLE PRODUCTO_SUCURSAL
 ADD CONSTRAINT fk_producto_codBarras_prodSuc
     FOREIGN KEY (Codigo_barras)
     REFERENCES producto(Codigo_barras)    
 ENABLE;
 
 ALTER TABLE PRODUCTO_SUCURSAL
 ADD CONSTRAINT fk_sucursal_id_prodSuc
     FOREIGN KEY (Id_sucursal)
     REFERENCES sucursal(Id)    
 ENABLE;
 
 ALTER TABLE PRODUCTO_SUCURSAL
 ADD CONSTRAINT CK_Cod_barras_prodSuc
     CHECK (LENGTH (Codigo_barras) = 13)
 ENABLE;
 
-- Creación de la tabla Producto Redimible y especificación de sus restricciones    
 CREATE TABLE PRODUCTO_REDIMIBLE
   (Codigo_barras VARCHAR2(13),
        Id_sucursal NUMBER,
        Puntos NUMBER NOT NULL,
        CONSTRAINT PRODUCTO_REDIMIBLE_PK PRIMARY KEY (Codigo_barras, Id_sucursal)
    );

 ALTER TABLE PRODUCTO_REDIMIBLE
 ADD CONSTRAINT fk_producto_codBarras_prodRed
     FOREIGN KEY (Codigo_barras)
     REFERENCES producto(Codigo_barras)    
 ENABLE;
 
 ALTER TABLE PRODUCTO_REDIMIBLE
 ADD CONSTRAINT fk_sucursal_id_prodRed
     FOREIGN KEY (Id_sucursal)
     REFERENCES sucursal(Id)    
 ENABLE;
 
 ALTER TABLE PRODUCTO_REDIMIBLE
 ADD CONSTRAINT CK_Cod_barras_prodRed
     CHECK (LENGTH (Codigo_barras) = 13)
 ENABLE;
 
-- Creación de la tabla Promoción y especificación de sus restricciones
 CREATE TABLE PROMOCION
   (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
       Id_sucursal NUMBER,
       Codigo_barras VARCHAR2(13) NOT NULL,
       Nombre VARCHAR2(255 BYTE) NOT NULL,
       Fecha_inicio DATE NOT NULL,
       Fecha_fin DATE NOT NULL,
       Tipo_promocion NUMBER NOT NULL,
       Valor1 NUMBER NOT NULL,
       Valor2 NUMBER NOT NULL,
       CONSTRAINT PROMOCION_PK PRIMARY KEY (Id, Id_sucursal)
    );
    
 ALTER TABLE PROMOCION
 ADD CONSTRAINT fk_producto_codBarras_prom
     FOREIGN KEY (Codigo_barras)
     REFERENCES producto(Codigo_barras)    
 ENABLE;
 
 ALTER TABLE PROMOCION
 ADD CONSTRAINT fk_sucursal_id_prom
     FOREIGN KEY (Id_sucursal)
     REFERENCES sucursal(Id)    
 ENABLE;
 
ALTER TABLE PROMOCION
 ADD CONSTRAINT CK_Cod_barras_prom
     CHECK (LENGTH (Codigo_barras) = 13)
 ENABLE;
 
-- Creación de la tabla Orden Pedido y especificación de sus restricciones
 CREATE TABLE ORDEN_PEDIDO
   (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
       Id_sucursal NUMBER,
       Nit_proveedor VARCHAR2(255 BYTE),
       Fecha_exp DATE NOT NULL,
       Fecha_est DATE NOT NULL,
       Fecha_entrega DATE NOT NULL,
       Estado VARCHAR2 (255 BYTE) NOT NULL,
       CONSTRAINT ORDEN_PEDIDO_PK PRIMARY KEY(Id, Id_sucursal, Nit_proveedor)
    );
 
 ALTER TABLE ORDEN_PEDIDO
 ADD CONSTRAINT fk_sucursal_id_ordPed
     FOREIGN KEY (Id_sucursal)
     REFERENCES sucursal(Id)    
 ENABLE;
 
 ALTER TABLE ORDEN_PEDIDO
 ADD CONSTRAINT fk_proveedor_nit_ordPed
     FOREIGN KEY (Nit_proveedor)
     REFERENCES proveedor(Nit)    
 ENABLE;
 
 ALTER TABLE ORDEN_PEDIDO
 ADD CONSTRAINT CK_Estado
     CHECK (Estado IN ('Espera', 'Entregado'))
 ENABLE;

-- Creación de la tabla Info Producto Proveedor y especificación de sus restricciones
 CREATE TABLE INFO_PRODUCTO_PROVEEDOR
   (Id_orden NUMBER,
       Codigo_barras NUMBER,
       Cantidad_producto NUMBER NOT NULL,
       CONSTRAINT INFO_PROD_PROVEEDOR_PK PRIMARY KEY (Id_orden, Codigo_barras)
    );
 ALTER TABLE INFO_PRODUCTO_PROVEEDOR
 ADD CONSTRAINT fk_producto_codBarras_infProdProv
     FOREIGN KEY (Codigo_barras)
     REFERENCES producto(Codigo_barras)    
 ENABLE;
 
 ALTER TABLE INFO_PRODUCTO_PROVEEDOR
 ADD CONSTRAINT fk_orden_pedido_Id_infProdProv
     FOREIGN KEY (Id_orden)
     REFERENCES orden_pedido(Id)
 ENABLE;
 
 ALTER TABLE INFO_PRODUCTO_PROVEEDOR
 ADD CONSTRAINT CK_Cod_barras_infProdProv
     CHECK (LENGTH (Codigo_barras) = 13)
 ENABLE;
 
-- Creación de la tabla Almacenamiento y especificación de sus restricciones
CREATE TABLE ALMACENAMIENTO
   (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
       Id_sucursal NUMBER,
       codigo_barras_producto varchar(13),
       Id_categoria_prod NUMBER,
       Id_tipo_prod NUMBER,
       Capa_vol NUMBER NOT NULL,
       Capa_peso NUMBER NOT NULL,
       Cantidad NUMBER NOT NULL,
       Tipo_alma NUMBER NOT NULL,
       Nivel_reavast NUMBER NOT NULL,
       CONSTRAINT ALMACENAMIENTO_PK PRIMARY KEY (Id,
                                                 Id_sucursal,
                                                 Id_categoria_prod,
                                                 Id_tipo_prod
                                                 )
    );
    
 ALTER TABLE ALMACENAMIENTO
 ADD CONSTRAINT fk_sucursal_id_alm
     FOREIGN KEY (Id_sucursal)
     REFERENCES sucursal(Id)    
 ENABLE;
       
 ALTER TABLE ALMACENAMIENTO
 ADD CONSTRAINT fk_categoria_id_alm
     FOREIGN KEY (Id_categoria_prod)
     REFERENCES categoria(Id)    
 ENABLE;
 
 ALTER TABLE ALMACENAMIENTO
 ADD CONSTRAINT fk_tipoProd_id_alm
     FOREIGN KEY (Id_tipo_prod)
     REFERENCES tipo_producto(Id)    
 ENABLE;
 ALTER TABLE ALMACENAMIENTO
ADD CONSTRAINT fk_almacenamiento_codigo_barras_alm
	FOREIGN KEY(codigo_barras_producto)
	REFERENCES producto(codigo_barras) 
ENABLE;
 
 -- 1 = Bodega, 2 = Estante
 ALTER TABLE ALMACENAMIENTO
 ADD CONSTRAINT CK_Tipo_alma
     CHECK ( Tipo_alma = 1 OR Tipo_alma = 2)    
 ENABLE;
 
 -- Creación de la tabla Clientes y especificación de sus restricciones
 CREATE TABLE CLIENTES
   (Email VARCHAR2 (255 BYTE),
          Nombre VARCHAR2 (255 BYTE) NOT NULL,
          CONSTRAINT CLIENTES_PK PRIMARY KEY (Email)
    );

 -- Creación de la tabla Personas y especificación de sus restricciones
 CREATE TABLE PERSONAS
   (Email VARCHAR2 (255 BYTE),
          Documento NUMBER,
          Puntaje NUMBER NOT NULL,
          CONSTRAINT PERSONAS_PK PRIMARY KEY (Email, Documento)
    );
 
 ALTER TABLE PERSONAS
 ADD CONSTRAINT fk_clientes_email_pers
     FOREIGN KEY (Email)
     REFERENCES clientes(Email)      
 ENABLE;    
          
-- Creación de la tabla Empresas y especificación de sus restricciones
 CREATE TABLE EMPRESAS
   (Email VARCHAR2 (255 BYTE),
          Nit VARCHAR2 (255 BYTE),
          Direccion VARCHAR2 (255 BYTE) NOT NULL,
          CONSTRAINT EMPRESAS_PK PRIMARY KEY (Email, Nit)
    );
 
 ALTER TABLE EMPRESAS
 ADD CONSTRAINT fk_clientes_email_emp
     FOREIGN KEY (Email)
     REFERENCES clientes(Email)      
 ENABLE;   
 
 -- Creación de la tabla Ventas y especificación de sus restricciones
 CREATE TABLE VENTAS
   (Id NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE NOT NULL ENABLE,
       Id_sucursal NUMBER,
       Email_cliente VARCHAR2 (255 BYTE),
       Consecutivo_FE NUMBER,
       Cufe VARCHAR2 (255 BYTE),
       Fecha_venta DATE NOT NULL,
       CONSTRAINT VENTAS_PK PRIMARY KEY (Id, Id_sucursal, Email_cliente)
    );

 ALTER TABLE VENTAS
 ADD CONSTRAINT fk_clientes_email_vent
     FOREIGN KEY (Email_clientes)
     REFERENCES clientes(Email)      
 ENABLE;   
 
 ALTER TABLE VENTAS
 ADD CONSTRAINT fk_sucursal_id_vent
     FOREIGN KEY (Id_sucursal)
     REFERENCES sucursal(Id)      
 ENABLE;
 
 -- Creación de la tabla Info Producto Sucursal y especificación de sus restricciones
 CREATE TABLE INFO_PRODUCTO_SUCURSAL
   (Id_venta NUMBER,
             Codigo_barras VARCHAR2 (13),
             Cantidad_producto NUMBER NOT NULL,
             CONSTRAINT INFO_PROD_SUCURSAL_PK PRIMARY KEY (Id_venta,
                                                           Codigo_barras
                                                           )
   );
 
 ALTER TABLE INFO_PRODUCTO_SUCURSAL
 ADD CONSTRAINT fk_ventas_id_infProdSuc
     FOREIGN KEY (Id_venta)
     REFERENCES ventas(Id)      
 ENABLE;     
   
ALTER TABLE INFO_PRODUCTO_SUCURSAL
 ADD CONSTRAINT fk_prod_sucursal_CodBarras_infProdSuc
     FOREIGN KEY (Codigo_barras)
     REFERENCES producto_sucursal(Codigo_barras)      
 ENABLE;
 
 -- Creación de la tabla Resoluciones y especificación de sus restricciones
 CREATE TABLE RESOLUCIONES
   (Numero_resolucion NUMBER,
                Fecha_habilitacion DATE NOT NULL,
                Fecha_vencimiento DATE NOT NULL,
                Inicio_conse NUMBER NOT NULL,
                Fin_conse NUMBER NOT NULL,
                Numero_act NUMBER NOT NULL,
                CONSTRAINT RESOLUCIONES_PK PRIMARY KEY (Numero_resolucion)
    );

 -- Creación de la tabla Resoluciones y especificación de sus restricciones
 CREATE TABLE INFORMACION
   (Nit VARCHAR2 (255 BYTE) NOT NULL,
        Nombre VARCHAR2 (255 BYTE) NOT NULL,
        FacturaE NUMBER NOT NULL
    );
 ALTER TABLE INFORMACION
 ADD CONSTRAINT CK_Unica_fila
     CHECK ( ROWNUM = 1)    
 ENABLE;
 
 ALTER TABLE INFORMACION
 ADD CONSTRAINT CK_FacturaE_boolean
     CHECK ( FacturaE = 0 OR FacturaE = 1)    
 ENABLE;
 
 COMMIT;
                
   
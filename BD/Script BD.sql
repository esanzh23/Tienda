use master;

create database TIENDADAYERSTORE;
GO
use TIENDADAYERSTORE;
GO

-- CREACIÓN DE LOGIN ADMINISTRADOR /sa/123 --
create login DAYERSTORE
with password = '12345',
default_database = TIENDADAYERSTORE;
GO

-- CREACIÓN DE USUARIO ADMINISTRADOR --
create user DAYERSTORE
for login DAYERSTORE
with default_schema = esq;
GO


-- CREACIÓN DE ESQUEMA PARA ADMINISTRACIÓN
create schema esq authorization DAYERSTORE;
GO

-- CREACIÓN DE PRIVILEGIOS PARA ADMINISTRADOR --
grant create proc, execute, create table, create role, select, insert, update, delete to DAYERSTORE;
GO


USE TIENDADAYERSTORE
;




CREATE TABLE UsuarioOpcion ( 
	IdUsuarioOpcion int NOT NULL,
	IdUsuario int,
	IdOpcion int
)
;

CREATE TABLE Opcion ( 
	IdOpcion int NOT NULL,
	Codigo varchar(20),
	Descripcion varchar(100)
)
;

CREATE TABLE Usuario ( 
	IdUsuario int NOT NULL,
	IdCargo int,
	Usuario varchar(20),
	Nombres varchar(80),
	Contraseña varchar(50),
	Activo bigint
)
;

CREATE TABLE Cargo ( 
	IdCargo int NOT NULL,
	Codigo varchar(20),
	Descripcion varchar(100)
)
;

CREATE TABLE TipoPago ( 
	IdTipoPago int NOT NULL,
	Codigo varchar(20),
	Descripcion varchar(100)
)
;

CREATE TABLE Cliente ( 
	IdCliente int NOT NULL,
	Codigo varchar(20),
	Nombres varchar(100),
	NroDocumento varchar(20),
	Email varchar(50),
	Telefono varchar(20),
	Direccion varchar(100)
)
;

CREATE TABLE PedidoVenta ( 
	IdPedidoVenta int NOT NULL,
	IdCliente int,
	IdTipoPago int,
	IdTipoComprobante int,
	Fecha date,
	MontoTotal decimal(10,2),
	descuento decimal(10,2)
)
;

CREATE TABLE Detalle ( 
	IdDetalle int NOT NULL,
	IdPedidoVenta int,
	IdProducto int,
	Cantidad int,
	Precio decimal(10,2),
	SubTotal decimal(10,2)
)
;

CREATE TABLE Producto ( 
	IdProducto int NOT NULL,
	IdTipoProducto int,
	Descripcion varchar(100),
	Stock decimal(10,2),
	Precio_Uniario decimal(10,2),
	Talla varchar(50),
	Marca varchar(20),
	Presentacion varchar(200),
	Imagen binary(10)
)
;

CREATE TABLE TipoProducto ( 
	IdTipoProducto int NOT NULL,
	Codigo varchar(20),
	Descripcion varchar(200)
)
;


ALTER TABLE UsuarioOpcion ADD CONSTRAINT PK_UsuarioOpcion 
	PRIMARY KEY CLUSTERED (IdUsuarioOpcion)
;

ALTER TABLE Opcion ADD CONSTRAINT PK_Opcion 
	PRIMARY KEY CLUSTERED (IdOpcion)
;

ALTER TABLE Usuario ADD CONSTRAINT PK_Usuario 
	PRIMARY KEY CLUSTERED (IdUsuario)
;

ALTER TABLE Cargo ADD CONSTRAINT PK_Cargo 
	PRIMARY KEY CLUSTERED (IdCargo)
;

ALTER TABLE TipoPago ADD CONSTRAINT PK_TipoPago 
	PRIMARY KEY CLUSTERED (IdTipoPago)
;

ALTER TABLE Cliente ADD CONSTRAINT PK_Cliente 
	PRIMARY KEY CLUSTERED (IdCliente)
;

ALTER TABLE PedidoVenta ADD CONSTRAINT PK_PedidoVenta 
	PRIMARY KEY CLUSTERED (IdPedidoVenta)
;

ALTER TABLE Detalle ADD CONSTRAINT PK_Detalle 
	PRIMARY KEY CLUSTERED (IdDetalle)
;

ALTER TABLE Producto ADD CONSTRAINT PK_Producto 
	PRIMARY KEY CLUSTERED (IdProducto)
;

ALTER TABLE TipoProducto ADD CONSTRAINT PK_TipoProducto 
	PRIMARY KEY CLUSTERED (IdTipoProducto)
;



ALTER TABLE UsuarioOpcion ADD CONSTRAINT FK_UsuarioOpcion_Opcion 
	FOREIGN KEY (IdOpcion) REFERENCES Opcion (IdOpcion)
	ON UPDATE CASCADE
;

ALTER TABLE UsuarioOpcion ADD CONSTRAINT FK_UsuarioOpcion_Usuario 
	FOREIGN KEY (IdUsuario) REFERENCES Usuario (IdUsuario)
	ON UPDATE CASCADE
;

ALTER TABLE Usuario ADD CONSTRAINT FK_Usuario_Cargo 
	FOREIGN KEY (IdCargo) REFERENCES Cargo (IdCargo)
	ON UPDATE CASCADE
;

ALTER TABLE PedidoVenta ADD CONSTRAINT FK_PedidoVenta_Cliente 
	FOREIGN KEY (IdCliente) REFERENCES Cliente (IdCliente)
	ON UPDATE CASCADE
;

ALTER TABLE PedidoVenta ADD CONSTRAINT FK_PedidoVenta_TipoPago 
	FOREIGN KEY (IdTipoPago) REFERENCES TipoPago (IdTipoPago)
	ON UPDATE CASCADE
;

ALTER TABLE Detalle ADD CONSTRAINT FK_Detalle_PedidoVenta 
	FOREIGN KEY (IdPedidoVenta) REFERENCES PedidoVenta (IdPedidoVenta)
	ON UPDATE CASCADE
;

ALTER TABLE Detalle ADD CONSTRAINT FK_Detalle_Producto 
	FOREIGN KEY (IdProducto) REFERENCES Producto (IdProducto)
	ON UPDATE CASCADE
;

ALTER TABLE Producto ADD CONSTRAINT FK_Producto_TipoProducto 
	FOREIGN KEY (IdTipoProducto) REFERENCES TipoProducto (IdTipoProducto)
	ON UPDATE CASCADE
;



--delete from cargo
select * from cargo

insert into cargo values(1,'ADM','Administrador')
insert into cargo values(2,'VEN','VENDEDOR')


--delete from usuario
select * from usuario
insert into usuario values(1,1,'ADMIN','Juan Perez Perez','123456',1)
insert into usuario values(2,2,'VENDEDOR1','Julisa Poma Cabrera','123456',1)
insert into usuario values(3,2,'VENDEDOR2','Johana de la cruz Sandoval','123456',1)
insert into usuario values(4,2,'VENDEDOR3','Magali Silva Contreras','123456',1)

--delete from TipoProducto
select * from TipoProducto
insert into TipoProducto values(1,'1001','Accesorio')
insert into TipoProducto values(2,'1002','Utencilios de Cocina')
insert into TipoProducto values(3,'1003','Lonchera')
insert into TipoProducto values(4,'1004','Bar')
insert into TipoProducto values(5,'1005','Prenda de Vestir')
insert into TipoProducto values(6,'1006','Higiene Personal')
insert into TipoProducto values(7,'1007','Utiles escolares')



select * from Producto
insert into Producto values(1,2,'Tazas',50,3.5,'','','',null)
insert into Producto values(2,2,'Jarras Manson',50,50.0,'','Manson','',null)
insert into Producto values(3,3,'Tomatodos',100,14.00,'','','',null)
insert into Producto values(4,4,'Vaso Chop',50,14.5,'','','',null)
insert into Producto values(5,1,'Taza para Auto',80,20.5,'','','',null)
insert into Producto values(6,5,'Polos',200,35.00,'','','',null)
insert into Producto values(7,5,'Gorros',280,15.00,'','','',null)
insert into Producto values(8,2,'Platos',200,5.00,'','','',null)
insert into Producto values(9,6,'Toallas',50,20.00,'','','',null)
insert into Producto values(10,1,'Bolsas de tela',300,8.00,'','','',null)
insert into Producto values(11,7,'Cartucheras',380,15.00,'','','',null)
insert into Producto values(12,6,'individuales ',400,18.00,'','','',null)
insert into Producto values(13,6,'servilletas',500,7.00,'','','',null)
insert into Producto values(14,1,'llaveros',600,9.00,'','','',null)
insert into Producto values(15,7,'agendas',60,20.00,'','','',null)
insert into Producto values(16,1,'langer',100,14.5,'','','',null)
insert into Producto values(17,1,'carcasa de celular',100,30.5,'','','',null)
insert into Producto values(18,1,'pulseras',100,45.5,'','','',null)
insert into Producto values(19,7,'mochilas',400,120.5,'','','',null)
insert into Producto values(20,1,'monedero',600,23.5,'','','',null)



select * from TipoPago

insert into TipoPago values(1,'0001','Efectivo')

select * from cliente

insert into cliente values(1,'CLI001','BABILON ROMANI MOISES GLICERIO','45437865','glice@gmail.com','','')
insert into cliente values(2,'CLI002','BARDALES ESPINOZA ANA LUCIA','76543965','lucia@gmail.com','','')
insert into cliente values(3,'CLI003','BELLIDO VALVERDE SILVANA ESTEFANIA','45908423','este@gmail.com','','')
insert into cliente values(4,'CLI004','SANCHEZ DE FREY EVELINA ALICIA','87238745','alic@gmail.com','','')
insert into cliente values(5,'CLI005','SANDOVAL MAYORGA EVA ANDREA','67877878','ceci@gmail.com','','')
insert into cliente values(6,'CLI006','BENITES CHAVEZ JAVIER MARTIN','23457689','martin@gmail.com','','')
insert into cliente values(7,'CLI007','CASTELLARES RAMIREZ LUZ CATHERINE ','56870900','cathe@gmail.com','','')
insert into cliente values(8,'CLI008','CASTILLO LARA JOSE EDUARDO','23546731','eduardo@gmail.com','','')
insert into cliente values(9,'CLI009','CASTRO TOMURA LUCILA MAGALI','45091298','magali@gmail.com','','')
insert into cliente values(10,'CLI010','CASTILLO LARA JUAN EMILIO','45091256','emilio@gmail.com','','')
insert into cliente values(11,'CLI011','TEJADA BARRERA CRISTINA DEL ROCIO','45092365','rocio@gmail.com','','')
insert into cliente values(12,'CLI012','TRISTAN ROBLES ADRIAN JESUS','45090900','jesus@gmail.com','','')
insert into cliente values(13,'CLI013','ABARCA OTAYZA DIANA ELIZABETH','45761243','eliza@gmail.com','','')
insert into cliente values(14,'CLI014','BALBUENA VEGA DE MILAGRO DEL CARMEN','45769809','mila@gmail.com','','')
insert into cliente values(15,'CLI015','ACOSTA ABARCA CARLA VERONICA','45099809','vero@gmail.com','','')
insert into cliente values(16,'CLI016','ALDANA SILVA MARTHA ISABEL','45090098','isabel@gmail.com','','')
insert into cliente values(17,'CLI017','ARIANA FERNANDEZ DAVILA OSCAR RAUL','45097877','oscar@gmail.com','','')
insert into cliente values(18,'CLI018','RIVERA RIVERO ANDRES','45232354','andres@gmail.com','','')
insert into cliente values(19,'CLI019','TORRES VALENCIANO JOSE LUIS','45768988','luis@gmail.com','','')
insert into cliente values(20,'CLI020','MARTINEZ ALMEIDA GABRIELA HARUMI','34546577','maru@gmail.com','','')

--delete from Opcion
select * from Opcion
insert into opcion values(1,'001','Mantenimiento de Usuarios')
insert into opcion values(2,'002','Mantenimiento de Productos')
insert into opcion values(3,'003','Gestion de Ventas')

--delete from UsuarioOpcion
select * from UsuarioOpcion

insert into UsuarioOpcion values(1,1,1)
insert into UsuarioOpcion values(2,1,2)
insert into UsuarioOpcion values(3,1,3)

insert into UsuarioOpcion values(4,2,3)
insert into UsuarioOpcion values(5,3,3)
insert into UsuarioOpcion values(6,4,3)


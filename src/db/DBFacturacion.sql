create database dbfacturacion;
use dbfacturacion;

create table cliente(
	cliente_id integer auto_increment,
    codigo varchar(20) not null,
    nit varchar(20) not null,
    nombre varchar(30) not null,
    direccion varchar(100),
    fecha_ingreso timestamp,
    PRIMARY KEY(cliente_id)
);

create table producto(
	producto_id integer auto_increment primary key,
    codigo varchar(20) not null,
    codigo_barras varchar(50) not null,
    precio numeric(16,2)
);
create table venta(
	venta_id integer auto_increment primary key,
    cliente_id integer,
    FOREIGN key(cliente_id)references cliente(cliente_id),
    numero integer not null,
	fecha_factura timestamp	
);

create table venta_detalle(
	venta_detalle_id integer auto_increment primary key,
    venta_id integer,
    producto_id integer,
    FOREIGN key (venta_id) references venta(venta_id),
    FOREIGN key(producto_id)references producto(producto_id),
    cantidad numeric(16,2),
    precio numeric(16,2)
);

use dbfacturacion;
select * from cliente;
select * from producto;
select * from venta;
select * from venta_detalle;



create table cliente_producto(
	cliente_id integer primary key,
	producto_id integer,
	FOREIGN KEY(cliente_id) references cliente(cliente_id),
	FOREIGN KEY(producto_id) references producto(producto_id)
);
use DBFacturacion;
SELECT * FROM venta_detalle;

SELECT * FROM venta_detalle INNER JOIN producto ON producto.producto_id = venta_detalle.producto_id WHERE producto.precio < 200;
SELECT venta.numero, venta.fecha_factura, cliente.nombre FROM cliente INNER JOIN venta ON venta.cliente_id = cliente.cliente_id WHERE fecha_factura BETWEEN ? AND ?;





SELECT * FROM producto WHERE EXISTS(
SELECT 1 FROM venta_detalle WHERE venta_detalle.producto_id = producto.producto_id
);
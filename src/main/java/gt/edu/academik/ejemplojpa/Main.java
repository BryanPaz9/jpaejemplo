package gt.edu.academik.ejemplojpa;

import gt.edu.academik.ejemplojpa.dominio.Cliente;
import gt.edu.academik.ejemplojpa.dominio.Producto;
import gt.edu.academik.ejemplojpa.dominio.Venta;
import gt.edu.academik.ejemplojpa.dominio.VentaDetalle;
import gt.edu.academik.ejemplojpa.servicio.ClienteServicio;
import gt.edu.academik.ejemplojpa.servicio.ProductoServicio;
import gt.edu.academik.ejemplojpa.servicio.VentaDetalleServicio;
import gt.edu.academik.ejemplojpa.servicio.VentaServicio;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mario Batres
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EjemploJPA_DS");
        EntityManager em = emf.createEntityManager();

        
        /**PRODUCTO*/
//        ProductoServicio productoServicio = new ProductoServicio(em);
////        List<Producto> productList = productoServicio.findAllWithJPQL();
//        productList.stream().forEach(System.out::println);


        /**CLIENTE*/
//        ClienteServicio  clienteServicio = new ClienteServicio(em);
////        List<Cliente> clientList = clienteServicio.getRecordsWithJPQL();
//        List<Cliente> clientList = clienteServicio.getRecordsWithCriteria();
//        clientList.stream().forEach(System.out::println);
        
        /**VENTA*/
//        VentaServicio ventaServicio = new VentaServicio(em);
////        List<Venta> ventaList = ventaServicio.getRecordsWithJPQL();
//        List<Venta> ventaList = ventaServicio.getRecordsWithCriteria();
//        ventaList.stream().forEach(System.out::println);
        
        /**VENTA DETALLE*/
//        VentaDetalleServicio ventaDetalleServicio = new VentaDetalleServicio(em);
//        List<VentaDetalle> ventaDetalleList = ventaDetalleServicio.getRecordsWithJPQL();
//        List<VentaDetalle> ventaDetalleList = ventaDetalleServicio.getRecordsWithCriteria();
//        ventaDetalleList.stream().forEach(System.out::println);

         ProductoServicio productoServicio = new ProductoServicio(em);
         
//         Producto producto = productoServicio.getProductWithReturn(1);
//            Producto producto = productoServicio.getProductWithCriteria("374");
         List<Producto> productos = productoServicio.getProductsExist();
            
         productos.stream().forEach(System.out::println);
//
//            VentaDetalleServicio ventaDetalleServicio = new VentaDetalleServicio(em);
//            List<VentaDetalle> ventaDetalleList = ventaDetalleServicio.getRecordsByProductPriceFilter(BigDecimal.valueOf(200));
//            ventaDetalleList.stream().forEach(System.out::println);
         
            
            
        em.close();

        emf.close();

    }
}

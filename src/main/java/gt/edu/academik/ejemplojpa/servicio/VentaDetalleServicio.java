/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.academik.ejemplojpa.servicio;

import gt.edu.academik.ejemplojpa.dominio.Producto;
import gt.edu.academik.ejemplojpa.dominio.Producto_;
import gt.edu.academik.ejemplojpa.dominio.VentaDetalle;
import gt.edu.academik.ejemplojpa.dominio.VentaDetalle_;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author 001la
 */
public class VentaDetalleServicio {
    
    public final EntityManager em;
    
    public VentaDetalleServicio(EntityManager em){
        this.em = em;
    }
    
    public List<VentaDetalle> getRecordsWithJPQL(){
       String query = "SELECT vd FROM VentaDetalle AS vd";
       List<VentaDetalle> ventaDetalleList = this.em.createQuery(query).getResultList();
       return ventaDetalleList;
    }
    
    public List<VentaDetalle> getRecordsWithCriteria(){
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<VentaDetalle> ventaQuery = builder.createQuery(VentaDetalle.class);
        Root<VentaDetalle> ventaRoot = ventaQuery.from(VentaDetalle.class);
        ventaQuery.select(ventaRoot);
        List<VentaDetalle> ventaDetalleList = this.em.createQuery(ventaQuery).getResultList();
        return ventaDetalleList;
    }
    


    //SELECT * FROM venta_detalle INNER JOIN producto ON 
    //producto.producto_id = venta_detalle.producto_id WHERE producto.precio < 200;
    
    public List<VentaDetalle> getRecordsByProductPriceFilter(BigDecimal precio){
    CriteriaBuilder builder = this.em.getCriteriaBuilder();
    CriteriaQuery<VentaDetalle> ventaDetalleQuery = builder.createQuery(VentaDetalle.class);
    Root<VentaDetalle> ventaDetalleRoot = ventaDetalleQuery.from(VentaDetalle.class);
    Join<VentaDetalle,Producto> productoJoin = ventaDetalleRoot.join(VentaDetalle_.producto);
    ventaDetalleQuery.where(builder.lt(productoJoin.get(Producto_.precio),precio));
    
    List<VentaDetalle> ventaDetalleList = this.em.createQuery(ventaDetalleQuery).getResultList();
    return ventaDetalleList;
    
    
    }
}

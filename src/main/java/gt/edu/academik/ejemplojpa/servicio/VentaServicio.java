/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.academik.ejemplojpa.servicio;

import gt.edu.academik.ejemplojpa.dominio.Venta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author 001la
 * Crear una constante de tipo EntityManager
 * Crear Constructor con parámetro la constante creada
 * Crear la lista para JPQL
 * Crear la lista para Criteria
 */
public class VentaServicio {
    public final EntityManager em;
    
    public VentaServicio(EntityManager em){
        this.em = em;
    }
    
    public List<Venta> getRecordsWithJPQL(){
        String query = "SELECT v FROM Venta AS v";
        List<Venta> ventaList = this.em.createQuery(query).getResultList();
        return ventaList;
    }
    
    public List<Venta> getRecordsWithCriteria(){
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Venta> ventaQuery = builder.createQuery(Venta.class);
        Root<Venta> ventaRoot = ventaQuery.from(Venta.class);
        ventaQuery.select(ventaRoot);
        List<Venta> ventaList = this.em.createQuery(ventaQuery).getResultList();
        return ventaList;
    }
    
}

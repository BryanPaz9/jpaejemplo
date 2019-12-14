package gt.edu.academik.ejemplojpa.servicio;

import gt.edu.academik.ejemplojpa.dominio.Producto;
import gt.edu.academik.ejemplojpa.dominio.Producto_;
import gt.edu.academik.ejemplojpa.dominio.VentaDetalle;
import gt.edu.academik.ejemplojpa.dominio.VentaDetalle_;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 *
 * @author Mario Batres
 */
public class ProductoServicio {

    private final EntityManager em;

    public ProductoServicio(EntityManager em) {
        this.em = em;
    }

    public List<Producto> findAllWithJPQL() {
        List<Producto> productoList = this.em.createQuery("SELECT p FROM Producto AS p").getResultList();

        return productoList;
    }

    public List<Producto> findAllWithCriteria() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();

        CriteriaQuery<Producto> productoQuery = builder.createQuery(Producto.class);

        Root<Producto> productoRoot = productoQuery.from(Producto.class);

        productoQuery.select(productoRoot);

        List<Producto> productoList = this.em.createQuery(productoQuery).getResultList();

        return productoList;
    }
    
    public Producto getProductWithReturn(Integer productId){
        return this.em.find(Producto.class, productId); //Consulta para llave primaria.
    }
    
    public Producto getProductWithCriteria(String codigo){
        try{
            CriteriaBuilder builder = this.em.getCriteriaBuilder();
            CriteriaQuery<Producto> productoQuery = builder.createQuery(Producto.class);
            Root<Producto> productoRoot = productoQuery.from(Producto.class);
            productoQuery.select(productoRoot).where(builder.equal(productoRoot.get("codigo"), codigo)); //primer parámetro nombre del campo en la clase, segundo parámetro el nombre del parámetro del método
            Producto product = this.em.createQuery(productoQuery).getSingleResult();
            return product;
        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    //productos precio < 1000
    
    public List<Producto> getProductsMinor(BigDecimal precio){
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Producto> productoQuery = builder.createQuery(Producto.class);
        Root<Producto> productoRoot = productoQuery.from(Producto.class);
        productoQuery.select(productoRoot).where(builder.lt(productoRoot.get(Producto_.precio), precio));
        List<Producto> product = this.em.createQuery(productoQuery).getResultList();
        return product;
    }
    
    public List <Producto> getProductsExist(){
    CriteriaBuilder builder = this.em.getCriteriaBuilder();
    CriteriaQuery<Producto> productoQuery = builder.createQuery(Producto.class);
    Root<Producto> productoRoot = productoQuery.from(Producto.class);
    
    Subquery<Integer> sub = builder.createQuery().subquery(Integer.class);
    sub.select(builder.literal(1));
    
    Root<VentaDetalle> subRoot = sub.from(VentaDetalle.class);
    sub.where(builder.equal(productoRoot.get(Producto_.productoId), subRoot.get(VentaDetalle_.producto)));
    productoQuery.where(builder.exists(sub));
    List<Producto> product = this.em.createQuery(productoQuery).getResultList();
    return product;
    }
    
}

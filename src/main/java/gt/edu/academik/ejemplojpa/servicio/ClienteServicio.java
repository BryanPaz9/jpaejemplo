/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.academik.ejemplojpa.servicio;

import gt.edu.academik.ejemplojpa.dominio.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author 001la
 */
public class ClienteServicio {
    private final EntityManager em;
    
    public ClienteServicio(EntityManager em){
        this.em = em;
    }
    
    public List<Cliente> getRecordsWithJPQL(){
        List<Cliente> listaCliente = this.em.createQuery("SELECT c FROM Cliente AS c").getResultList();
        return listaCliente;
    }
    
    public List<Cliente> getRecordsWithCriteria(){
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Cliente> clienteQuery = builder.createQuery(Cliente.class);
        Root <Cliente> clienteRoot = clienteQuery.from(Cliente.class);
        clienteQuery.select(clienteRoot);
        List <Cliente> clienteList = this.em.createQuery(clienteQuery).getResultList();
        return clienteList;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entity.Venta;
import interfaces.Iventa;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author An
 */
public class ventadao implements Iventa {

    @Override
    public boolean insert(Session session, Venta venta) throws Exception {
      session.save(venta);
      return true;
    }

    @Override
    public Venta getUltimoRegistro(Session session) throws Exception {
     String hql="from Venta order by idventa desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (Venta) query.uniqueResult();  
    }
    
}

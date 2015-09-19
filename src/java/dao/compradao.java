/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Compra;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public class compradao implements interfaces.Icompra{

    @Override
    public boolean insert(Session session, Compra compra) throws Exception {
      session.save(compra);
        
        return true;
    }

    @Override
    public Compra getUltimoRegistro(Session session) throws Exception {
     String hql="from compra order by idcompra desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (Compra) query.uniqueResult();   
    }
    
}

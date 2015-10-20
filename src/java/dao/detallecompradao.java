/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Detallecompra;
import hibernateutil.HibernateUtil;
import interfaces.Idetallecompra;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public class detallecompradao implements Idetallecompra {

    @Override
    public  boolean insert(Session session, Detallecompra detallecompra) throws Exception {
        session.save(detallecompra);

        return true;
    }
  public static List<Detallecompra> detallecompr(Session session){
       session=null;
        Query query=null;
        try {
          
            session=HibernateUtil.getSessionFactory().openSession();
            String hql="from Detallecompra as dc ORDER BY dc.compra.idcompra DESC ";
            query=session.createQuery(hql);
            
            return query.list();
        } catch (Exception e) {
        }finally{
        session.close();
        }
    return null;
    }
}

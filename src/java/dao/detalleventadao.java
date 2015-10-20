/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Detalleventa;
import hibernateutil.HibernateUtil;
import interfaces.Idetalleventa;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author An
 */
public class detalleventadao implements Idetalleventa {

    @Override
    public boolean insert(Session session, Detalleventa detalleventa) throws Exception {
        session.save(detalleventa);
        return true;
    }

    public static List<Detalleventa> detaventa(Session session){
     session=null;
     Query query=null;
        try {
            session=HibernateUtil.getSessionFactory().openSession();
            String Hql="from Detalleventa as dc ORDER BY dc.venta.idventa DESC ";
            query=session.createQuery(Hql);
            return query.list();
        } catch (Exception e) {
        }finally{
        session.close();
                
        }
    return null;
    }
}

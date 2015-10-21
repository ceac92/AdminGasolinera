/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ctgformapago;
import hibernateutil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author cesarwillian
 */
public class formapagodao {
    
    public static List<Ctgformapago> allformapago(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Hql = "from Ctgformapago";
            Query query = session.createQuery(Hql);
            return query.list();
        } catch(Exception e){
            System.err.println(e.getMessage());
        } finally{
            session.close();
        }
        return null;
    }
    
    public static Ctgformapago formapago(int idformapago){
            Session session = null;
            Query query = null;
            Ctgformapago cfp = new Ctgformapago();
            try{
              session = HibernateUtil.getSessionFactory().openSession();
              String hql = "select fp from Ctgformapago fp where fp.idctgFormaPago =:idfpago";
              query = session.createQuery(hql);
              query.setParameter("idfpago", idformapago);
              cfp = (Ctgformapago) query.uniqueResult();
            }catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
            }
            return cfp;
        }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ctgtipofactura;
import hibernateutil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author cesarwillian
 */
public class tipofacturadao {
    
    public static List<Ctgtipofactura> alltipofactura(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Hql = "from Ctgtipofactura";
            Query query = session.createQuery(Hql);
            return query.list();
        } catch(Exception e){
            System.err.println(e.getMessage());
        } finally{
            session.close();
        }
        return null;
    }
    
    public static Ctgtipofactura tipofactura(int idtipofactura){
            Session session = null;
            Query query = null;
            Ctgtipofactura tf = new Ctgtipofactura();
            try{
              session = HibernateUtil.getSessionFactory().openSession();
              String hql = "select p  from Ctgtipofactura p where p.idctgTipoFactura=:idfactura";
              query = session.createQuery(hql);
              query.setParameter("idfactura", idtipofactura);
              tf = (Ctgtipofactura) query.uniqueResult();
            }catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
            }
            return tf;
        }
    
}

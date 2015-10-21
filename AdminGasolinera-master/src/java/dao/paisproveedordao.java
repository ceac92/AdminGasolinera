/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ctgpaisproveedor;
import hibernateutil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author cesarwillian
 */
public class paisproveedordao {
    
    public static List<Ctgpaisproveedor> allpais(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Hql = "from Ctgpaisproveedor";
            Query query = session.createQuery(Hql);
            return query.list();
        } catch(Exception e){
            System.err.println(e.getMessage());
        } finally{
            session.close();
        }
        return null;
    }
    
    public static Ctgpaisproveedor pproveedor(int idpais){
            Session session = null;
            Query query = null;
            Ctgpaisproveedor cpp = new Ctgpaisproveedor();
            try{
              session = HibernateUtil.getSessionFactory().openSession();
              String hql = "select fp from Ctgpaisproveedor fp where fp.idctgPaisProveedor =:idpaisproveedor";
              query = session.createQuery(hql);
              query.setParameter("idpaisproveedor", idpais);
              cpp = (Ctgpaisproveedor) query.uniqueResult();
            }catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
            }
            return cpp;
        }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ctgtipocliente;
import hibernateutil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author cesarwillian
 */
public class tipoclientedao {
    
        public static List<Ctgtipocliente> alltipocliente(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Hql = "from Ctgtipocliente";
            Query query = session.createQuery(Hql);
            return query.list();
        } catch(Exception e){
            System.err.println(e.getMessage());
        } finally{
            session.close();
        }
        return null;
    }
        
        public static Ctgtipocliente tipocliente(int idtipocliente){
            Session session = null;
            Query query = null;
            Ctgtipocliente tc = new Ctgtipocliente();
            try{
              session = HibernateUtil.getSessionFactory().openSession();
              String hql = "select p  from Ctgtipocliente p where p.idctgTipoCliente=:idcliente";
              query = session.createQuery(hql);
              query.setParameter("idcliente", idtipocliente);
              tc = (Ctgtipocliente) query.uniqueResult();
            }catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
            }
            return tc;
        }
}

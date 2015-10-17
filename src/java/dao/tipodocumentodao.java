/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ctgtipodocumento;
import hibernateutil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author cesarwillian
 */
public class tipodocumentodao {
    
    public static List<Ctgtipodocumento> alltipodoc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String hql = "from Ctgtipodocumento";
            Query query = session.createQuery(hql);
            return query.list();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            session.close();
        }
        return null;
    }
    
    public static Ctgtipodocumento tipodocumento(int idtipodocumento){
        Session session = null;
        Query query = null;
        Ctgtipodocumento td = new Ctgtipodocumento();
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            String hql = "select d from Ctgtipodocumento d where d.idctgTipoDocumento =: iddocumento";
            query = session.createQuery(hql);
            query.setParameter("iddocumento", idtipodocumento);
            td = (Ctgtipodocumento) query.uniqueResult();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            session.close();
        }
        return td;
    }
    
}

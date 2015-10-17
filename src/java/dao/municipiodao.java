package dao;


import entity.Ctgmunicipio;
import hibernateutil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cesarwillian
 */
public class municipiodao {
    
    public static List<Ctgmunicipio> allmunicipio(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Hql = "from Ctgmunicipio";
            Query query = session.createQuery(Hql);
            return query.list();
        } catch(Exception e){
            System.err.println(e.getMessage());
        } finally{
            session.close();
        }
        return null;
    }
    
    
    public static Ctgmunicipio municipio(int idctgmunicipio){
            Session session = null;
            Query query = null;
            Ctgmunicipio cm = new Ctgmunicipio();
            try{
              session = HibernateUtil.getSessionFactory().openSession();
              String hql = "select fp from Ctgmunicipio fp where fp.idctgMunicipio =:idmunicipio";
              query = session.createQuery(hql);
              query.setParameter("idmunicipio", idctgmunicipio);
              cm = (Ctgmunicipio) query.uniqueResult();
            }catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
            }
            return cm;
        }
    
}

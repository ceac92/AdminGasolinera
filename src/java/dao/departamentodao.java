/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ctgdepartamento;
import hibernateutil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author cesarwillian
 */
public class departamentodao {
    
    public static List<Ctgdepartamento> alldepartamento(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String Hql = "from Ctgdepartamento";
            Query query = session.createQuery(Hql);
            return query.list();
        } catch(Exception e){
            System.err.println(e.getMessage());
        } finally{
            session.close();
        }
        return null;
    }
    
    public static Ctgdepartamento departamento(int idctgdepartamento){
            Session session = null;
            Query query = null;
            Ctgdepartamento cd = new Ctgdepartamento();
            try{
              session = HibernateUtil.getSessionFactory().openSession();
              String hql = "select fp from Ctgdepartamento fp where fp.idctgDepartamento =:iddepartamento";
              query = session.createQuery(hql);
              query.setParameter("iddepartamento", idctgdepartamento);
              cd = (Ctgdepartamento) query.uniqueResult();
            }catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
            }
            return cd;
        }
    
}

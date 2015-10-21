/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Empleado;
import hibernateutil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public class empleadodao {
    public static  List<Empleado> getAllempleado(Session session){
        session=null;
        Query query=null;
        
        try {
           session= HibernateUtil.getSessionFactory().openSession();
           String hql="from Empleado e where e.estado='a' ";
           query=session.createQuery(hql);
           return query.list();
           
        } catch (Exception e) {
            
        }finally{
        session.close();
        }
        return null;
    }
    
    public static List<Empleado> getEmpleadod(Session session){
    session=null;
    Query query=null;
     
       try {
           session= HibernateUtil.getSessionFactory().openSession();
           String hql="from Empleado e where e.estado='d' ";
           query=session.createQuery(hql);
           return query.list();
           
        } catch (Exception e) {
            
        }finally{
        session.close();
        }
        return null;
    }
}

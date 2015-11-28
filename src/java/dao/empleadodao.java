/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Detallecaja;
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
    
    public static  Detallecaja empleadolistaunica(Session session, String valor, int idepleado){
    session=null;
    Query query=null;
        try {
            session=HibernateUtil.getSessionFactory().openSession();
            String hql="from Detallecaja dc where dc.empleado.idempleado=:idem and dc.turno=:valor";
            query=session.createQuery(hql);
            query.setParameter("valor", valor);
            query.setParameter("idem", idepleado);
            return (Detallecaja) query.uniqueResult();
        } catch (Exception e) {
        }finally{
        session.close();
        }
        return null;
    }
    public static Empleado emplecorreo(Session session,String correo){
    session=null;
    Query query=null;
        try {
            session=HibernateUtil.getSessionFactory().openSession();
            String hql=" from Empleado as e where e.mail=:cor";
            query=session.createQuery(hql);
            query.setParameter("cor", correo);
            return  (Empleado) query.uniqueResult();
        } catch (Exception e) {
        }finally{
        session.close();
        }
        return null;
    }
}

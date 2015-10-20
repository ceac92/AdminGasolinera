/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ctgtipoproducto;
import entity.Producto;
import hibernateutil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public class productodao implements interfaces.Iproducto{


    @Override
    public Producto getByIdProducto(Session session, Integer idProducto) throws Exception {
    return (Producto) session.load(Producto.class, idProducto);  
    }

    @Override
    public List<Producto> getAll(Session session) throws Exception {
        session=null;
        Query query=null;
        try {
            session=HibernateUtil.getSessionFactory().openSession();
           String hql="from Producto p where p.estado='a'";
           query=session.createQuery(hql);
           return query.list();
           
        } catch (Exception e) {
        }finally{
        session.close();
        }
        return null;
    }
   
    public static List<Producto> getALLproductosd(Session session)throws Exception{
     session=null;
     Query query=null;
        try {
            session=HibernateUtil.getSessionFactory().openSession();
            String hql="from Producto p where p.estado='d'";
            query=session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
           
        }finally{
         session.close();
        }
        return null;
    } 
    
    public static List<Ctgtipoproducto> listcategoria(Session session){
    session=null;
    Query query=null;
        try {
            session=HibernateUtil.getSessionFactory().openSession();
            String hql="from Ctgtipoproducto";
            query=session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
        }
    return null;
    }
}

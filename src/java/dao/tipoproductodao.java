/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ctgtipoproducto;
import hibernateutil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public class tipoproductodao  {
    /* si hacer interfaces con la palabra "implements" y el nombre de interface lo llamadas para que cree
    metodos astractos  lo que esta aquie son unos basicos pero no todos puede hacer estaticos unos se debe de 
    implementar.
    
 */
// esta lo que hacer trae todos los elementos de la tabla  static es para ser llamada desde cualquier lugar.
    public static List<Ctgtipoproducto> alltiproductos() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "from Ctgtipoproducto";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;

    }

    public static Ctgtipoproducto tipoproducto(int idtipoproducto)  {
        Session session = null;
        Query query = null;
        Ctgtipoproducto ctp = new Ctgtipoproducto();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select p  from Ctgtipoproducto p where p.idctgTipoProducto=:idproducto";
            query = session.createQuery(hql);
            query.setParameter("idproducto", idtipoproducto);
            ctp = (Ctgtipoproducto) query.uniqueResult();
            

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
        }
       return ctp;
    }

}

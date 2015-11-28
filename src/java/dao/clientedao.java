/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Cliente;
import hibernateutil.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public class clientedao {

    public static Cliente clientecorreo(Session session, String correo) {
        session = null;
        Query query = null;
        try {
            session=HibernateUtil.getSessionFactory().openSession();
            String Hql="from Cliente as c where c.mail=:core";
            query=session.createQuery(Hql);
            query.setParameter("core", correo);
            return (Cliente) query.uniqueResult();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Empleado;
import interfaces.Iusuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Erick
 */
public class loginDao implements Iusuario {

    @Override
    public Empleado getEmpLogin(Session session, String email) throws Exception {

        String hql = "FROM Empleado WHERE mail=:Usuario";
        Query query = session.createQuery(hql);
        query.setParameter("Usuario", email);
        Empleado user = (Empleado) query.uniqueResult();
        return user;

    }
public static  Empleado getEmple(Session session,int idempleado){
  String hql = "FROM Empleado WHERE  idempleado=:Usuario";
        Query query = session.createQuery(hql);
        query.setParameter("Usuario", idempleado);
        Empleado user = (Empleado) query.uniqueResult();
        return user;
}
   
}

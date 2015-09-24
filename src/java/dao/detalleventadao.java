/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Detalleventa;
import interfaces.Idetalleventa;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public class detalleventadao implements Idetalleventa {

    @Override
    public boolean insert(Session session, Detalleventa detalleventa) throws Exception {
        session.save(detalleventa);
        return true;
    }

}

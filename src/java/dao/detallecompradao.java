/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Detallecompra;
import interfaces.Idetallecompra;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public class detallecompradao implements Idetallecompra {

    @Override
    public  boolean insert(Session session, Detallecompra detallecompra) throws Exception {
        session.save(detallecompra);

        return true;
    }

}

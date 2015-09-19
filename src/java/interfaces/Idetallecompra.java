/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Detallecompra;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public interface Idetallecompra {
     public boolean insert(Session session, Detallecompra detallecompra) throws Exception;
}

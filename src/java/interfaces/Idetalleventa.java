/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Detalleventa;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public interface Idetalleventa {
        public boolean insert(Session session, Detalleventa detalleventa) throws Exception;
    
}

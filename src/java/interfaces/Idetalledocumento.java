/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Detalledocumento;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public interface Idetalledocumento {
          public boolean insert(Session session, Detalledocumento detalledocumento) throws Exception;
    
}

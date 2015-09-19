/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Compra;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public interface Icompra {
     public boolean insert(Session session, Compra compra) throws Exception;
    public  Compra  getUltimoRegistro(Session session) throws Exception;
}

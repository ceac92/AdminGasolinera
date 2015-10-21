/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Venta;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public interface Iventa {

    public boolean insert(Session session, Venta venta) throws Exception;

    public Venta getUltimoRegistro(Session session) throws Exception;
}

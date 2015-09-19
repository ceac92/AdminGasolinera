/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Producto;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public interface Iproducto {

    public Producto getByIdProducto(Session session, Integer idProducto) throws Exception;

    public List<Producto> getAll(Session session) throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Producto;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public class productodao implements interfaces.Iproducto{


    @Override
    public Producto getByIdProducto(Session session, Integer idProducto) throws Exception {
    return (Producto) session.load(Producto.class, idProducto);  
    }

    @Override
    public List<Producto> getAll(Session session) throws Exception {
        return session.createCriteria(Producto.class).list();
    }
    
  

             
}

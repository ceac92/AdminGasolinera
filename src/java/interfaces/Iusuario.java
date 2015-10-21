/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Empleado;
import org.hibernate.Session;

/**
 *
 * @author Erick
 */
public interface Iusuario {
    
     public  Empleado getEmpLogin(Session session, String mail) throws Exception;
    
}

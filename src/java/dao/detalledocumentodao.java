/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entity.Detalledocumento;
import interfaces.Idetalledocumento;
import org.hibernate.Session;
/**
 *
 * @author An
 */
public class detalledocumentodao implements Idetalledocumento{

    @Override
    public boolean insert(Session session, Detalledocumento detalledocumento) throws Exception {
        session.save(detalledocumento);
        return true;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entity.Ctgtipodocumento;
import interfaces.Idocumento;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public class documentodao implements Idocumento {

    @Override
    public Ctgtipodocumento getByIdProducto(Session session, Integer iddocumento) throws Exception {
      return (Ctgtipodocumento) session.load(Ctgtipodocumento.class, iddocumento);
    }

    @Override
    public List<Ctgtipodocumento> getAll(Session session) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

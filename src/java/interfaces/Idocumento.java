/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Ctgtipodocumento;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author An
 */
public interface Idocumento {
      public Ctgtipodocumento getByIdProducto(Session session, Integer iddocumento) throws Exception;

    public List<Ctgtipodocumento> getAll(Session session) throws Exception;

}

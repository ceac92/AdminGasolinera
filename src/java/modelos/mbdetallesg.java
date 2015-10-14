/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entity.Detallecompra;
import hibernateutil.HibernateUtil;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author An
 */
@ManagedBean
@SessionScoped
public class mbdetallesg implements Serializable{
    Session session;

    public int getIddetallecompra() {
        return iddetallecompra;
    }

    public void setIddetallecompra(int iddetallecompra) {
        this.iddetallecompra = iddetallecompra;
    }
    private int iddetallecompra ;

    /**
     * Creates a new instance of mbdetallesg
     */
    public mbdetallesg() {
    }
     public void verDetalles(ActionEvent miActionEvent, int iddetacompra) throws IOException {
        if (iddetacompra != 0) {
            this.iddetallecompra = iddetacompra;
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrador/detallecompra.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo procesar la peticion"));
        }
    }
      public  List<Detallecompra> detallecompr(int valor ){
       session=null;
        Query query=null;
        try {
            valor=this.iddetallecompra;
            session=HibernateUtil.getSessionFactory().openSession();
            String hql="select dc.cantidad, p.precioCosto,p.nombre from Detallecompra as dc inner join dc.producto as p inner join dc.compra as c where c.idcompra=:iddetalle";
            query=session.createQuery(hql);
              query.setParameter("iddetalle", valor);
            return query.list();
        } catch (Exception e) {
        }finally{
        session.close();
        }
    return null;
    }
}

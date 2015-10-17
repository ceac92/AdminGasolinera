/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.paisproveedordao;
import entity.Ctgpaisproveedor;
import hibernateutil.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author cesarwillian
 */
@Named("mbpproveedor")
@ViewScoped
public class mbpaisproveedor implements Serializable {
    
    Session session;
    Transaction transaction;
    private Ctgpaisproveedor pp;
    private List<Ctgpaisproveedor> listpproveedor;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Ctgpaisproveedor getPp() {
        return pp;
    }

    public void setPp(Ctgpaisproveedor pp) {
        this.pp = pp;
    }

    public List<Ctgpaisproveedor> getListpproveedor() {
        return listpproveedor;
    }

    public void setListpproveedor(List<Ctgpaisproveedor> listpproveedor) {
        this.listpproveedor = listpproveedor;
    }

    
    /**
     * Creates a new instance of mbpaisproveedor
     */
    public mbpaisproveedor() {
        this.pp = new Ctgpaisproveedor();
    }
    
    @Inject
    private Ctgpaisproveedor paisproveedor;
    
    public void agregar() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Ctgpaisproveedor cp = new Ctgpaisproveedor();
        cp.setCodigo(this.paisproveedor.getCodigo());
        cp.setNombre(this.paisproveedor.getNombre());
        session.save(cp);
        transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "se agrego"));

        try {

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

    }
    
    public List<Ctgpaisproveedor> getAllpproveedor() {
        this.listpproveedor = paisproveedordao.allpais();
        return this.listpproveedor;
    }
    
    public void actualizar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.session.update(this.pp);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Actualizacion realizada"));
        } catch (Exception e) {
            this.transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } finally {
            this.session.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.tipoclientedao;
import entity.Ctgtipocliente;
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
@Named(value = "mbtipocliente")
@ViewScoped
public class mbtipocliente implements Serializable {
    
    Session session;
    Transaction transaction;
    private Ctgtipocliente tcliente;
    private List<Ctgtipocliente> listtipocliente;

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

    public Ctgtipocliente getTcliente() {
        return tcliente;
    }

    public void setTcliente(Ctgtipocliente tcliente) {
        this.tcliente = tcliente;
    }

    public List<Ctgtipocliente> getListtipocliente() {
        return listtipocliente;
    }

    public void setListtipocliente(List<Ctgtipocliente> listtipocliente) {
        this.listtipocliente = listtipocliente;
    }

    /**
     * Creates a new instance of mbtipocliente
     */
    public mbtipocliente() {
        this.tcliente = new Ctgtipocliente();
    }
    @Inject
    private Ctgtipocliente tipocliente;
    
    public void agregar(){

        try {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.transaction = this.session.beginTransaction();
        Ctgtipocliente tc = new Ctgtipocliente();
        tc.setNombre(this.tipocliente.getNombre());
        tc.setDescripcion(this.tipocliente.getDescripcion());
        this.session.save(tc);
        this.transaction.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "se agrego"));

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }
    
    
    public List<Ctgtipocliente> getAlltipocliente(){
        this.listtipocliente = tipoclientedao.alltipocliente();
        return this.listtipocliente;
    }
    
    public void actualizar(){
        this.session = null;
        this.transaction = null;
        try{
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.session.update(this.tcliente);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Actualizacion realizada"));
        } catch(Exception e){
            this.transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } finally{
            this.session.close();
        }
    }
    
}

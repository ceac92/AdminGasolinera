/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.formapagodao;
import entity.Ctgformapago;
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
@Named(value = "mbformapago")
@ViewScoped

public class mbformapago implements Serializable {

 
    Session session;
    Transaction transaction;
    private Ctgformapago formapago;
    private List<Ctgformapago> listformapago;

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

    public Ctgformapago getFormapago() {
        return formapago;
    }

    public void setFormapago(Ctgformapago formapago) {
        this.formapago = formapago;
    }

    public List<Ctgformapago> getListformapago() {
        return listformapago;
    }

    public void setListformapago(List<Ctgformapago> listformapago) {
        this.listformapago = listformapago;
    }
    /**
     * Creates a new instance of mbformapago
     */
    public mbformapago() {
        this.formapago = new Ctgformapago();
    }
    @Inject
    private Ctgformapago fpago;
    
    public void agregar(){

        try {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.transaction = this.session.beginTransaction();
        Ctgformapago fp = new Ctgformapago();
        fp.setNombre(this.fpago.getNombre());
        fp.setDescripcion(this.fpago.getDescripcion());
        this.session.save(fp);
        this.transaction.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "se agrego"));

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }
    
    public List<Ctgformapago> getAllformapago(){
        this.listformapago = formapagodao.allformapago();
        return this.listformapago;
    }
    
    public void actualiza(){
        this.session = null;
        this.transaction = null;
        try{
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.session.update(this.formapago);
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

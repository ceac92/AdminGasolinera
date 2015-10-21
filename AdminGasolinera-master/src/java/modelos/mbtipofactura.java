/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.tipofacturadao;
import entity.Ctgtipofactura;
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
@Named(value = "mbtipofactura")
@ViewScoped
public class mbtipofactura implements Serializable {
    
    Session session;
    Transaction transaction;
    private Ctgtipofactura tfactura;
    private List<Ctgtipofactura> listtipofactura;

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

    public Ctgtipofactura getTfactura() {
        return tfactura;
    }

    public void setTfactura(Ctgtipofactura tfactura) {
        this.tfactura = tfactura;
    }

    public List<Ctgtipofactura> getListtipofactura() {
        return listtipofactura;
    }

    public void setListtipofactura(List<Ctgtipofactura> listtipofactura) {
        this.listtipofactura = listtipofactura;
    }
    
    
    /**
     * Creates a new instance of mbtipofactura
     */
    public mbtipofactura() {
        this.tfactura = new Ctgtipofactura();
    }
    
    @Inject
    private Ctgtipofactura tipofactura;
    
    public void agregar(){

        try {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.transaction = this.session.beginTransaction();
        Ctgtipofactura tf = new Ctgtipofactura();
        tf.setNombre(this.tipofactura.getNombre());
        tf.setDescripcion(this.tipofactura.getDescripcion());
        this.session.save(tf);
        this.transaction.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "se agrego"));

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }
    
    public List<Ctgtipofactura> getAlltipofactura(){
        this.listtipofactura = tipofacturadao.alltipofactura();
        return this.listtipofactura;
    }
    
        public void actualizar(){
        this.session = null;
        this.transaction = null;
        try{
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.session.update(this.tfactura);
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

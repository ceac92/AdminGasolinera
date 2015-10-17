/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


import dao.tipodocumentodao;
import entity.Ctgtipodocumento;
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
@Named(value = "mbtipodocumento")
@ViewScoped
public class mbtipodocumento implements Serializable {
    
    Session session;
    Transaction transaction;
    private Ctgtipodocumento tdocumento;
    
    private List<Ctgtipodocumento> listtipodocumento;

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

    public Ctgtipodocumento getTdocumento() {
        return tdocumento;
    }

    public void setTdocumento(Ctgtipodocumento tdocumento) {
        this.tdocumento = tdocumento;
    }

    public List<Ctgtipodocumento> getListtipodocumento() {
        return listtipodocumento;
    }

    public void setListtipodocumento(List<Ctgtipodocumento> listtipodocumento) {
        this.listtipodocumento = listtipodocumento;
    }

    /**
     * Creates a new instance of mbtipodocumento
     */
    public mbtipodocumento() {
        this.tdocumento = new Ctgtipodocumento();
    }
    @Inject
    private Ctgtipodocumento tipodocumento;
    
    public void agregar(){
        try{
            this.session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Ctgtipodocumento ctd = new Ctgtipodocumento();
            ctd.setNombre(this.tipodocumento.getNombre());
            ctd.setDescripcio(this.tipodocumento.getDescripcio());
            session.save(ctd);
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "se agrego"));
        }catch(Exception e){
            transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }finally{
            session.close();
        }
    }
    
    public List<Ctgtipodocumento> getAlltipodoc(){
    this.listtipodocumento = tipodocumentodao.alltipodoc();
    return listtipodocumento;
    }
    
    public void actualizar(){
    session = null;
    transaction = null;
    try{
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(this.tdocumento);
        transaction.commit();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Actualizacion realizada"));
        }catch(Exception e){
            transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }finally{
        session.close();
        }
    }
    
}

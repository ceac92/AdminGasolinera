/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.municipiodao;
import entity.Ctgdepartamento;
import entity.Ctgmunicipio;
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
@Named("mbmunicipio")
@ViewScoped
public class mbmunicipio implements Serializable{
    
    Session session;
    Transaction transaction;
    private Ctgmunicipio vmunicipio;
    private List<Ctgmunicipio> listmunicipio;
    private int iddepartamento;

    public int getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(int iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

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

    public Ctgmunicipio getVmunicipio() {
        return vmunicipio;
    }

    public void setVmunicipio(Ctgmunicipio vmunicipio) {
        this.vmunicipio = vmunicipio;
    }

    public List<Ctgmunicipio> getListmunicipio() {
        return listmunicipio;
    }

    public void setListmunicipio(List<Ctgmunicipio> listmunicipio) {
        this.listmunicipio = listmunicipio;
    }

    /**
     * Creates a new instance of mbmunicipio
     */
    public mbmunicipio() {
        this.vmunicipio = new Ctgmunicipio();
    }
    
    @Inject
    private Ctgmunicipio municipio;
    
    public void agregar() {
        
        try {
        this.session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Ctgmunicipio cp = new Ctgmunicipio();
        cp.setCtgdepartamento((Ctgdepartamento) session.get(Ctgdepartamento.class, this.iddepartamento));
        cp.setNombre(this.municipio.getNombre());
        session.save(cp);
        transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "se agrego"));

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

    }
    
    public List<Ctgmunicipio> getAllmunicipio() {
        this.listmunicipio = municipiodao.allmunicipio();
        return this.listmunicipio;
    }
    
    public void actualizar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.session.update(this.vmunicipio);
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.departamentodao;
import entity.Ctgdepartamento;
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
@Named("mbdepartamento")
@ViewScoped

public class mbdepartamento implements Serializable {

    Session session;
    Transaction transaction;
    private Ctgdepartamento vdepartamento;
    private List<Ctgdepartamento> listdepartamento;

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

    public Ctgdepartamento getVdepartamento() {
        return vdepartamento;
    }

    public void setVdepartamento(Ctgdepartamento vdepartamento) {
        this.vdepartamento = vdepartamento;
    }

    public List<Ctgdepartamento> getListdepartamento() {
        return listdepartamento;
    }

    public void setListdepartamento(List<Ctgdepartamento> listdepartamento) {
        this.listdepartamento = listdepartamento;
    }

    /**
     * Creates a new instance of mbdepartamento
     */
    public mbdepartamento() {
        this.vdepartamento = new Ctgdepartamento();
    }

    @Inject
    private Ctgdepartamento departament;

    public void agregar() {
        if (departament.getIdctgDepartamento() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No es nombre"));
            return;
        }

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Ctgdepartamento cp = new Ctgdepartamento();
            cp.setNombre(this.departament.getNombre());
            session.save(cp);
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "se agrego"));

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    public List<Ctgdepartamento> getAlldepartamento() {
        this.listdepartamento = departamentodao.alldepartamento();
        return this.listdepartamento;
    }

    public void actualizar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.session.update(this.vdepartamento);
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

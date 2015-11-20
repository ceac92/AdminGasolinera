/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.empleadodao;
import dao.loginDao;
import entity.Ctgtipodocumento;
import entity.Detallecaja;
import entity.Detalledocumento;
import entity.Empleado;
import hibernateutil.HibernateUtil;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author An
 */
@Named(value = "mbconsultag")
@SessionScoped
public class mbconsultag implements Serializable {

    Session session;
    Transaction transaction;
    private int idemple;
    private Empleado emple;
    private Detalledocumento detadocu;
    private int idtipodocumento;
    private String turno;
    private Detallecaja cajas;

    public Detallecaja getCajas() {
        return cajas;
    }

    public void setCajas(Detallecaja cajas) {
        this.cajas = cajas;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(int idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    public Detalledocumento getDetadocu() {
        return detadocu;
    }

    public void setDetadocu(Detalledocumento detadocu) {
        this.detadocu = detadocu;
    }

    public int getIdemple() {
        return idemple;
    }

    public void setIdemple(int idemple) {
        this.idemple = idemple;
    }

    public Empleado getEmple() {
        return emple;
    }

    public void setEmple(Empleado emple) {
        this.emple = emple;
    }

    /**
     * Creates a new instance of mbconsultag
     */
    public mbconsultag() {
        this.detadocu = new Detalledocumento();
    }

    public void empleadoupdate(ActionEvent miActionEvent, int idempleadoup) throws IOException {
        if (idempleadoup != 0) {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.idemple = idempleadoup;
            this.emple = loginDao.getEmple(session, idempleadoup);
            FacesContext.getCurrentInstance().getExternalContext().redirect("registroempleado.xhtml");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo procesar la peticion"));
        }
        session.close();
    }

    public void updateempleado() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            this.session.update(this.emple);
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado", "Actualizado"));
        } catch (Exception e) {
            this.transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } finally {
            this.session.close();
        }

    }

    public List<Detalledocumento> getAlldocumentos(int valor) {
        this.session = null;
        Query query = null;
        try {
            valor = this.idemple;
            String hql = "from Detalledocumento dd where dd.empleado.idempleado=:idempleado ";
            this.session = HibernateUtil.getSessionFactory().openSession();
            query = session.createQuery(hql);
            query.setParameter("idempleado", valor);
            return query.list();
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return null;
    }

    public void upddatedocumento() {
        this.session = null;
        this.transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            this.detadocu.setCtgtipodocumento((Ctgtipodocumento) session.get(Ctgtipodocumento.class, this.idtipodocumento));
            session.update(this.detadocu);
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Documento", "Actualizado"));
        } catch (Exception e) {
            transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } finally {
            session.close();
        }
    }

    public List<Detallecaja> getAllcaja(int idemple) {
        this.session = null;
        Query query = null;
        try {
            idemple=this.idemple;
            this.session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from Detallecaja dd where dd.empleado.idempleado=:uno";
            query = session.createQuery(hql);
            query.setParameter("uno",idemple );
            return query.list();
        } catch (Exception e) {
        }
        return null;
    }

    public void guardarTurno() {
        this.session = null;
        this.transaction = null;
        Detallecaja dc = new Detallecaja();
       this.cajas= empleadodao.empleadolistaunica(session, turno, idemple);
         if (this.cajas != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Turno ya creado"));
            return;
        }
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            dc.setEmpleado((Empleado) session.get(Empleado.class, this.idemple));
            dc.setTurno(this.turno);
            session.save(dc);
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "turno", "Agregado"));
        } catch (Exception e) {
            transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage()));
        } finally {
            session.close();
        }

    }
}
